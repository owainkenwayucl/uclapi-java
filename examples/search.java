// UCL API example in Java
// This example searchs for people.
//
// Owain Kenway

package examples;

import uclapi.UCLApiConnection;
import uclapi.Person;


public class search {
    public static void main(String[] args) {
        if (args.length == 2) {

            // Get our response out of the UCL API
            UCLApiConnection conn = new UCLApiConnection(args[0]);

            Person[] people = Person.searchAPI(conn, args[1]);

            for (int i = 0; i < people.length; i++) {
                System.out.println(people[i].toString());
            }


        } else {
            System.err.println("Run with API key + query as arguments.");
            System.exit(1);
        }
    }
}