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
	${MAKE} ${MAKE_OPTIONS} -f nbproject/Makefile-PIC24FJ128DA106.mk dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}

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
${OBJECTDIR}/accessory.o: accessory.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/accessory.o.d 
	@${RM} ${OBJECTDIR}/accessory.o.ok ${OBJECTDIR}/accessory.o.err 
	@${FIXDEPS} "${OBJECTDIR}/accessory.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"../common" -I"../microchip/include" -I"../libusb" -I"../libbtstack" -I"../libbtstack/include" -I"../libbtstack/src" -I"../libadb" -mlarge-data -mconst-in-data -Os -MMD -MF "${OBJECTDIR}/accessory.o.d" -o ${OBJECTDIR}/accessory.o accessory.c    
	
${OBJECTDIR}/bt_app.o: bt_app.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/bt_app.o.d 
	@${RM} ${OBJECTDIR}/bt_app.o.ok ${OBJECTDIR}/bt_app.o.err 
	@${FIXDEPS} "${OBJECTDIR}/bt_app.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"../common" -I"../microchip/include" -I"../libusb" -I"../libbtstack" -I"../libbtstack/include" -I"../libbtstack/src" -I"../libadb" -mlarge-data -mconst-in-data -Os -MMD -MF "${OBJECTDIR}/bt_app.o.d" -o ${OBJECTDIR}/bt_app.o bt_app.c    
	
${OBJECTDIR}/connection.o: connection.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/connection.o.d 
	@${RM} ${OBJECTDIR}/connection.o.ok ${OBJECTDIR}/connection.o.err 
	@${FIXDEPS} "${OBJECTDIR}/connection.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -D__MPLAB_DEBUGGER_PK3=1 -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"../common" -I"../microchip/include" -I"../libusb" -I"../libbtstack" -I"../libbtstack/include" -I"../libbtstack/src" -I"../libadb" -mlarge-data -mconst-in-data -Os -MMD -MF "${OBJECTDIR}/connection.o.d" -o ${OBJECTDIR}/connection.o connection.c    
	
else
${OBJECTDIR}/accessory.o: accessory.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/accessory.o.d 
	@${RM} ${OBJECTDIR}/accessory.o.ok ${OBJECTDIR}/accessory.o.err 
	@${FIXDEPS} "${OBJECTDIR}/accessory.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"../common" -I"../microchip/include" -I"../libusb" -I"../libbtstack" -I"../libbtstack/include" -I"../libbtstack/src" -I"../libadb" -mlarge-data -mconst-in-data -Os -MMD -MF "${OBJECTDIR}/accessory.o.d" -o ${OBJECTDIR}/accessory.o accessory.c    
	
${OBJECTDIR}/bt_app.o: bt_app.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/bt_app.o.d 
	@${RM} ${OBJECTDIR}/bt_app.o.ok ${OBJECTDIR}/bt_app.o.err 
	@${FIXDEPS} "${OBJECTDIR}/bt_app.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"../common" -I"../microchip/include" -I"../libusb" -I"../libbtstack" -I"../libbtstack/include" -I"../libbtstack/src" -I"../libadb" -mlarge-data -mconst-in-data -Os -MMD -MF "${OBJECTDIR}/bt_app.o.d" -o ${OBJECTDIR}/bt_app.o bt_app.c    
	
${OBJECTDIR}/connection.o: connection.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} ${OBJECTDIR} 
	@${RM} ${OBJECTDIR}/connection.o.d 
	@${RM} ${OBJECTDIR}/connection.o.ok ${OBJECTDIR}/connection.o.err 
	@${FIXDEPS} "${OBJECTDIR}/connection.o.d" $(SILENT) -c ${MP_CC} $(MP_EXTRA_CC_PRE)  -g -omf=coff -x c -c -mcpu=$(MP_PROCESSOR_OPTION) -ffunction-sections -Wall -DNDEBUG -I"." -I"../common" -I"../microchip/include" -I"../libusb" -I"../libbtstack" -I"../libbtstack/include" -I"../libbtstack/src" -I"../libadb" -mlarge-data -mconst-in-data -Os -MMD -MF "${OBJECTDIR}/connection.o.d" -o ${OBJECTDIR}/connection.o connection.c    
	
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: archive
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} -omf=coff $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
else
dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk   
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_AR} -omf=coff $(MP_EXTRA_AR_PRE) r dist/${CND_CONF}/${IMAGE_TYPE}/libconn.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}    
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
