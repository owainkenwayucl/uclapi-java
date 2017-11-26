/**
 * This class represents a Person in the UCL API.
 * @author Owain Kenway
 */
package uclapi;

import java.lang.Math;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Person {

    private String name;
    private String status;
    private String department;
    private String email;

    /**
     * Constructor to create a blank person.
     */
    public Person() {
        this.name = "";
        this.status = "";
        this.department = "";
        this.email = "";
    }

    /**
     * Use JSON.simple to construct a person from a JSONObject.
     * NOTE: Assumes the object IS a person!
     * @param jsonperson a JSONObject containing a person.
     */
    public Person(JSONObject jsonperson) {
        try {
            this.name = new String(((String)jsonperson.get("name")));
            this.status = new String(((String)jsonperson.get("status")));
            this.department = new String(((String)jsonperson.get("department")));
            this.email = new String(((String)jsonperson.get("email")));
        } catch(Exception e) {
            System.err.println(e.toString());
            System.exit(2);
        }
    }

    /**
     * @return a string representation of the room.
     */
    public String toString() {
        String retval = "";
        retval = retval + "Name: " + this.name + "\n"
                        + "Status: " + this.status + "\n"
                        + "Department: " + this.department + "\n"
                        + "Email: " + this.email + "\n";
        return retval;
    }

    /**
     * Set person name.
     * @param name the new name of the person.
     */
    public void setName(String name) {
        this.name = new String(name);
    }

    /**
     * Get person name.
     * @return the name of the person.
     */
    public String getName() {
        return new String(this.name);
    }

    /**
     * Set person's status..
     * @param status the new status of the person.
     */
    public void setStatus(String status) {
        this.status = new String(status);
    }

    /**
     * Get person's status.
     * @return the status of the person.
     */
    public String getStatus() {
        return new String(this.status);
    }

    /**
     * Set department.
     * @param department the new department of the person.
     */
    public void setDepartment(String department) {
        this.department = new String(department);
    }

    /**
     * Get department..
     * @return the person's department..
     */
    public String getDepartment() {
        return new String(this.department);
    }

    /**
     * Set email.
     * @param email the new email address..
     */
    public void setEmail(String email) {
        this.email = new String(email);
    }

    /**
     * Get the person's email.
     * @return the email address of the persosn..
     */
    public String getEmail() {
        return new String(this.email);
    }

}