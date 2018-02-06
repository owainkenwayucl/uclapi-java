// UCL API example in Java - search for free rooms.
//
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import uclapi.Room;

import java.util.Hashtable;

public class searchfreerooms {
    public static void main(String[] args) {
        if (args.length == 3) {

            UCLApiConnection conn = new UCLApiConnection(args[0]);
            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("start_datetime", args[1]);
            params.put("end_datetime", args[2]);

            Room[] rooms = Room.searchAPIForFreeRooms(conn, params);

            for (int i = 0; i<rooms.length; i++) {
                System.out.println(rooms[i].toString());
            }
            
        } else {
            System.err.println("Run with API key + start + end datetimes as arguments.");
            System.exit(1);
        }
    }
}