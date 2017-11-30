/**
 * This class represents a room booking in the UCL API.
 * @author Owain Kenway
 */
package uclapi;

import uclapi.UCLApiConnection;
import uclapi.Room;

import java.lang.Math;
import java.util.Hashtable;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Booking {
     
    // Extracted from JSON example.
    
    private String roomname;
    private String roomid;
    private String siteid;

    private String endtime;
    private String starttime;
    private long slotid;
    private String phone;
    private String description;
    private double weeknumber;
    private String contact;


    /**
     * Constructor to create a blank room.
     */
    public Booking() {
        this.roomname = "";
        this.roomid = "";
        this.siteid = "";
        this.endtime = "";
        this.starttime = "";
        this.slotid = 0;
        this.phone = "";
        this.description = "";
        this.weeknumber = 0d;
        this.contact = "";
    }

    /**
     * Use JSON.simple to create a booking from a JSONObject.
     * NOTE: Assumes the object IS a booking!
     * @param jsonbooking a JSONObject containing a booking.
     */
    public Booking(JSONObject jsonbooking) {
        try {
            this.roomname = new String(((String)jsonbooking.get("roomname")));
            this.roomid = new String(((String)jsonbooking.get("roomid")));
            this.siteid = new String(((String)jsonbooking.get("siteid")));
            this.endtime = new String(((String)jsonbooking.get("end_time")));
            this.starttime = new String(((String)jsonbooking.get("start_time")));

            // These fields appear to be able to be null...
            try {
                this.phone = new String(((String)jsonbooking.get("phone")));                
            } catch (NullPointerException e) {
                this.phone = "";
            }
            try {
                this.description = new String(((String)jsonbooking.get("description")));
            } catch (NullPointerException e) {
                this.description = "";
            }
            try {
                this.contact = new String(((String)jsonbooking.get("contact")));
            } catch (NullPointerException e) {
                this.contact = "";
            }
            this.slotid = ((long)jsonbooking.get("slotid"));            
            this.weeknumber = (double)jsonbooking.get("weeknumber");

        } catch(Exception e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
            System.exit(2);
        }

    }

    /**
     * Perform a query on a given UCL API connection and return an array of bookings.
     * @param conn UCLApiConnection
     * @param params hashtable of query parameters
     * @return Array of bookings
     */
    public static Booking[] searchAPI(UCLApiConnection conn, Hashtable<String, String> params) {
        return Booking.searchAPI(conn, UCLApiConnection.RoomBookingsEP, params);
    }

    /**
     * Perform a query on a given UCL API connection and return an array of bookings.
     * @param conn UCLApiConnection
     * @param endpoint the API path
     * @param params hashtable of query parameters
     * @return Array of bookings
     */
    public static Booking[] searchAPI(UCLApiConnection conn, String endpoint, Hashtable<String, String> params) {

        try {
            boolean cont = true;
            LinkedList<Booking> bookings = new LinkedList<Booking>();

            while (cont == true) {
                String response = conn.queryAPI(endpoint, params);
                JSONParser p = new JSONParser();
                JSONObject responseObject = (JSONObject)p.parse(response);

                JSONArray bs = (JSONArray)responseObject.get("bookings");

                int nBookings = bs.size();
                
                for (int i = 0; i < nBookings; i++) {
                    bookings.add(new Booking((JSONObject)bs.get(i)));
                }

                cont = (boolean)responseObject.get("next_page_exists"); 
            
                if (cont) {
                    params.put("page_token",(String)responseObject.get("page_token"));

                }
            }


            return bookings.toArray(new Booking[bookings.size()]);

        } catch (Exception e){
            System.err.println(e.toString());
            System.exit(5);
        }
        return new Booking[0];
    }
    /**
     * @return a string representation of the room.
     */
    public String toString() {
        String retval = "";
        retval = retval + "Name: " + this.roomname + "\n"
                        + "Room id: " + this.roomid + "\n"
                        + "Site id: " + this.siteid + "\n"
                        + "Description: " + this.description + "\n"
                        + "Start: " + this.starttime + "\n"
                        + "End: " + this.endtime + "\n"
                        + "Week: " + Double.toString(this.weeknumber) +"\n"
                        + "Slot ID: " + Long.toString(this.slotid) + "\n"
                        + "Contact: " + this.contact + "\n"
                        + "Phone: " + this.phone + "\n";
        return retval;
    }

    /**
     * Get room.
     * @param conn a connection to recover room via.
     * @return the room.
     */
    public Room getRoom(UCLApiConnection conn) {
        Hashtable<String, String> params = new Hashtable<String, String>();
        params.put("siteid", this.siteid);
        params.put("roomid", this.roomid);
        return Room.searchAPI(conn, params)[0];
    }

    /**
     * Set endtime.
     * @param endtime the new endtime of the booking.
     */
    public void setEndtime(String endtime) {
        this.endtime = new String(endtime);
    }

    /**
     * Get end time.
     * @return the end time of the booking.
     */
    public String getEndtime() {
        return new String(this.endtime);
    }

    /**
     * Set start time.
     * @param starttime the new start time of the booking.
     */
    public void setStarttime(String starttime) {
        this.starttime = new String(starttime);
    }

    /**
     * Get start time.
     * @return the start time of the booking.
     */
    public String getStarttime() {
        return new String(this.starttime);
    }

    /**
     * Set slot id.
     * @param slotid the new id of the slot.
     */
    public void setSlotid(long slotid) {
        this.slotid = slotid;
    }

    /**
     * Get slot id.
     * @return the slot id.
     */
    public long getSlotid() {
        return this.slotid;
    }

    /**
     * Set phone number.
     * @param phone the new phone number
     */
    public void setPhone(String phone) {
        this.phone = new String(phone);
    }

    /**
     * Get phone number.
     * @return the phone number.
     */
    public String getPhone() {
        return new String(this.phone);
    }

    /**
     * Set description.
     * @param description the new description.
     */
    public void setDescription(String description) {
        this.description = new String(description);
    }

    /**
     * Get description.
     * @return the description of the booking..
     */
    public String getDescription() {
        return new String(this.description);
    }


    /**
     * Set contact.
     * @param contact the new contact for the booking.
     */
    public void setContact(String contact) {
        this.contact = new String(contact);
    }

    /**
     * Get contact.
     * @return the contact for the booking.
     */
    public String getContact() {
        return new String(this.contact);
    }

    /**
     * Set week number.
     * @param weeknumber the new weeknumber.
     */
    public void setWeeknumber(double weeknumber) {
        this.weeknumber = weeknumber;
    }

    /**
     * Get week number.
     * @return the week number (a double).
     */
    public double getWeeknumber() {
        return this.weeknumber;
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

}