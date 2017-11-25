// UCL API example in Java
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import java.util.Hashtable;

public class test {
    public static void main(String[] args) {
        if (args.length == 1) {
            UCLApiConnection conn = new UCLApiConnection(args[0]);
            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("date", "20171125");
            params.put("results_per_page", "1");
            String response = conn.queryAPI(UCLApiConnection.RoomBookingsEP, params);
            System.out.println(response);
        } else {
            System.err.println("Run with API key as argument.");
            System.exit(1);
        }
    }
}