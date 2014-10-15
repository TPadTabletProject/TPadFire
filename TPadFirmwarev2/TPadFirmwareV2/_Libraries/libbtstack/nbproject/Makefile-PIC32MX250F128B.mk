#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Include project Makefile
ifeq "${IGNORE_LOCAL}" "TRUE"
# do not include local makefile. User is passing all local related variables already
else
include Makefile
# Include makefile containing local settings
ifeq "$(wildcard nbproject/Makefile-local-PIC32MX250F128B.mk)" "nbproject/Makefile-local-PIC32MX250F128B.mk"
include nbproject/Makefile-local-PIC32MX250F128B.mk
endif
endif

# Environment
MKDIR=gnumkdir -p
RM=rm -f 
MV=mv 
CP=cp 

# Macros
CND_CONF=PIC32MX250F128B
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
IMAGE_TYPE=debug
OUTPUT_SUFFIX=a
DEBUGGABLE_SUFFIX=
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}
else
IMAGE_TYPE=production
OUTPUT_SUFFIX=a
DEBUGGABLE_SUFFIX=
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}
endif

# Object Directory
OBJECTDIR=build/${CND_CONF}/${IMAGE_TYPE}

# Distribution Directory
DISTDIR=dist/${CND_CONF}/${IMAGE_TYPE}

# Object Files Quoted if spaced
OBJECTFILES_QUOTED_IF_SPACED=${OBJECTDIR}/src/btstack_memory.o ${OBJECTDIR}/src/hci.o ${OBJECTDIR}/src/hci_cmds.o ${OBJECTDIR}/src/hci_dump.o ${OBJECTDIR}/src/hci_transport_mchpusb.o ${OBJECTDIR}/src/l2cap.o ${OBJECTDIR}/src/l2cap_signaling.o ${OBJECTDIR}/src/linked_list.o ${OBJECTDIR}/src/memory_pool.o ${OBJECTDIR}/src/rfcomm.o ${OBJECTDIR}/src/run_loop.o ${OBJECTDIR}/src/sdp.o ${OBJECTDIR}/src/sdp_util.o ${OBJECTDIR}/src/utils.o
POSSIBLE_DEPFILES=${OBJECTDIR}/src/btstack_memory.o.d ${OBJECTDIR}/src/hci.o.d ${OBJECTDIR}/src/hci_cmds.o.d ${OBJECTDIR}/src/hci_dump.o.d ${OBJECTDIR}/src/hci_transport_mchpusb.o.d ${OBJECTDIR}/src/l2cap.o.d ${OBJECTDIR}/src/l2cap_signaling.o.d ${OBJECTDIR}/src/linked_list.o.d ${OBJECTDIR}/src/memory_pool.o.d ${OBJECTDIR}/src/rfcomm.o.d ${OBJECTDIR}/src/run_loop.o.d ${OBJECTDIR}/src/sdp.o.d ${OBJECTDIR}/src/sdp_util.o.d ${OBJECTDIR}/src/utils.o.d

# Object Files
OBJECTFILES=${OBJECTDIR}/src/btstack_memory.o ${OBJECTDIR}/src/hci.o ${OBJECTDIR}/src/hci_cmds.o ${OBJECTDIR}/src/hci_dump.o ${OBJECTDIR}/src/hci_transport_mchpusb.o ${OBJECTDIR}/src/l2cap.o ${OBJECTDIR}/src/l2cap_signaling.o ${OBJECTDIR}/src/linked_list.o ${OBJECTDIR}/src/memory_pool.o ${OBJECTDIR}/src/rfcomm.o ${OBJECTDIR}/src/run_loop.o ${OBJECTDIR}/src/sdp.o ${OBJECTDIR}/src/sdp_util.o ${OBJECTDIR}/src/utils.o


CFLAGS=
ASFLAGS=
LDLIBSOPTIONS=

############# Tool locations ##########################################
# If you copy a project from one host to another, the path where the  #
# compiler is installed may be different.                             #
# If you open this project with MPLAB X in the new host, this         #
# makefile will be regenerated and the paths will be corrected.       #
#######################################################################
# fixDeps replaces a bunch of sed/cat/printf statements that slow down the build
FIXDEPS=fixDeps

.build-conf:  ${BUILD_SUBPROJECTS}
	${MAKE} ${MAKE_OPTIONS} -f nbproject/Makefile-PIC32MX250F128B.mk dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}

MP_PROCESSOR_OPTION=32MX250F128B
MP_LINKER_FILE_OPTION=
# ------------------------------------------------------------------------------------
# Rules for buildStep: assemble
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
else
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: assembleWithPreprocess
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
else
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: compile
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
${OBJECTDIR}/src/btstack_memory.o: src/btstack_memory.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/btstack_memory.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/btstack_memory.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/btstack_memory.o.d" -o ${OBJECTDIR}/src/btstack_memory.o src/btstack_memory.c  
	
${OBJECTDIR}/src/hci.o: src/hci.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci.o.d" -o ${OBJECTDIR}/src/hci.o src/hci.c  
	
${OBJECTDIR}/src/hci_cmds.o: src/hci_cmds.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_cmds.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_cmds.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci_cmds.o.d" -o ${OBJECTDIR}/src/hci_cmds.o src/hci_cmds.c  
	
${OBJECTDIR}/src/hci_dump.o: src/hci_dump.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_dump.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_dump.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci_dump.o.d" -o ${OBJECTDIR}/src/hci_dump.o src/hci_dump.c  
	
${OBJECTDIR}/src/hci_transport_mchpusb.o: src/hci_transport_mchpusb.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_transport_mchpusb.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" -o ${OBJECTDIR}/src/hci_transport_mchpusb.o src/hci_transport_mchpusb.c  
	
