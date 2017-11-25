/**
 * This class represents a connection to the UCL API.
 * @author Owain Kenway
 */

package uclapi;

public class UCLApiConnection {

    // Our API key
    private String APIKey;

    /**
     * Basic constructor, sets API key for this connection.
     * 
     * @param key The UCL API key for this connection.
     */
    public UCLApiConnection(String key) {
        this.APIKey = new String(key);
    }

}