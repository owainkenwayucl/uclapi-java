// UCL API example in Java
// This example queries the roombookings/rooms endpoint and prints results.
//
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import uclapi.Booking;

import java.util.Hashtable;

public class searchbookings {
    public static void main(String[] args) {
        if (args.length == 2) {

            UCLApiConnection conn = new UCLApiConnection(args[0]);
            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("date", args[1]);

            Booking[] bookings = Booking.searchAPI(conn, params);

            for (int i = 0; i<bookings.length; i++) {
                System.out.println(bookings[i].toString());
            }

            System.out.println("Got " + Integer.toString(bookings.length) + " bookings.");
            
        } else {
            System.err.println("Run with API key + date [YYYYMMDD] as arguments.");
            System.exit(1);
        }
    }
}