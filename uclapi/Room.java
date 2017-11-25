/**
 * This class represents a room in the UCL API.
 * @author Owain Kenway
 */
package uclapi;

 public class Room {
     
    // Extracted from JSON example.
    private String roomname;
    private String roomid;
    private String siteid;
    private int capacity;
    private String classification;
    private boolean automated;
    private double latitude;
    private double longitude;
    private String[] address;

    /**
     * Constructor to create a blank room.
     */
    public Room() {
        this.roomname = "";
        this.roomid = "";
        this.siteid = "";
        this.capacity = 0;
        this.classification = "";
        this.automated = false;
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        this.address = new String[4];
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
     * @param id the new name of the room.
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
 }