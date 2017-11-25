JAVAC = javac 

uclapi/UCLApiConnection.class: uclapi/UCLApiConnection.java
	$(JAVAC) uclapi/UCLApiConnection.java
	
clean:
	rm -rf uclapi/*.class *.class trash/*.class