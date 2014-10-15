#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Include project Makefile
include Makefile
# Include makefile containing local settings
ifeq "$(wildcard nbproject/Makefile-local-PIC24FJ128DA106.mk)" "nbproject/Makefile-local-PIC24FJ128DA106.mk"
include nbproject/Makefile-local-PIC24FJ128DA106.mk
endif

# Environment
MKDIR=gnumkdir -p
RM=rm -f 
MV=mv 
CP=cp 

# Macros
CND_CONF=PIC24FJ128DA106
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
	${MAKE} ${MAKE_OPTIONS} -f nbproject/Makefile-PIC24FJ128DA106.mk dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}

MP_PROCESSOR_OPTION=24FJ128DA106
MP_LINKER_FILE_OPTION=,-Tp24FJ128DA106.gld
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
	@${RM} ${OBJECTDIR}/src/btstack_memory.o.ok ${OBJECTDIR}/src/btstack_memory.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/btstack_memory.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/btstack_memory.o.d" -o ${OBJECTDIR}/src/btstack_memory.o src/btstack_memory.c    
	
${OBJECTDIR}/src/hci.o: src/hci.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci.o.d 
	@${RM} ${OBJECTDIR}/src/hci.o.ok ${OBJECTDIR}/src/hci.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci.o.d" -o ${OBJECTDIR}/src/hci.o src/hci.c    
	
${OBJECTDIR}/src/hci_cmds.o: src/hci_cmds.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_cmds.o.d 
	@${RM} ${OBJECTDIR}/src/hci_cmds.o.ok ${OBJECTDIR}/src/hci_cmds.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_cmds.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci_cmds.o.d" -o ${OBJECTDIR}/src/hci_cmds.o src/hci_cmds.c    
	
${OBJECTDIR}/src/hci_dump.o: src/hci_dump.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_dump.o.d 
	@${RM} ${OBJECTDIR}/src/hci_dump.o.ok ${OBJECTDIR}/src/hci_dump.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_dump.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci_dump.o.d" -o ${OBJECTDIR}/src/hci_dump.o src/hci_dump.c    
	
${OBJECTDIR}/src/hci_transport_mchpusb.o: src/hci_transport_mchpusb.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_transport_mchpusb.o.d 
	@${RM} ${OBJECTDIR}/src/hci_transport_mchpusb.o.ok ${OBJECTDIR}/src/hci_transport_mchpusb.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" -o ${OBJECTDIR}/src/hci_transport_mchpusb.o src/hci_transport_mchpusb.c    
	
${OBJECTDIR}/src/l2cap.o: src/l2cap.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap.o.d 
	@${RM} ${OBJECTDIR}/src/l2cap.o.ok ${OBJECTDIR}/src/l2cap.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/l2cap.o.d" -o ${OBJECTDIR}/src/l2cap.o src/l2cap.c    
	
${OBJECTDIR}/src/l2cap_signaling.o: src/l2cap_signaling.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap_signaling.o.d 
	@${RM} ${OBJECTDIR}/src/l2cap_signaling.o.ok ${OBJECTDIR}/src/l2cap_signaling.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap_signaling.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/l2cap_signaling.o.d" -o ${OBJECTDIR}/src/l2cap_signaling.o src/l2cap_signaling.c    
	
${OBJECTDIR}/src/linked_list.o: src/linked_list.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/linked_list.o.d 
	@${RM} ${OBJECTDIR}/src/linked_list.o.ok ${OBJECTDIR}/src/linked_list.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/linked_list.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/linked_list.o.d" -o ${OBJECTDIR}/src/linked_list.o src/linked_list.c    
	
${OBJECTDIR}/src/memory_pool.o: src/memory_pool.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/memory_pool.o.d 
	@${RM} ${OBJECTDIR}/src/memory_pool.o.ok ${OBJECTDIR}/src/memory_pool.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/memory_pool.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/memory_pool.o.d" -o ${OBJECTDIR}/src/memory_pool.o src/memory_pool.c    
	
${OBJECTDIR}/src/rfcomm.o: src/rfcomm.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/rfcomm.o.d 
	@${RM} ${OBJECTDIR}/src/rfcomm.o.ok ${OBJECTDIR}/src/rfcomm.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/rfcomm.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/rfcomm.o.d" -o ${OBJECTDIR}/src/rfcomm.o src/rfcomm.c    
	
${OBJECTDIR}/src/run_loop.o: src/run_loop.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/run_loop.o.d 
	@${RM} ${OBJECTDIR}/src/run_loop.o.ok ${OBJECTDIR}/src/run_loop.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/run_loop.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/run_loop.o.d" -o ${OBJECTDIR}/src/run_loop.o src/run_loop.c    
	
${OBJECTDIR}/src/sdp.o: src/sdp.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp.o.d 
	@${RM} ${OBJECTDIR}/src/sdp.o.ok ${OBJECTDIR}/src/sdp.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/sdp.o.d" -o ${OBJECTDIR}/src/sdp.o src/sdp.c    
	
${OBJECTDIR}/src/sdp_util.o: src/sdp_util.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp_util.o.d 
	@${RM} ${OBJECTDIR}/src/sdp_util.o.ok ${OBJECTDIR}/src/sdp_util.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp_util.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/sdp_util.o.d" -o ${OBJECTDIR}/src/sdp_util.o src/sdp_util.c    
	
${OBJECTDIR}/src/utils.o: src/utils.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/utils.o.d 
	@${RM} ${OBJECTDIR}/src/utils.o.ok ${OBJECTDIR}/src/utils.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/utils.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/utils.o.d" -o ${OBJECTDIR}/src/utils.o src/utils.c    
	
