// UCL API example in Java
// This example searchs for people.
//
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import uclapi.Person;

import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class search {
    public static void main(String[] args) {
        if (args.length == 2) {

            // Get our response out of the UCL API
            UCLApiConnection conn = new UCLApiConnection(args[0]);
            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("query", args[1]);
            String response = conn.queryAPI(UCLApiConnection.SearchPeopleEP, params);

            // Convert it into Person objects and print out.
            try {
                JSONParser p = new JSONParser();
                JSONObject responseObject = (JSONObject)p.parse(response);

                JSONArray people = (JSONArray)responseObject.get("people");
                int nPeople = people.size();
                System.out.println("Got " + Integer.toString(nPeople) + " people.");

                for (int i = 0; i < nPeople; i++) {
                    JSONObject jperson = (JSONObject)people.get(i);

                    Person person = new Person(jperson);
                    System.out.println(person.toString());
                }

            } catch (Exception e){
                System.err.println(e.toString());
                System.exit(5);
            }

        } else {
            System.err.println("Run with API key + query as arguments.");
            System.exit(1);
        }
    }
}