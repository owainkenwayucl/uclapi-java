JAVAC = javac 

all: uclapi/UCLApiConnection.class uclapi/Room.class

uclapi/UCLApiConnection.class: uclapi/UCLApiConnection.java Makefile
	$(JAVAC) uclapi/UCLApiConnection.java

uclapi/Room.class: uclapi/Room.java Makefile
	$(JAVAC) uclapi/Room.java

clean:
	rm -rf uclapi/*.class *.class trash/*.class examples/*.class