else
${OBJECTDIR}/src/btstack_memory.o: src/btstack_memory.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/btstack_memory.o.d 
	@${RM} ${OBJECTDIR}/src/btstack_memory.o.ok ${OBJECTDIR}/src/btstack_memory.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/btstack_memory.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/btstack_memory.o.d" -o ${OBJECTDIR}/src/btstack_memory.o src/btstack_memory.c    
	
${OBJECTDIR}/src/hci.o: src/hci.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci.o.d 
	@${RM} ${OBJECTDIR}/src/hci.o.ok ${OBJECTDIR}/src/hci.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci.o.d" -o ${OBJECTDIR}/src/hci.o src/hci.c    
	
${OBJECTDIR}/src/hci_cmds.o: src/hci_cmds.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_cmds.o.d 
	@${RM} ${OBJECTDIR}/src/hci_cmds.o.ok ${OBJECTDIR}/src/hci_cmds.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_cmds.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci_cmds.o.d" -o ${OBJECTDIR}/src/hci_cmds.o src/hci_cmds.c    
	
${OBJECTDIR}/src/hci_dump.o: src/hci_dump.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_dump.o.d 
	@${RM} ${OBJECTDIR}/src/hci_dump.o.ok ${OBJECTDIR}/src/hci_dump.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_dump.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci_dump.o.d" -o ${OBJECTDIR}/src/hci_dump.o src/hci_dump.c    
	
${OBJECTDIR}/src/hci_transport_mchpusb.o: src/hci_transport_mchpusb.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/hci_transport_mchpusb.o.d 
	@${RM} ${OBJECTDIR}/src/hci_transport_mchpusb.o.ok ${OBJECTDIR}/src/hci_transport_mchpusb.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/hci_transport_mchpusb.o.d" -o ${OBJECTDIR}/src/hci_transport_mchpusb.o src/hci_transport_mchpusb.c    
	
${OBJECTDIR}/src/l2cap.o: src/l2cap.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap.o.d 
	@${RM} ${OBJECTDIR}/src/l2cap.o.ok ${OBJECTDIR}/src/l2cap.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/l2cap.o.d" -o ${OBJECTDIR}/src/l2cap.o src/l2cap.c    
	
${OBJECTDIR}/src/l2cap_signaling.o: src/l2cap_signaling.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/l2cap_signaling.o.d 
	@${RM} ${OBJECTDIR}/src/l2cap_signaling.o.ok ${OBJECTDIR}/src/l2cap_signaling.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/l2cap_signaling.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/l2cap_signaling.o.d" -o ${OBJECTDIR}/src/l2cap_signaling.o src/l2cap_signaling.c    
	
${OBJECTDIR}/src/linked_list.o: src/linked_list.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/linked_list.o.d 
	@${RM} ${OBJECTDIR}/src/linked_list.o.ok ${OBJECTDIR}/src/linked_list.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/linked_list.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/linked_list.o.d" -o ${OBJECTDIR}/src/linked_list.o src/linked_list.c    
	
${OBJECTDIR}/src/memory_pool.o: src/memory_pool.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/memory_pool.o.d 
	@${RM} ${OBJECTDIR}/src/memory_pool.o.ok ${OBJECTDIR}/src/memory_pool.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/memory_pool.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/memory_pool.o.d" -o ${OBJECTDIR}/src/memory_pool.o src/memory_pool.c    
	
${OBJECTDIR}/src/rfcomm.o: src/rfcomm.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/rfcomm.o.d 
	@${RM} ${OBJECTDIR}/src/rfcomm.o.ok ${OBJECTDIR}/src/rfcomm.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/rfcomm.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/rfcomm.o.d" -o ${OBJECTDIR}/src/rfcomm.o src/rfcomm.c    
	
${OBJECTDIR}/src/run_loop.o: src/run_loop.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/run_loop.o.d 
	@${RM} ${OBJECTDIR}/src/run_loop.o.ok ${OBJECTDIR}/src/run_loop.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/run_loop.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/run_loop.o.d" -o ${OBJECTDIR}/src/run_loop.o src/run_loop.c    
	
${OBJECTDIR}/src/sdp.o: src/sdp.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp.o.d 
	@${RM} ${OBJECTDIR}/src/sdp.o.ok ${OBJECTDIR}/src/sdp.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/sdp.o.d" -o ${OBJECTDIR}/src/sdp.o src/sdp.c    
	
${OBJECTDIR}/src/sdp_util.o: src/sdp_util.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/sdp_util.o.d 
	@${RM} ${OBJECTDIR}/src/sdp_util.o.ok ${OBJECTDIR}/src/sdp_util.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/sdp_util.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/sdp_util.o.d" -o ${OBJECTDIR}/src/sdp_util.o src/sdp_util.c    
	
${OBJECTDIR}/src/utils.o: src/utils.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/src 
	@${RM} ${OBJECTDIR}/src/utils.o.d 
	@${RM} ${OBJECTDIR}/src/utils.o.ok ${OBJECTDIR}/src/utils.o.err 
	@${FIXDEPS} "${OBJECTDIR}/src/utils.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"./include" -I"../common" -I"../libusb" -I"../microchip/include" -mlarge-data -mconst-in-code -Os -MMD -MF "${OBJECTDIR}/src/utils.o.d" -o ${OBJECTDIR}/src/utils.o src/utils.c    
	
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: archive
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} -omf=coff $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
else
dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} -omf=coff $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libbtstack.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
endif


# Subprojects
.build-subprojects:


# Subprojects
.clean-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r build/PIC24FJ128DA106
	${RM} -r dist/PIC24FJ128DA106

# Enable dependency checking
.dep.inc: .depcheck-impl

DEPFILES=$(shell mplabwildcard ${POSSIBLE_DEPFILES})
ifneq (${DEPFILES},)
include ${DEPFILES}
endif
