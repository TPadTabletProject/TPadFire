/* 
 * File:   main.c
 * Author: Motoki Mizoguchi based on code from David Meyer
 *
 * Created on August 22nd, 2012
 *
 * Editor: Craig Shultz
 *
 * Edited on November 21st, 2012
 * Added USB Host functionality and removed need for ADC sampling and converstion
 * TPad now interfaces with Kindle Fire via USB and the Android Debug Port Bridge (ADB)
 */

// DEVCFG3:
#pragma config IOL1WAY  = OFF       // Peripheral Pin Select Configuration, allow mult reconfig
#pragma config PMDL1WAY = OFF       // Peripheral Module Disable Config, allow mult reconfig
// DEVCFG2:
#pragma config UPLLEN   = ON        // USB PLL Enable
#pragma config UPLLIDIV = DIV_2     // USB PLL Divider
// 8MHz / 2 * 24 / 2 = 48MHz
#pragma config FPLLODIV = DIV_2     // PLL Output Divider
#pragma config FPLLMUL  = MUL_20    // PLL Multiplier
#pragma config FPLLIDIV = DIV_2     // PLL Input Divider
// 8MHz / 2 * 20 / 2 = 40MHz

// DEVCFG1:
#pragma config FWDTEN   = OFF       // Watchdog Timer
#pragma config WDTPS    = PS1       // Watchdog Timer Postscale
#pragma config FCKSM    = CSDCMD    // Clock Switching & Fail Safe Clock Monitor
#pragma config FPBDIV   = DIV_1     // Peripheral Clock divisor
#pragma config OSCIOFNC = OFF       // CLKO Enable
#pragma config POSCMOD  = HS        // Primary Oscillator
#pragma config IESO     = OFF       // Internal/External Switch-over
#pragma config FSOSCEN  = OFF       // Secondary Oscillator Enable (KLO was off)
#pragma config FNOSC    = PRIPLL    // Oscillator Selection
// DEVCFG0:
#pragma config CP       = OFF       // Code Protect
#pragma config BWP      = ON        // Boot Flash Write Protect
#pragma config PWP      = OFF       // Program Flash Write Protect
#pragma config ICESEL   = ICS_PGx1  // ICE/ICD Comm Channel Select
#pragma config JTAGEN   = OFF       // JTAG Enable
#pragma config DEBUG    = OFF       // Background Debugger Enable


/** Includes *******************************************************/
#include <plib.h>
#include "adb.h"
#include "connection.h"
#include "HardwareProfile.h"

/** PreCompiler Definitions ****************************************/
#define DEBUGLED LATBbits.LATB7
#define OUTPUT_FREQ 33500  //PWM output freq
#define BUFFER_FREQ 10000 //Buffer output timer freq
#define MAX_BUFFER_LENGTH 1000  //4kB with 1 byte per value
#define MAX_BYTE_SIZE 255

#define COMMAND_MAG 0x0a
#define COMMAND_FREQ 0x0b
#define COMMAND_TEXT 0x0c
#define COMMAND_ALIVE 0x0d

/** Global Variables ***********************************************/
static int tpadMAG;
static int tpadFREQ;

int count = 0;
int incoming = 0;

typedef struct {
    BYTE Buff0[MAX_BUFFER_LENGTH];
    BYTE Buff1[MAX_BUFFER_LENGTH];
    BYTE Front;
    int Index;
    int Length0;
    int Length1;
    BYTE On;
} TextureBuff;
static TextureBuff tBuff;

typedef enum {
    STATE_INIT,
    STATE_WAITING,
    STATE_CONNECTING,
    STATE_CONNECTED
} STATE;

static CHANNEL_HANDLE h;
static STATE state = STATE_INIT;

/** Function Headers ***********************************************/
void PWM_Set_DC(double dc);
void setTimer2(int freq);
void setupTimer3(int freq);
void pauseTimer3();
void resumeTimer3();

void initPWM(void);
void PIC32MX250_setup_pins(void);
void handleTexture(BYTE* raw_data, UINT32 data_len);

// ADB communication channels
void ADBCallback(ADB_CHANNEL_HANDLE handle, const void* data, UINT32 data_len);
// Convert between bytes and integers/bytes and shorts
int intFromBytes(BYTE* in);
unsigned short shortFromBytes(BYTE* in);

union {
    BYTE array[4];
    int value;
} iConverter;

union {
    BYTE array[2];
    unsigned short value;
} sConverter;