${OBJECTDIR}/src/l2cap.o: src/l2cap.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/l2cap.o.d" -o ${OBJECTDIR}/src/l2cap.o src/l2cap.c  
	
${OBJECTDIR}/src/l2cap_signaling.o: src/l2cap_signaling.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap_signaling.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap_signaling.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/l2cap_signaling.o.d" -o ${OBJECTDIR}/src/l2cap_signaling.o src/l2cap_signaling.c  
	
${OBJECTDIR}/src/linked_list.o: src/linked_list.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/linked_list.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/linked_list.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/linked_list.o.d" -o ${OBJECTDIR}/src/linked_list.o src/linked_list.c  
	
${OBJECTDIR}/src/memory_pool.o: src/memory_pool.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/memory_pool.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/memory_pool.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/memory_pool.o.d" -o ${OBJECTDIR}/src/memory_pool.o src/memory_pool.c  
	
${OBJECTDIR}/src/rfcomm.o: src/rfcomm.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/rfcomm.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/rfcomm.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/rfcomm.o.d" -o ${OBJECTDIR}/src/rfcomm.o src/rfcomm.c  
	
${OBJECTDIR}/src/run_loop.o: src/run_loop.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/run_loop.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/run_loop.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/run_loop.o.d" -o ${OBJECTDIR}/src/run_loop.o src/run_loop.c  
	
${OBJECTDIR}/src/sdp.o: src/sdp.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/sdp.o.d" -o ${OBJECTDIR}/src/sdp.o src/sdp.c  
	
${OBJECTDIR}/src/sdp_util.o: src/sdp_util.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp_util.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp_util.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/sdp_util.o.d" -o ${OBJECTDIR}/src/sdp_util.o src/sdp_util.c  
	
${OBJECTDIR}/src/utils.o: src/utils.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/utils.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/utils.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/utils.o.d" -o ${OBJECTDIR}/src/utils.o src/utils.c  
	
else
${OBJECTDIR}/src/btstack_memory.o: src/btstack_memory.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/btstack_memory.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/btstack_memory.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/btstack_memory.o.d" -o ${OBJECTDIR}/src/btstack_memory.o src/btstack_memory.c  
	
${OBJECTDIR}/src/hci.o: src/hci.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci.o.d" -o ${OBJECTDIR}/src/hci.o src/hci.c  
	
${OBJECTDIR}/src/hci_cmds.o: src/hci_cmds.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_cmds.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_cmds.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci_cmds.o.d" -o ${OBJECTDIR}/src/hci_cmds.o src/hci_cmds.c  
	
${OBJECTDIR}/src/hci_dump.o: src/hci_dump.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_dump.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_dump.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci_dump.o.d" -o ${OBJECTDIR}/src/hci_dump.o src/hci_dump.c  
	
${OBJECTDIR}/src/hci_transport_mchpusb.o: src/hci_transport_mchpusb.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_transport_mchpusb.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" -o ${OBJECTDIR}/src/hci_transport_mchpusb.o src/hci_transport_mchpusb.c  
	
${OBJECTDIR}/src/l2cap.o: src/l2cap.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/l2cap.o.d" -o ${OBJECTDIR}/src/l2cap.o src/l2cap.c  
	
${OBJECTDIR}/src/l2cap_signaling.o: src/l2cap_signaling.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap_signaling.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap_signaling.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/l2cap_signaling.o.d" -o ${OBJECTDIR}/src/l2cap_signaling.o src/l2cap_signaling.c  
	
${OBJECTDIR}/src/linked_list.o: src/linked_list.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/linked_list.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/linked_list.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/linked_list.o.d" -o ${OBJECTDIR}/src/linked_list.o src/linked_list.c  
	
${OBJECTDIR}/src/memory_pool.o: src/memory_pool.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/memory_pool.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/memory_pool.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/memory_pool.o.d" -o ${OBJECTDIR}/src/memory_pool.o src/memory_pool.c  
	
${OBJECTDIR}/src/rfcomm.o: src/rfcomm.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/rfcomm.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/rfcomm.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/rfcomm.o.d" -o ${OBJECTDIR}/src/rfcomm.o src/rfcomm.c  
	
${OBJECTDIR}/src/run_loop.o: src/run_loop.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/run_loop.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/run_loop.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/run_loop.o.d" -o ${OBJECTDIR}/src/run_loop.o src/run_loop.c  
	
${OBJECTDIR}/src/sdp.o: src/sdp.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/sdp.o.d" -o ${OBJECTDIR}/src/sdp.o src/sdp.c  
	
${OBJECTDIR}/src/sdp_util.o: src/sdp_util.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp_util.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp_util.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/sdp_util.o.d" -o ${OBJECTDIR}/src/sdp_util.o src/sdp_util.c  
	
${OBJECTDIR}/src/utils.o: src/utils.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/utils.o.d 
	@${FIXDEPS} "${OBJECTDIR}/src/utils.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../libadb" -I"../libconn" -I"../libusb" -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../microchip/include/USB" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -MMD -MF "${OBJECTDIR}/src/utils.o.d" -o ${OBJECTDIR}/src/utils.o src/utils.c  
	
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: archive
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk    
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
else
dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
endif


# Subprojects
.build-subprojects:


# Subprojects
.clean-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r build/PIC32MX250F128B
	${RM} -r dist/PIC32MX250F128B

# Enable dependency checking
.dep.inc: .depcheck-impl

DEPFILES=$(shell mplabwildcard ${POSSIBLE_DEPFILES})
ifneq (${DEPFILES},)
include ${DEPFILES}
endif
