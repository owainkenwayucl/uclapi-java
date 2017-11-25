/**
 * This class represents a connection to the UCL API.
 * Owain Kenway, November 2017
 */
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