int main() {
    CFGCONbits.JTAGEN = 0; // turn off JTAG, get back those pins for IO use
    // set PIC32to max computing power
    DEBUGLED = 0;
    // enable multi-vector interrupts
    INTEnableSystemMultiVectoredInt();
    INTEnableInterrupts();
    PIC32MX250_setup_pins();
    SYSTEMConfigPerformance(SYS_FREQ);

    setTimer2(OUTPUT_FREQ); //
    setupTimer3(BUFFER_FREQ);
    initPWM(); //Initializing PWM on OC3.

    // Initialize texture buffer and index to zero

    int i;
    tBuff.Index = 0; //init to zero
    tBuff.On = 0; //turn off
    tBuff.Length0 = 0; //start with zero length
    tBuff.Length1 = 0; //start with zero length
    tBuff.Front = 0;
    for (i = 0; i < MAX_BUFFER_LENGTH; i++) {
        tBuff.Buff0[i] = 0; //init entire buffer to zero
        tBuff.Buff1[i] = 0; //init entire buffer to zero
    }


    // Initialize the USB host
    ConnectionInit();
    DEBUGLED = 1;

    //Main USB State Machine
    while (1) {
        // Keep the USB connection running;
        // Handle incoming data and manage outgoing data.
        ConnectionTasks();
        // Main state machine
        switch (state) {
            case STATE_INIT:

                state = STATE_WAITING;
                h = INVALID_CHANNEL_HANDLE;
                break;

            case STATE_WAITING:
                DEBUGLED = 0;
                if (ADBAttached()) {
                    state = STATE_CONNECTING;
                }
                break;

            case STATE_CONNECTING:

                if (ADBConnected()) {
                    // Open a channel to the Android device
                    // See "adb.h" in libadb for more details
                    // (I don't think the name tcp:4545 matters)
                    h = ADBOpen("tcp:4545", &ADBCallback);

                    if (h != INVALID_CHANNEL_HANDLE) {
                        state = STATE_CONNECTED;
                        WriteCoreTimer(0);
                        // Send plaintext and let the recipient do formatting
                        ADBWrite(h & 0xFF, "Hello from TPAD!", 17);
                    }
                }
                break;

            case STATE_CONNECTED:
                DEBUGLED = 1;

                if (!ADBAttached()) {
                    state = STATE_INIT;
                }
                if (ADBChannelReady(h)) {

                    // Execute tasks that rely on the Android-PIC32 connection
                    // Here we will just wait for messages to come in and be handled below
                }
                // Timeout timer. If the coretimer is not reset by a keepalive command from the Android, the PIC will reset the USB communications
                if (ReadCoreTimer() > 200000000) {
                    state = STATE_INIT;
                }

                break;
        } // end state machine
    } // end while loop

    return 0;
}

// Timer2 Set up function.

void setTimer2(int freq) {
    T2CON = 0b000 << 4; // set prescaler to 1:1
    T2CONSET = 1 << 15; // turn Timer2 on
    T2CONCLR = 0x2; // set input to PBCLK (the default; all defaults fine)

    PR2 = (SYS_FREQ / freq) - 1; // set the period in period register 2
    TMR2 = 0; // reset the Timer2 count
}

void setupTimer3(int freq) {

    INTDisableInterrupts(); //disable interrupts while settting up

    T3CON = 0b000 << 4; // set prescaler to 1:1
    T3CONCLR = 0x2; // set input to PBCLK (the default; all defaults fine)
    PR3 = (SYS_FREQ / freq) - 1; // set the period in period register 3
    TMR3 = 0; // reset the Timer3 count
    T3CONSET = 1 << 15; // turn Timer3 on


    IPC3bits.T3IP = 5; // set timer interrupt priority
    IPC3bits.T3IS = 0; // set subpriority
    IFS0bits.T3IF = 0; // clear interrupt flag


    INTEnableSystemMultiVectoredInt(); // enable interrupts at CPU
    INTEnableInterrupts();
}

void pauseTimer3() {
    IEC0bits.T3IE = 0; // disable interrupt
}

void resumeTimer3() {
    TMR3 = 0; // reset the Timer3 count
    IEC0bits.T3IE = 1; // enable interrupt
}

// following is used for setting the duty cycle as a percent

void PWM_Set_DC(double dc) {
    if (dc > 50) //maximum duty cycle is 50%
        dc = 50;
    if (dc < 1) //minimum would be 1%
        dc = 1;
    // Convert the percent to a number of counts
    OC3RS = (int) ((dc * (PR2 + 1)) / 100.0) - 1;
}

//PWM Initialization function.

