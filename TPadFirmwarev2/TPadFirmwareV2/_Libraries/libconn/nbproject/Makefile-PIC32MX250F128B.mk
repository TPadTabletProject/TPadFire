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
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}
else
IMAGE_TYPE=production
OUTPUT_SUFFIX=a
DEBUGGABLE_SUFFIX=
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}
endif

# Object Directory
OBJECTDIR=build/${CND_CONF}/${IMAGE_TYPE}

# Distribution Directory
DISTDIR=dist/${CND_CONF}/${IMAGE_TYPE}

# Object Files Quoted if spaced
OBJECTFILES_QUOTED_IF_SPACED=${OBJECTDIR}/accessory.o ${OBJECTDIR}/bt_app.o ${OBJECTDIR}/connection.o
POSSIBLE_DEPFILES=${OBJECTDIR}/accessory.o.d ${OBJECTDIR}/bt_app.o.d ${OBJECTDIR}/connection.o.d

# Object Files
OBJECTFILES=${OBJECTDIR}/accessory.o ${OBJECTDIR}/bt_app.o ${OBJECTDIR}/connection.o


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
	${MAKE} ${MAKE_OPTIONS} -f nbproject/Makefile-PIC32MX250F128B.mk dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}

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
${OBJECTDIR}/accessory.o: accessory.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/accessory.o.d 
	@${FIXDEPS} "${OBJECTDIR}/accessory.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../libusb" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -I"../libadb" -I"../libconn" -MMD -MF "${OBJECTDIR}/accessory.o.d" -o ${OBJECTDIR}/accessory.o accessory.c  
	
${OBJECTDIR}/bt_app.o: bt_app.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/bt_app.o.d 
	@${FIXDEPS} "${OBJECTDIR}/bt_app.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../libusb" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -I"../libadb" -I"../libconn" -MMD -MF "${OBJECTDIR}/bt_app.o.d" -o ${OBJECTDIR}/bt_app.o bt_app.c  
	
${OBJECTDIR}/connection.o: connection.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/connection.o.d 
	@${FIXDEPS} "${OBJECTDIR}/connection.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_ICD3=1 -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../libusb" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -I"../libadb" -I"../libconn" -MMD -MF "${OBJECTDIR}/connection.o.d" -o ${OBJECTDIR}/connection.o connection.c  
	
else
${OBJECTDIR}/accessory.o: accessory.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/accessory.o.d 
	@${FIXDEPS} "${OBJECTDIR}/accessory.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../libusb" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -I"../libadb" -I"../libconn" -MMD -MF "${OBJECTDIR}/accessory.o.d" -o ${OBJECTDIR}/accessory.o accessory.c  
	
${OBJECTDIR}/bt_app.o: bt_app.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/bt_app.o.d 
	@${FIXDEPS} "${OBJECTDIR}/bt_app.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../libusb" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -I"../libadb" -I"../libconn" -MMD -MF "${OBJECTDIR}/bt_app.o.d" -o ${OBJECTDIR}/bt_app.o bt_app.c  
	
${OBJECTDIR}/connection.o: connection.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/connection.o.d 
	@${FIXDEPS} "${OBJECTDIR}/connection.o.d" $(SILENT) -rsi ${MP_CC_DIR}../ -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"../common" -I"../microchip/common" -I"../microchip/include" -I"../microchip/usb" -I"../libusb" -I"../libbtstack/src" -I"../libbtstack/include/btstack" -I"../libbtstack" -I"../libadb" -I"../libconn" -MMD -MF "${OBJECTDIR}/connection.o.d" -o ${OBJECTDIR}/connection.o connection.c  
	
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: archive
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk    
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
else
dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
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
