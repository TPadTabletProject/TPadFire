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
ifeq "$(wildcard nbproject/Makefile-local-PIC32MX795F512L.mk)" "nbproject/Makefile-local-PIC32MX795F512L.mk"
include nbproject/Makefile-local-PIC32MX795F512L.mk
endif
endif

# Environment
MKDIR=gnumkdir -p
RM=rm -f 
MV=mv 
CP=cp 

# Macros
CND_CONF=PIC32MX795F512L
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
IMAGE_TYPE=debug
OUTPUT_SUFFIX=a
DEBUGGABLE_SUFFIX=
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/libusb.${OUTPUT_SUFFIX}
else
IMAGE_TYPE=production
OUTPUT_SUFFIX=a
DEBUGGABLE_SUFFIX=
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/libusb.${OUTPUT_SUFFIX}
endif

# Object Directory
OBJECTDIR=build/${CND_CONF}/${IMAGE_TYPE}

# Distribution Directory
DISTDIR=dist/${CND_CONF}/${IMAGE_TYPE}

# Object Files Quoted if spaced
OBJECTFILES_QUOTED_IF_SPACED=${OBJECTDIR}/usb_config.o ${OBJECTDIR}/_ext/455219722/usb_host.o ${OBJECTDIR}/usb_host_android.o ${OBJECTDIR}/usb_host_bluetooth.o
POSSIBLE_DEPFILES=${OBJECTDIR}/usb_config.o.d ${OBJECTDIR}/_ext/455219722/usb_host.o.d ${OBJECTDIR}/usb_host_android.o.d ${OBJECTDIR}/usb_host_bluetooth.o.d

# Object Files
OBJECTFILES=${OBJECTDIR}/usb_config.o ${OBJECTDIR}/_ext/455219722/usb_host.o ${OBJECTDIR}/usb_host_android.o ${OBJECTDIR}/usb_host_bluetooth.o


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
	${MAKE} ${MAKE_OPTIONS} -f nbproject/Makefile-PIC32MX795F512L.mk dist/${CND_CONF}/${IMAGE_TYPE}/libusb.${OUTPUT_SUFFIX}

MP_PROCESSOR_OPTION=32MX795F512L
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
${OBJECTDIR}/usb_config.o: usb_config.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/usb_config.o.d 
	@${FIXDEPS} "${OBJECTDIR}/usb_config.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/usb_config.o.d" -o ${OBJECTDIR}/usb_config.o usb_config.c  
	
${OBJECTDIR}/_ext/455219722/usb_host.o: ../microchip/usb/usb_host.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/_ext/455219722 
	@${RM} ${OBJECTDIR}/_ext/455219722/usb_host.o.d 
	@${FIXDEPS} "${OBJECTDIR}/_ext/455219722/usb_host.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/_ext/455219722/usb_host.o.d" -o ${OBJECTDIR}/_ext/455219722/usb_host.o ../microchip/usb/usb_host.c  
	
${OBJECTDIR}/usb_host_android.o: usb_host_android.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/usb_host_android.o.d 
	@${FIXDEPS} "${OBJECTDIR}/usb_host_android.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/usb_host_android.o.d" -o ${OBJECTDIR}/usb_host_android.o usb_host_android.c  
	
${OBJECTDIR}/usb_host_bluetooth.o: usb_host_bluetooth.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/usb_host_bluetooth.o.d 
	@${FIXDEPS} "${OBJECTDIR}/usb_host_bluetooth.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/usb_host_bluetooth.o.d" -o ${OBJECTDIR}/usb_host_bluetooth.o usb_host_bluetooth.c  
	
else
${OBJECTDIR}/usb_config.o: usb_config.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/usb_config.o.d 
	@${FIXDEPS} "${OBJECTDIR}/usb_config.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/usb_config.o.d" -o ${OBJECTDIR}/usb_config.o usb_config.c  
	
${OBJECTDIR}/_ext/455219722/usb_host.o: ../microchip/usb/usb_host.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR}/_ext/455219722 
	@${RM} ${OBJECTDIR}/_ext/455219722/usb_host.o.d 
	@${FIXDEPS} "${OBJECTDIR}/_ext/455219722/usb_host.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/_ext/455219722/usb_host.o.d" -o ${OBJECTDIR}/_ext/455219722/usb_host.o ../microchip/usb/usb_host.c  
	
${OBJECTDIR}/usb_host_android.o: usb_host_android.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/usb_host_android.o.d 
	@${FIXDEPS} "${OBJECTDIR}/usb_host_android.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/usb_host_android.o.d" -o ${OBJECTDIR}/usb_host_android.o usb_host_android.c  
	
${OBJECTDIR}/usb_host_bluetooth.o: usb_host_bluetooth.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/usb_host_bluetooth.o.d 
	@${FIXDEPS} "${OBJECTDIR}/usb_host_bluetooth.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../microchip/include" -I"../common" -I"../microchip/common" -I"../microchip/usb" -I"../libusb" -MMD -MF "${OBJECTDIR}/usb_host_bluetooth.o.d" -o ${OBJECTDIR}/usb_host_bluetooth.o usb_host_bluetooth.c  
	
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: archive
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
dist/${CND_CONF}/${IMAGE_TYPE}/libusb.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk    
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libusb.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
else
dist/${CND_CONF}/${IMAGE_TYPE}/libusb.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libusb.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
endif


# Subprojects
.build-subprojects:


# Subprojects
.clean-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r build/PIC32MX795F512L
	${RM} -r dist/PIC32MX795F512L

# Enable dependency checking
.dep.inc: .depcheck-impl

DEPFILES=$(shell mplabwildcard ${POSSIBLE_DEPFILES})
ifneq (${DEPFILES},)
include ${DEPFILES}
endif