void initPWM(void) {
    OC3CONbits.OCM = 6; //PWM mode with no fault protection
    OC3RS = 0; // set buffered PWM duty cycle in counts
    OC3R = 0; // set initial PWM duty cycle in counts
    OC3CONbits.ON = 1; //Turn OC3 on.
    PWM_Set_DC(0); //Set duty cycle to 0%
};


// ===========================
// Helper function definitions
// ===========================

// Print any data received.
// This function is automatically called when data is received over ADB.

void ADBCallback(ADB_CHANNEL_HANDLE handle, const void* data, UINT32 data_len) {
    // Ignore empty messages
    if (data_len < 1) {
        return;
    }


    //DEBUGLED = 0;

    // Get data without the first byte
    //BYTE raw_data[data_len - 1];
    //memmove(raw_data, data + 1, data_len - 1);

    // First byte identifies the data
    BYTE* id = (BYTE*) data;

    if (data_len > 5) {

        switch (*id) {
            case COMMAND_TEXT:

                handleTexture(((BYTE*) data) + 1, data_len - 1);

                break;

            default:
                break;
        }
        return;


    }
    switch (*id) {

        case COMMAND_FREQ:

            incoming = intFromBytes(((BYTE*) data) + 1);
            PR2 = (SYS_FREQ / incoming) - 1;
            TMR2 = 0; // reset the Timer2 count
            

            break;

        case COMMAND_MAG:

            tpadMAG = (int)*(((BYTE*) data) + 1);
            PWM_Set_DC(tpadMAG / 255.0 * 50); //scale incoming magnitude to a duty cycle 0-50
            pauseTimer3();
            tBuff.On = 0x0;

            break;

        case COMMAND_ALIVE:
            WriteCoreTimer(0);
            break;

        default:
            break;
    }
    return;

}

void handleTexture(BYTE* raw_data, UINT32 raw_length) {
    //load new raw data into texture buffer
    //RAW DATA CANNOT EXCEED 4000BYTES!
    //The data length here should always be divible by 2
    if (tBuff.Front == 1) {

        if ((raw_length) < (MAX_BUFFER_LENGTH + 1)) {
            //if the received data is longer than our buffer, shorten it.
            tBuff.Length0 = raw_length;
        } else {
            tBuff.Length0 = MAX_BUFFER_LENGTH;
        }
        memmove(tBuff.Buff0, raw_data, tBuff.Length0);
        tBuff.Index = 0;
        tBuff.Front = 0;

    } else if (tBuff.Front == 0) {

        if ((raw_length) < (MAX_BUFFER_LENGTH + 1)) {
            //if the received data is longer than our buffer, shorten it.
            tBuff.Length1 = raw_length;
        } else {
            tBuff.Length1 = MAX_BUFFER_LENGTH;
        }
        memmove(tBuff.Buff1, raw_data, tBuff.Length1);
        tBuff.Index = 0;
        tBuff.Front = 1;
    }


    //check to see if the timer is already started, if not, then start it!
    if (tBuff.On == 0x0) {
        tBuff.On = 0x1;
        resumeTimer3();
    }

}

void __ISR(_TIMER_3_VECTOR, IPL5SOFT) Timer3ISR(void) {
    //Update our PWM with the proper buffer value
    if (tBuff.Front == 1) {
        PWM_Set_DC(tBuff.Buff1[tBuff.Index] / 255.0 * 50.0);

        tBuff.Index++;
        //Check to see if we are off the end of our valid data
        if (tBuff.Index > tBuff.Length1 - 1) {
            //if we are, then reset the index to zero
            tBuff.Index = 0;
        }

    } else if (tBuff.Front == 0) {

        PWM_Set_DC(tBuff.Buff0[tBuff.Index] / 255.0 * 50.0);

        tBuff.Index++;
        //Check to see if we are off the end of our valid data
        if (tBuff.Index > tBuff.Length0 - 1) {
            //if we are, then reset the index to zero
            tBuff.Index = 0;
        }


    }
    IFS0bits.T3IF = 0; // clear interrupt flag
}

// Convert an array of four bytes to an integer

int intFromBytes(BYTE* in) {
    iConverter.array[0] = *(in);
    iConverter.array[1] = *(in + 1);
    iConverter.array[2] = *(in + 2);
    iConverter.array[3] = *(in + 3);
    return iConverter.value;
}

unsigned short shortFromBytes(BYTE* in) {
    sConverter.array[0] = *(in);
    sConverter.array[1] = *(in + 1);
    return sConverter.value;
}
