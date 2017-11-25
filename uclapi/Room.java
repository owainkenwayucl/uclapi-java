/**
 * This class represents a room in the UCL API.
 * @author Owain Kenway
 */
package uclapi;

import java.lang.Math;

 public class Room {
     
    // Extracted from JSON example.
    private String roomname;
    private String roomid;
    private String siteid;
    private String sitename;
    private int capacity;
    private String classification;
    private boolean automated;
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
        this.automated = false;
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.address = new String[address_size];
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
                        + "Capacity: " + Integer.toString(this.capacity) + "\n"
                        + "Classification: " + this.classification + "\n"
                        + "Automated: " + Boolean.toString(this.automated) + "\n"
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
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get capacity.
     * @return the capactiy of the site.
     */
    public int getCapacity() {
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
    public void setAutomation(boolean automation) {
        this.automated = automation;
    }

    /**
     * Get automation.
     * @return whether the room is automated or not.
     */
    public boolean getAutomation() {
        return this.automated;
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