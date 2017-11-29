JAVAC = javac 

all: uclapi/UCLApiConnection.class uclapi/Room.class uclapi/Person.class uclapi/Equipment.class

uclapi/UCLApiConnection.class: uclapi/UCLApiConnection.java Makefile
	$(JAVAC) uclapi/UCLApiConnection.java

uclapi/Room.class: uclapi/Room.java Makefile
	$(JAVAC) uclapi/Room.java

uclapi/Person.class: uclapi/Person.java Makefile
	$(JAVAC) uclapi/Person.java

uclapi/Equipment.class: uclapi/Equipment.java Makefile
	$(JAVAC) uclapi/Equipment.java

clean:
	rm -rf uclapi/*.class *.class trash/*.class examples/*.class