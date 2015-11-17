#Makefile
CORE = readernotes/src/core
EXCEPTIONS = readernotes/src/exceptions
CORE_TESTS = readernotes/test/core
JUNIT_PATH=libs/junit-4.12.jar
HAMCREST=libs/java-hamcrest-2.0.0.0.jar
JUNIT_EXEC=org.junit.runner.JUnitCore

all:
	javac -cp $(JUNIT_PATH) `find $(CORE) $(EXCEPTIONS) $(CORE_TESTS) \
    -name *.java`

#This is how to run junit from the jar package inside the project.
test:
	java -cp .:$(HAMCREST):$(JUNIT_PATH) $(JUNIT_EXEC) readernotes.test.core.BookTest
	#java -cp .:$(HAMCREST):$(JUNIT_PATH) $(JUNIT_EXEC) readernotes.test.core.SinteseTest
	#java -cp .:$(HAMCREST):$(JUNIT_PATH) $(JUNIT_EXEC) readernotes.test.core.ShellTest

clean:
	rm readernotes/src/core/*.class
	rm readernotes/src/exceptions/*.class
	rm readernotes/test/core/*.class
