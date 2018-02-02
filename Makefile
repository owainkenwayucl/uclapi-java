JAVAC = javac 

default: uclapi/UCLApiConnection.class uclapi/JSONWrapper.class uclapi/Room.class uclapi/ClusterRoom.class uclapi/Person.class uclapi/Equipment.class uclapi/Booking.class Makefile

all: uclapi/UCLApiConnection.class uclapi/JSONWrapper.class uclapi/Room.class uclapi/ClusterRoom.class uclapi/Person.class uclapi/Equipment.class uclapi/Booking.class Makefile jar javadoc

javadoc: doc/javadoc/index.html

jar: uclapi.jar

uclapi.jar: uclapi/UCLApiConnection.class uclapi/JSONWrapper.class uclapi/Room.class uclapi/ClusterRoom.class uclapi/Person.class uclapi/Equipment.class uclapi/Booking.class Makefile
	jar -cvf uclapi.jar uclapi/*.class

uclapi/UCLApiConnection.class: uclapi/UCLApiConnection.java Makefile
	$(JAVAC) uclapi/UCLApiConnection.java

uclapi/JSONWrapper.class: uclapi/JSONWrapper.java Makefile
	$(JAVAC) uclapi/JSONWrapper.java

uclapi/Room.class: uclapi/Room.java Makefile
	$(JAVAC) uclapi/Room.java

uclapi/ClusterRoom.class: uclapi/ClusterRoom.java Makefile
	$(JAVAC) uclapi/ClusterRoom.java

uclapi/Person.class: uclapi/Person.java Makefile
	$(JAVAC) uclapi/Person.java

uclapi/Equipment.class: uclapi/Equipment.java Makefile
	$(JAVAC) uclapi/Equipment.java

uclapi/Booking.class: uclapi/Booking.java Makefile
	$(JAVAC) uclapi/Booking.java

doc/javadoc/index.html: uclapi/Room.java uclapi/ClusterRoom.java uclapi/JSONWrapper.java uclapi/Person.java uclapi/Equipment.java uclapi/Booking.java Makefile
	mkdir -p doc/javadoc; cd doc/javadoc; javadoc uclapi

clean:
	rm -rf uclapi/*.class *.class trash/*.class examples/*.class doc/javadoc *.jar
