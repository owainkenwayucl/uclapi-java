/**
 * This class represents a piece of equipment in a room in the UCL API.
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
     * Use JSON.simple to create a piece of equipment from a JSONObject.
     * NOTE: Assumes the object IS a piece of equipment!
     * @param jsonequipment a JSONObject containing equipment.
     */
    public Equipment(JSONObject jsonequipment) {
        try {

            this.type=JSONWrapper.safeGetString(jsonequipment, "type");
            this.description=JSONWrapper.safeGetString(jsonequipment, "description");
            this.number=JSONWrapper.safeGetLong(jsonequipment, "units");

        } catch(Exception e) {
            System.err.println(e.toString());
            if (uclapi.UCLApiConnection.ExitOnException) {
                System.exit(2);
            }
        }

    }

    /**
     * Perform a query on a given UCL API connection and return an array equipment.
     * @param conn UCLApiConnection
     * @param endpoint the API path
     * @param params hashtable of query parameters
     * @return Array of Equipment
     */
    public static Equipment[] searchAPI(UCLApiConnection conn, String endpoint, Hashtable<String, String> params) {
        String response = conn.queryAPI(endpoint, params);

        try {
            JSONParser p = new JSONParser();
            JSONObject responseObject = (JSONObject)p.parse(response);

            JSONArray equipment = (JSONArray)responseObject.get("equipment");
            int nEquipment = equipment.size();
            Equipment[] retval = new Equipment[nEquipment];

            for (int i = 0; i < nEquipment; i++) {
                JSONObject jeq = (JSONObject)equipment.get(i);

                retval[i] = new Equipment(jeq);

            }

            return retval;

        } catch (Exception e){
            System.err.println(e.toString());
            if (uclapi.UCLApiConnection.ExitOnException) {
                System.exit(5);
            }
        }
        return new Equipment[0];
    }

    /**
     * Perform a query on a given UCL API connection and return an array equipment.
     * @param conn UCLApiConnection
     * @param params hashtable of query parameters
     * @return Array of Equipment
     */
    public static Equipment[] searchAPI(UCLApiConnection conn, Hashtable<String, String> params) {
        return Equipment.searchAPI(conn, UCLApiConnection.RoomEquipmentEP, params);
    }

    /**
     * @return a string representation of this equipment.
     */
    public String toString() {
        return new String("Type: " + this.type + "\nDescription: " + this.description + "\nNumber: " + Long.toString(this.number) + "\n");
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
