/**
 * This class represents a piece of equipment in a room in the UCL API.
 * @author Owain Kenway
 */
package uclapi;

import uclapi.UCLApiConnection;

import java.lang.Math;
import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class Equipment {

    // Extracted from documentation;
    private String type;
    private String description;
    private long number;

    /**
     * Constructor to create a blank piece of equipment.
     */
    public Equipment() {
        this.type = "";
        this.description = "";
        this.number = 0;
    }

    /**
     * Set equipment type.
     * @param newType the new type of the equipment.
     */
    public void setType(String newType) {
        this.type = new String(newType);
    }

    /**
     * Set equipment description.
     * @param newDescription the new description of the equipment.
     */
    public void setDescription(String newDescription) {
        this.description = new String(newDescription);
    }

    /**
     * Set equipment number.
     * @param newNumber the new number of the equipment.
     */
    public void setNumber(long newNumber) {
        this.number = newNumber;
    }

    /**
     * Get type of equipment.
     * @return the type of equipment.
     */
    public String getType() {
        return new String(this.type);
    }

    /**
     * Get description of the equipment.
     * @return the description of the equipment.
     */
    public String getDescription() {
        return new String(this.description);
    }

    /**
     * Get number of the equipment.
     * @return the number of the equipment.
     */
    public long getNumber() {
        return this.number;
    }
}