/**
 * This class represents a connection to the UCL API.
 * @author Owain Kenway
 */

package uclapi;

import java.net.URLConnection;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Hashtable;

public class UCLApiConnection {

    // Our API key
    private String APIKey;
    
    // URL base, public so for testing purpose you can modify it but generally don't!
    public String UCLApiEndpoint = new String("https://uclapi.com/");

    // Constants (e.g. endpoints)
    public static final String RoomBookingsEP = "roombookings/bookings";

    /**
     * Basic constructor, sets API key for this connection.
     * 
     * @param key The UCL API key for this connection.
     */
    public UCLApiConnection(String key) {
        this.APIKey = new String(key);
    }

    /**
     * Perform a generic API query on our.
     *
     * @param Endpoint The URL endpoint (e.g. roombookings/bookings)
     * @param Parameters The parameters to query with.
     * @return A string containing the JSON response.
     */
    public String queryAPI(String Endpoint, Hashtable<String, String> Parameters) {
        String query = "";
        for (String key : Parameters.keySet()) {
        String value = (String) Parameters.get(key);
        query = new String(query + "&" + key + "=" + value);
        }
        String url = new String(UCLApiEndpoint + Endpoint + "?token=" + APIKey + query);
        String charset = "UTF-8";
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.setRequestProperty("Accept-Charset", charset);

            InputStream response = conn.getInputStream();

            try (Scanner rReader = new Scanner(response)) {
                String rBody = rReader.useDelimiter("\\A").next();
                return rBody;
            }

        } catch(Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
        return new String("FAIL");
    }

}
