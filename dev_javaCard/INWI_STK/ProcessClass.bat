echo start convert

@echo off
setlocal
set BASE_DIR=%~dp0
set JC_KIT=C:\LogosJavaCardDevEnv\javaCardLibraries\javaCardKit
REM snippet below purpose is to get current folder name which is also the project name
for %%A in ("%~f0\..") do set "PROJECTNAME=%%~nxA"
echo projectname is %PROJECTNAME%


set JC_LIB=C:\LogosJavaCardDevEnv\javaCardLibraries
set JAVA_HOME=%JC_LIB%\javaHome\jdk1.6.0_02

set JC_212_HOME=%JC_KIT%\java_card_kit-2_1_2
set JC_221_HOME=%JC_KIT%\java_card_kit-2_2_1
set JC_222_HOME=%JC_KIT%\java_card_kit-2_2_2
set JC_302_HOME=%JC_KIT%\JCDK3.0.2_ClassicEdition
set JC_304_HOME=%JC_KIT%\JCDK3.0.4_ClassicEdition

set CONVERTER_CLASSNAME=com.sun.javacard.converter.Converter

set SELECT_CONV=222
    echo *****************************************************
IF %SELECT_CONV%==212 (
	echo *select converter 2.1.2                             *
	set JC_CLASSPATH="%JC_212_HOME%\lib\*;%JC_CLASSPATH%"
	set JC_CLASSIC_HOME=%JC_212_HOME%	
)ELSE IF %SELECT_CONV%==221 (
	echo *select converter 2.2.1 
	set JC_CLASSIC_HOME=%JC_221_HOME%                        *
	set JC_CLASSPATH="%JC_221_HOME%\lib\*;%JC_CLASSPATH%"
)ELSE IF %SELECT_CONV%==222 (
	echo *select converter 2.2.2                             *
	set JC_CLASSPATH="%JC_222_HOME%\lib\*;%JC_CLASSPATH%"
	set JC_CLASSIC_HOME=%JC_222_HOME%
)ELSE IF %SELECT_CONV%==302 (
	echo *select converter 3.0.2                             *
	set JC_CLASSPATH="%JC_302_HOME%\lib\*;%JC_CLASSPATH%"
	set JC_CLASSIC_HOME=%JC_302_HOME%
	set CONVERTER_CLASSNAME=com.sun.javacard.converter.Main
)ELSE IF %SELECT_CONV%==304 (
	echo *select converter 3.0.4                             *
	set JC_CLASSPATH="%JC_304_HOME%\lib\*;%JC_CLASSPATH%"
	set JC_CLASSIC_HOME=%JC_302_HOME%
	set JAVA_HOME=%JC_LIB%\javaHome\jre7
	set CONVERTER_CLASSNAME=com.sun.javacard.converter.Main

)ELSE (
	echo *                      Error                        *	
	echo Please select converter in batch file %~f0 at line 23
	echo *****************************************************
	goto End
)
    echo *****************************************************



echo on

"%JAVA_HOME%\bin\java"   -classpath "%JC_CLASSPATH%" %CONVERTER_CLASSNAME% -config "%BASE_DIR%%PROJECTNAME%.opt"
@echo off
if ERRORLEVEL 1 goto ErrorConversion

echo Generate APDU script
call "%JC_LIB%\lopm\lopt" -script="%BASE_DIR%%PROJECTNAME%.lopm.txt" F -filename="%BASE_DIR%%PROJECTNAME%.lopm"
if ERRORLEVEL 1 goto ErrorLOPT
goto End

:ErrorConversion
echo Error during conversion
goto End

:ErrorLOPT
echo Error while generating lopm file
goto End

@echo %date%  %time%



:End
@echo %date%  %time%
java -jar SetAppletVersion.jar -source "./bin"
endlocal
exit
