/**
 * This class represents a room in the UCL API.
 * @author Owain Kenway
 */
package uclapi;

import java.lang.Math;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

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
     */
    public Room(JSONObject jsonroom) {
        try {
            JSONObject jlocation = (JSONObject)jsonroom.get("location");
            JSONObject jcoordinates = (JSONObject)jlocation.get("coordinates");
            JSONArray jaddress = (JSONArray)jlocation.get("address");

            this.roomname = new String(((String)jsonroom.get("roomname")));
            this.roomid = new String(((String)jsonroom.get("roomid")));
            this.siteid = new String(((String)jsonroom.get("siteid")));
            this.sitename = new String(((String)jsonroom.get("sitename")));
            this.capacity = ((long)jsonroom.get("capacity"));
            this.classification = new String(((String)jsonroom.get("classification")));
            this.automated = new String(((String)jsonroom.get("automated")));

            this.latitude = Double.parseDouble((String)jcoordinates.get("lat"));
            this.longitude = Double.parseDouble((String)jcoordinates.get("lng"));

            this.address = new String[address_size];
            for (int i = 0; i < address_size; i++) {
                this.address[i] = new String((String)jaddress.get(i));
            }

        } catch(Exception e) {
            System.err.println(e.toString());
            System.exit(2);
        }

    }

    /**
     * @returns a string representation of the room.
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
     * Get site id.
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