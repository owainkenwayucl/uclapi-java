/**
 * This class represents a room in the UCL API.
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

public class Room {
     
    // Extracted from JSON example.
    private String roomname;
    private String roomid;
    private String siteid;
    private String sitename;
    private long capacity;
    private String classification;
    private String automated;
    private double latitude;
    private double longitude;
    private String[] address;

    private final static int address_size = 4;

    /**
     * Constructor to create a blank room.
     */
    public Room() {
        this.roomname = "";
        this.roomid = "";
        this.siteid = "";
        this.sitename = "";
        this.capacity = 0;
        this.classification = "";
        this.automated = "";
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.address = new String[address_size];
    }

    /**
     * Use JSON.simple to create a room from a JSONObject.
     * NOTE: Assumes the object IS a room!
     * @param jsonroom a JSONObject containing a room.
     */
    public Room(JSONObject jsonroom) {
        try {
            JSONObject jlocation = (JSONObject)jsonroom.get("location");
            JSONObject jcoordinates = (JSONObject)jlocation.get("coordinates");
            JSONArray jaddress = (JSONArray)jlocation.get("address");

            this.roomname = JSONWrapper.safeGetString(jsonroom, "roomname");
            this.roomid = JSONWrapper.safeGetString(jsonroom, "roomid");
            this.siteid = JSONWrapper.safeGetString(jsonroom, "siteid");
            this.sitename = JSONWrapper.safeGetString(jsonroom, "sitename");
            this.capacity = JSONWrapper.safeGetLong(jsonroom, "capacity");
            this.classification = JSONWrapper.safeGetString(jsonroom, "classification");
            this.automated = JSONWrapper.safeGetString(jsonroom, "automated");
            this.latitude = JSONWrapper.safeGetDouble(jcoordinates, "lat");
            this.longitude = JSONWrapper.safeGetDouble(jcoordinates, "lng");
            this.address = new String[address_size];
            for (int i = 0; i < address_size; i++) {
                this.address[i] = JSONWrapper.safeGetString(jaddress, i);
            }
            
        } catch(Exception e) {
            System.err.println(e.toString());
            if (uclapi.UCLApiConnection.ExitOnException) {
                System.exit(2);
            }
        }

    }

    /**
     * Perform a query on a given UCL API connection and return an array of rooms.
     * @param conn UCLApiConnection
     * @param params hashtable of query parameters
     * @return Array of Rooms
     */
    public static Room[] searchAPI(UCLApiConnection conn, Hashtable<String, String> params) {
        return Room.searchAPI(conn, UCLApiConnection.RoomRoomsEP, params);
    }

    /**
     * Perform a query on a given UCL API connection and return an array of rooms.
     * @param conn UCLApiConnection
     * @param endpoint the API path
     * @param params hashtable of query parameters
     * @return Array of Rooms
     */
    public static Room[] searchAPI(UCLApiConnection conn, String endpoint, Hashtable<String, String> params) {
        String response = conn.queryAPI(endpoint, params);

        try {
            JSONParser p = new JSONParser();
            JSONObject responseObject = (JSONObject)p.parse(response);

            JSONArray rooms = (JSONArray)responseObject.get("rooms");
            int nRooms = rooms.size();
            Room[] retval = new Room[nRooms];

            for (int i = 0; i < nRooms; i++) {
                JSONObject jroom = (JSONObject)rooms.get(i);

                retval[i] = new Room(jroom);

            }

            return retval;

        } catch (Exception e){
            System.err.println(e.toString());
            if (uclapi.UCLApiConnection.ExitOnException) {
                System.exit(5);
            }
        }
        return new Room[0];
    }
    /**
     * @return a string representation of the room.
     */
    public String toString() {
        String retval = "";
        retval = retval + "Name: " + this.roomname + "\n"
                        + "Room id: " + this.roomid + "\n"
                        + "Site id: " + this.siteid + "\n"
                        + "Site name: " + this.sitename + "\n"
                        + "Capacity: " + Long.toString(this.capacity) + "\n"
                        + "Classification: " + this.classification + "\n"
                        + "Automated: " + this.automated + "\n"
                        + "Location:\n"
                        + "  Latitude: " + Double.toString(this.latitude) + "\n"
                        + "  Longitude: " + Double.toString(this.longitude) + "\n"
                        + "Address:\n";
        for (int i = 0; i < address_size; i++) {
            retval = retval + "  " + this.address[i] + "\n";
        }
        return retval;
    }


    /**
     * Set room name.
     * @param name the new name of the room.
     */
    public void setRoomname(String name) {
        this.roomname = new String(name);
    }

    /**
     * Get room name.
     * @return the name of the room.
     */
    public String getRoomname() {
        return new String(this.roomname);
    }

    /**
     * Set room id.
     * @param id the new id of the room.
     */
    public void setRoomid(String id) {
        this.roomid = new String(id);
    }

    /**
     * Get room id.
     * @return the id of the room.
     */
    public String getRoomid() {
        return new String(this.roomid);
    }

    /**
     * Set site id.
     * @param id the new id of the site.
     */
    public void setSiteid(String id) {
        this.siteid = new String(id);
    }

    /**
     * Get site id.
     * @return the id of the site.
     */
    public String getSiteid() {
        return new String(this.siteid);
    }

    /**
     * Set site name.
     * @param name the new name of the site.
     */
    public void setSitename(String name) {
        this.sitename = new String(name);
    }

    /**
     * Get site name.
     * @return the name of the site.
     */
    public String getSitename() {
        return new String(this.sitename);
    }

    /**
     * Set capacity.
     * @param capacity the new capacity of the room..
     */
    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    /**
     * Get capacity.
     * @return the capactiy of the site.
     */
    public long getCapacity() {
        return this.capacity;
    }

    /**
     * Set classification
     * @param classification the new classification of the room.
     */
    public void setClassification(String classification) {
        this.classification = new String(classification);
    }

    /**
     * Get classificaton.
     * @return the classification of the room.
     */
    public String getClassification() {
        return new String(this.classification);
    }

    /**
     * Set automation.
     * @param automation the new state automation.
     */
    public void setAutomation(String automation) {
        this.automated = new String(automation);
    }

    /**
     * Get automation.
     * @return whether the room is automated or not.
     */
    public String getAutomation() {
        return new String(this.automated);
    }

    /**
     * Set co-ordinates..
     * @param latitude the new latitude.
     * @param longitude the new longitude..
     */
    public void setCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get latitude.
     * @return latitude.
     */
    public double getLatitude() {
        return this.latitude;
    }    

    /**
     * Get longitude.
     * @return longitude.
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Set address
     * @param address - String array of size address_size.
     */
    public void setAddress(String[] address) {
        int inlength = Math.min(address_size, address.length);
        for (int i = 0; i < inlength; i++) {
            this.address[i] = new String(address[i]);
        }
    }

    /**
     * Get address.
     * @return the address
     */
    public String[] getAddress() {
        String[] retval = new String[address_size];
        for (int i = 0; i < address_size; i++) {
            retval[i] = new String(this.address[i]);
        }
        return retval;
    }
}
