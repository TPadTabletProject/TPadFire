/*
 * Copyright 2011 Ytai Ben-Tsvi. All rights reserved.
 *
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ARSHAN POURSOHI OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied.
 */

// Capabilities and features of specific platforms.
// A platform is a combination of a hardaware interface and bootloader ABI.
//
// Provides:
// NUM_PINS         - The number of physical pins on the board, including the
//                    on-board LED.
// NUM_PWM_MODULES  - The number of available PWM modules.
// NUM_UART_MODULES - The number of available UART modules.
// NUM_SPI_MODULES  - The number of available SPI modules.
// NUM_I2C_MODULES  - The number of available I2C modules.

#ifndef __PLATFORM_H__
#define __PLATFORM_H__

#define PLATFORM_IOIO_BASE 1000  // base platform number for 'official' IOIO platforms
// BLAPI 1
#define PLATFORM_IOIO0000 PLATFORM_IOIO_BASE + 0
#define PLATFORM_IOIO0001 PLATFORM_IOIO_BASE + 1
#define PLATFORM_IOIO0002 PLATFORM_IOIO_BASE + 2
#define PLATFORM_IOIO0003 PLATFORM_IOIO_BASE + 3
// BLAPI 3
#define PLATFORM_IOIO0020 PLATFORM_IOIO_BASE + 20
#define PLATFORM_IOIO0021 PLATFORM_IOIO_BASE + 21
#define PLATFORM_IOIO0022 PLATFORM_IOIO_BASE + 22
#define PLATFORM_IOIO0023 PLATFORM_IOIO_BASE + 23
// add more platforms here!

#define HARDWARE_IOIO_BASE 1000  // base hardware interface version number for 'official' IOIO platforms
#define HARDWARE_IOIO0000 HARDWARE_IOIO_BASE + 0
#define HARDWARE_IOIO0001 HARDWARE_IOIO_BASE + 1
#define HARDWARE_IOIO0002 HARDWARE_IOIO_BASE + 2
#define HARDWARE_IOIO0003 HARDWARE_IOIO_BASE + 3

// arbitrary numbers chosen for NU32 definitions
#define PLATFORM_NU32 400
#define HARDWARE_NU32 400

// arbitrary numbers chosen for TPAD definitions
#define PLATFORM_TPAD 401
#define HARDWARE_TPAD 401

#ifndef PLATFORM
#error Must define PLATFORM
#endif

// derive hardware from plaftform
#if PLATFORM == PLATFORM_IOIO0000 || PLATFORM == PLATFORM_IOIO0020
#define HARDWARE HARDWARE_IOIO0000
#elif PLATFORM == PLATFORM_IOIO0001 || PLATFORM == PLATFORM_IOIO0021
#define HARDWARE HARDWARE_IOIO0001
#elif PLATFORM == PLATFORM_IOIO0002 || PLATFORM == PLATFORM_IOIO0022
#define HARDWARE HARDWARE_IOIO0002
#elif PLATFORM == PLATFORM_IOIO0003 || PLATFORM == PLATFORM_IOIO0023
#define HARDWARE HARDWARE_IOIO0003
#elif PLATFORM == PLATFORM_NU32
#define HARDWARE HARDWARE_NU32
#elif PLATFORM == PLATFORM_TPAD
#define HARDWARE = HARDWARE_TPAD
#else
#error Unknown hardware for PLATFORM
#endif


// sanity: assert MCU
#if HARDWARE == HARDWARE_IOIO0000
  #ifndef __PIC24FJ256DA206__
    #error Platform and MCU mismatch - expecting PIC24FJ256DA206
  #endif
#elif HARDWARE == HARDWARE_IOIO0001
  #ifndef __PIC24FJ128DA106__
    #error Platform and MCU mismatch - expecting PIC24FJ128DA106
  #endif
#elif HARDWARE == HARDWARE_IOIO0002
  #ifndef __PIC24FJ128DA206__
    #error Platform and MCU mismatch - expecting PIC24FJ128DA206
  #endif
#elif HARDWARE == HARDWARE_IOIO0003
  #ifndef __PIC24FJ256DA206__
    #error Platform and MCU mismatch - expecting PIC24FJ256DA206
  #endif
#elif HARDWARE == HARDWARE_NU32
  #ifndef __C32MX795F512L__
    #error Platform and MCU mismatch - expecting PIC32MX795F512L
  #endif
#elif HARDWARE == HARDWARE_TPAD
  #ifndef __32MX250F128B__
    #error Platform and MCU mismatch - expecting PIC32MX250F128B
  #endif
#else
  #error Unknown platform
#endif

// number of pins of each platform
#if HARDWARE == HARDWARE_IOIO0000
  #define NUM_PINS 50
#elif HARDWARE >= HARDWARE_IOIO0001 && HARDWARE <= HARDWARE_IOIO0003
  #define NUM_PINS 49
#elif HARDWARE == HARDWARE_NU32
#define NUM_PINS 85 // using # of IO pins; total # pins is 100
#elif HARDWARE == HARDWARE_TPAD
#define NUM_PINS 21 // using # of IO pins; total # pins is 28
#else
  #error Unknown hardware
#endif

#if defined(__PIC24FJ256DA206__) || defined(__PIC24FJ128DA106__) || defined(__PIC24FJ128DA206__)
  #define NUM_PWM_MODULES 9
  #define NUM_UART_MODULES 4
  #define NUM_SPI_MODULES 3
  #define NUM_I2C_MODULES 3
  #define NUM_INCAP_MODULES 9
#elif defined(__32MX795F512L__)
// from the PIC32 datasheet at http://ww1.microchip.com/downloads/en/DeviceDoc/61156G.pdf
  #define NUM_PWM_MODULES 5
  #define NUM_UART_MODULES 6
  #define NUM_SPI_MODULES 4
  #define NUM_I2C_MODULES 5
  #define NUM_INCAP_MODULES 9 // don't know what this is
#elif defined(__32MX250F128B__)
// from the PIC32 datasheet
  #define NUM_PWM_MODULES 5
  #define NUM_UART_MODULES 2
  #define NUM_SPI_MODULES 2
  #define NUM_I2C_MODULES 2
  #define NUM_INCAP_MODULES 9 // don't know what this is
#else
  #error Unknown MCU
#endif

#endif  // __PLATFORM_H__
