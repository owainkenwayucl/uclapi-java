// UCL API example in Java
// This example queries the roombookings/rooms endpoint and prints results.
//
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import uclapi.Room;

import java.util.Hashtable;

public class searchrooms {
    public static void main(String[] args) {
        if (args.length == 2) {

            UCLApiConnection conn = new UCLApiConnection(args[0]);
            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("date", args[1]);

            Room[] rooms = Room.searchAPI(conn, params);

            for (int i = 0; i<rooms.length; i++) {
                System.out.println(rooms[i].toString());
            }
            
        } else {
            System.err.println("Run with API key + date [YYYYMMDD] as arguments.");
            System.exit(1);
        }
    }
}