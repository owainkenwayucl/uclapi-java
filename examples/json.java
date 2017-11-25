// UCL API example in Java
// This example queries the roombookings/rooms endpoint and converts
// the first response from JSON into a uclapi.Room object.
//
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import uclapi.Room;

import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class json {
    public static void main(String[] args) {
        if (args.length == 1) {

            // Get our response out of the UCL API
            UCLApiConnection conn = new UCLApiConnection(args[0]);
            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("date", "20171125");
            params.put("results_per_page", "1");
            String response = conn.queryAPI(UCLApiConnection.RoomRoomsEP, params);

            // Convert it into Room objects and print out.
            try {
                JSONParser p = new JSONParser();
                JSONObject responseObject = (JSONObject)p.parse(response);

                JSONArray rooms = (JSONArray)responseObject.get("rooms");
                int nRooms = rooms.size();
                System.out.println("Got " + Integer.toString(nRooms) + " rooms.");

                for (int i = 0; i < nRooms; i++) {
                    JSONObject jroom = (JSONObject)rooms.get(i);

                    Room room = new Room(jroom);
                    System.out.println(room.toString());
                }

            } catch (Exception e){
                System.err.println(e.toString());
                System.exit(5);
            }

        } else {
            System.err.println("Run with API key as argument.");
            System.exit(1);
        }
    }
}