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
            this.department = new String(((String)jsonperson.get("departmet")));
            this.email = new String(((String)jsonperson.get("email")));
        } catch(Exception e) {
            System.err.println(e.toString());
            System.exit(2);
        }
    }


}