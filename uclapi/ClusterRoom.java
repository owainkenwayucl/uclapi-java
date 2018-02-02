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

public class ClusterRoom {
     
    // Extracted from JSON example.
    private String roomname;
    private String buildingname;
    private String postcode;
    private String roomid;
    private double latitude;
    private double longitude;
    private String address;
    private long seats;
    private long freeseats;
    private String status;

    private final static int address_size = 4;

    /**
     * Constructor to create a blank room.
     */
    public ClusterRoom() {
        this.roomname = "";
        this.buildingname = "";
        this.postcode = "";
        this.roomid = "";
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.address = "";
        this.status = "";
        this.seats = 0;
        this.freeseats = 0;
    }

    /**
     * Use JSON.simple to create a room from a JSONObject.
     * NOTE: Assumes the object IS a room!
     * @param jsonroom a JSONObject containing a room.
     */
    public ClusterRoom(JSONObject jsonroom) {
        try {
            JSONObject jlocation = (JSONObject)jsonroom.get("location");

            this.roomname = JSONWrapper.safeGetString(jlocation, "roomname");
            this.roomid = JSONWrapper.safeGetString(jlocation, "room_id");
            this.buildingname = JSONWrapper.safeGetString(jlocation, "building_name");
            this.postcode = JSONWrapper.safeGetString(jlocation, "postcode");
            this.address = JSONWrapper.safeGetString(jlocation, "address");
            this.latitude = JSONWrapper.safeGetDouble(jlocation, "latitude");
            this.longitude = JSONWrapper.safeGetDouble(jlocation, "longitude");

            this.seats = JSONWrapper.safeGetLong(jsonroom, "total_seats");
            this.freeseats = JSONWrapper.safeGetLong(jsonroom, "free_seats");
            this.status = JSONWrapper.safeGetString(jsonroom, "room_status");

            
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
    public static ClusterRoom[] searchAPI(UCLApiConnection conn, Hashtable<String, String> params) {
        return ClusterRoom.searchAPI(conn, UCLApiConnection.DesktopFreeEP, params);
    }

    /**
     * Perform a query on a given UCL API connection and return an array of rooms.
     * @param conn UCLApiConnection
     * @param endpoint the API path
     * @param params hashtable of query parameters
     * @return Array of Rooms
     */
    public static ClusterRoom[] searchAPI(UCLApiConnection conn, String endpoint, Hashtable<String, String> params) {
        return ClusterRoom.searchAPI(conn, endpoint, params, "data");
    }

    /**
     * Perform a query on a given UCL API connection and return an array of rooms.
     * @param conn UCLApiConnection
     * @param endpoint the API path
     * @param params hashtable of query parameters
     * @param responseTableName name of table of results in JSON output.
     * @return Array of Rooms
     */
    public static ClusterRoom[] searchAPI(UCLApiConnection conn, String endpoint, Hashtable<String, String> params, String responseTableName) {
        String response = conn.queryAPI(endpoint, params);

        try {
            JSONParser p = new JSONParser();
            JSONObject responseObject = (JSONObject)p.parse(response);

            JSONArray rooms = (JSONArray)responseObject.get(responseTableName);
            int nRooms = rooms.size();
            ClusterRoom[] retval = new ClusterRoom[nRooms];

            for (int i = 0; i < nRooms; i++) {
                JSONObject jroom = (JSONObject)rooms.get(i);

                retval[i] = new ClusterRoom(jroom);

            }

            return retval;

        } catch (Exception e){
            System.err.println(e.toString());
            if (uclapi.UCLApiConnection.ExitOnException) {
                System.exit(5);
            }
        }
        return new ClusterRoom[0];
    }
    /**
     * @return a string representation of the room.
     */
    public String toString() {
        String retval = "";
        retval = retval + "Name: " + this.roomname + "\n"
                        + "Room id: " + this.roomid + "\n"
                        + "Location:\n"
                        + "  Latitude: " + Double.toString(this.latitude) + "\n"
                        + "  Longitude: " + Double.toString(this.longitude) + "\n"
                        + "Address:\n"
                        + "  " + this.buildingname + "\n"
                        + "  " + this.address + "\n"
                        + "  " + this.postcode + "\n"
                        + "Availability:\n"
                        + "  Total seats: " + Long.toString(this.seats) + "\n"
                        + "  Free seats: " + Long.toString(this.freeseats) + "\n"
                        + "  Status: " + this.status + "\n";
 
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
     * @param address - the new address.
     */
    public void setAddress(String address) {
        this.address = new String(address);
    }

    /**
     * Get address.
     * @return the address
     */
    public String getAddress() {
        return new String(this.address);
    }

    /**
     * Set the building name.
     * @param buildingname - the new building name.
     */
    public void setBuildingName(String buildingname) {
        this.buildingname = new String(buildingname);
    }

    /**
     * Get building name.
     * @return the building name
     */
    public String getBuildingName() {
        return new String(this.buildingname);
    }

    /**
     * Set the postcode.
     * @param postcode - the new postcode.
     */
    public void setPostcode(String postcode) {
        this.postcode = new String(postcode);
    }

    /**
     * Get postcode.
     * @return postcode
     */
    public String getPostcode() {
        return new String(this.postcode);
    }

    /**
     * Set the status.
     * @param status - the new status.
     */
    public void setStatus(String status) {
        this.status = new String(status);
    }

    /**
     * Get status.
     * @return status
     */
    public String getStatis() {
        return new String(this.status);
    }

    /**
     * Set number of seats.
     * @param seats the new capacity of the room.
     */
    public void setSeats(long seats) {
        this.seats = seats;
    }

    /**
     * Get seats.
     * @return the capacity of the room.
     */
    public long getSeats() {
        return this.seats;
    }

    /**
     * Set number of free seats.
     * @param seats the new number of free seats.
     */
    public void setFreeSeats(long seats) {
        this.freeseats = seats;
    }

    /**
     * Get number of free seats.
     * @return the number of free seats in the room.
     */
    public long getFreeSeats() {
        return this.freeseats;
    }
}
