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
    public static final String RoomRoomsEP = "roombookings/rooms";

    /**
     * Basic constructor, sets API key for this connection.
     * 
     * @param key The UCL API key for this connection.
     */
    public UCLApiConnection(String key) {
        this.APIKey = new String(key);
    }

    /**
     * Perform a generic API query on our connection.
     *
     * @param Endpoint The URL endpoint (e.g. roombookings/bookings)
     * @param Parameters The parameters to query with.
     * @return A string containing the JSON response.
     */
    public String queryAPI(String Endpoint, Hashtable<String, String> Parameters) {

        // Unpack the Hashtable of parameters into a query string.
        String query = "";
        for (String key : Parameters.keySet()) {
            String value = (String) Parameters.get(key);
            query = new String(query + "&" + key + "=" + value);
        }

        // Construct a URL out of base url, our endpoint, our API key and the query.
        String url = new String(UCLApiEndpoint + Endpoint + "?token=" + APIKey + query);

        // It's not clear this is necessary but I had enough encoding horrors at #hackbentham...
        String charset = "UTF-8"; 

        try {

            // Open our connection.
            URLConnection conn = new URL(url).openConnection();
            conn.setRequestProperty("Accept-Charset", charset);
            conn.addRequestProperty("User-Agent", "Owain's UCL API wrapper for Java");
            InputStream response = conn.getInputStream();

            // Pull data out and return it.
            try (Scanner rReader = new Scanner(response)) {
                String rBody = rReader.useDelimiter("\\A").next();
                return rBody;
            }

        } catch(Exception e) {
            // Annoyingly we don't get our Error response, just a 403 exception which is less helpful.
            System.err.println(e.toString());
            System.exit(1);
        }

        // If we get here something has gone very wrong...
        return "";
    }

}
