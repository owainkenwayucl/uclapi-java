/**
 * This class represents a Person in the UCL API.
 * @author Owain Kenway
 */
package uclapi;

import uclapi.UCLApiConnection;
import uclapi.JSONWrapper;

import java.lang.Math;
import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

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
            this.name = JSONWrapper.safeGetString(jsonperson, "name");
            this.status = JSONWrapper.safeGetString(jsonperson, "status");
            this.department = JSONWrapper.safeGetString(jsonperson, "department");
            this.email = JSONWrapper.safeGetString(jsonperson, "email");
        } catch(Exception e) {
            System.err.println(e.toString());
            if (uclapi.UCLApiConnection.ExitOnException) {
                System.exit(2);
            }
        }
    }

    /**
     * Perform a query on a given UCL API connection and return an array of people.
     * @param conn UCLApiConnection
     * @param query Query string
     * @return Array of people
     */
    public static Person[] searchAPI(UCLApiConnection conn, String query) {
        Hashtable<String, String> params = new Hashtable<String, String>();
        params.put("query", query);
        String response = conn.queryAPI(UCLApiConnection.SearchPeopleEP, params);

        try {
            JSONParser p = new JSONParser();
            JSONObject responseObject = (JSONObject)p.parse(response);

            JSONArray people = (JSONArray)responseObject.get("people");
            int nPeople = people.size();
            Person[] retval = new Person[nPeople];

            for (int i = 0; i < nPeople; i++) {
                JSONObject jperson = (JSONObject)people.get(i);

                retval[i] = new Person(jperson);

            }
            return retval;
        } catch (Exception e){
            System.err.println(e.toString());
            if (uclapi.UCLApiConnection.ExitOnException) {
                System.exit(5);
            }
        }
        return new Person[0];
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
