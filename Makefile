JAVAC ?= javac
JAVA ?= java
FLAGS = --release 17 -Xlint:all -Werror

.PHONY: reference check clean
reference: build/reference/LabCheck.class
	$(JAVA) -cp build/reference LabCheck $(TASK)
check: build/starter/LabCheck.class
	$(JAVA) -cp build/starter LabCheck $(TASK)
build/reference/LabCheck.class: src/reference/Tasks.java tests/LabCheck.java
	mkdir -p build/reference
	$(JAVAC) $(FLAGS) -d build/reference $^
build/starter/LabCheck.class: src/starter/Tasks.java tests/LabCheck.java
	mkdir -p build/starter
	$(JAVAC) $(FLAGS) -d build/starter $^
clean:
	rm -rf build
