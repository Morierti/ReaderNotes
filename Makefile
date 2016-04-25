#Makefile
CORE = readernotes/src/core
UI	= readernotes/src/ui
BASE = readernotes
SRC = readernotes/src
EXCEPTIONS = readernotes/src/exceptions
DATABASE = readernotes/src/database
CORE_TESTS = readernotes/test/core
JUNIT_PATH=libs/junit-4.12.jar
HAMCREST=libs/java-hamcrest-2.0.0.0.jar
JDOM=libs/jdom-2.0.6/jdom-2.0.6.jar
JUNIT_EXEC=org.junit.runner.JUnitCore

all:
	javac -cp $(JUNIT_PATH):$(JDOM) `find $(CORE) $(EXCEPTIONS) $(CORE_TESTS) $(DATABASE) $(BASE) $(UI) \
    -name *.java`

run:
#After loading the jdom library it needs to go back to the base directory.
	java -classpath libs/jdom-2.0.6/jdom-2.0.6.jar: readernotes.Shell

#This is how to run junit from the jar package inside the project.
test:
	java -cp .:$(HAMCREST):$(JUNIT_PATH) $(JUNIT_EXEC) readernotes.test.core.BookTest
	java -cp .:$(HAMCREST):$(JUNIT_PATH) $(JUNIT_EXEC) readernotes.test.core.SinteseTest
	java -cp .:$(HAMCREST):$(JUNIT_PATH) $(JUNIT_EXEC) readernotes.test.core.ShellTest

clean:
	rm readernotes/src/core/*.class
	rm readernotes/src/exceptions/*.class
	rm readernotes/src/database/*.class
	rm readernotes/src/ui/*.class
	rm readernotes/test/core/*.class
	rm readernotes/Shell.class
