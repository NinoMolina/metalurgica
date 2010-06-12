echo off
REM ***************************************************
REM * Batch file for DAO-Generator execution
REM ***************************************************

REM **************************************************
REM **************************************************
REM * JAVA_HOME : provide the path of java home folder
REM * 		  in the following command line	
REM **************************************************
REM **************************************************

REM set JAVA_HOME=D:\j2sdk1.4.2_04

set CLASSPATH=""

REM if "" == "%JAVA_HOME%" goto javaHomeNotSet



REM ****************************************************
REM ****************************************************
REM *
REM * CLASSPATH : provide path of JDBC Driver JAR file/s
REM * 		  in the following command line
REM ****************************************************
REM ****************************************************

javaw -classpath %CLASSPATH%.;MainDAO.jar;"lib\pg74jdbc3.jar";"lib\mimjdbc3.jar" pm.dao.gen.DAOMain 


goto end

REM :javaHomeNotSet
REM echo Please set the JAVA_HOME environment variable.
REM goto end

:end


