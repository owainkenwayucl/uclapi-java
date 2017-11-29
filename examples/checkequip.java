// UCL API example in Java
// This example searchs for equipment in a room.
//
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import uclapi.Equipment;

import java.util.Hashtable;

public class checkequip {
    public static void main(String[] args) {
        if (args.length == 3) {

            // Get our response out of the UCL API
            UCLApiConnection conn = new UCLApiConnection(args[0]);

            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("roomid", args[2]);
            params.put("siteid", args[1]);

            Equipment[] gear = Equipment.searchAPI(conn, params);

            for (int i = 0; i < gear.length; i++) {
                System.out.println(gear[i].toString());
            }


        } else {
            System.err.println("Run with API key + siteid + roomid as arguments.");
            System.exit(1);
        }
    }
}