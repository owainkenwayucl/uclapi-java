// UCL API example in Java
// This example queries the roombookings/rooms endpoint and prints results.
//
// Owain Kenway

package examples

import uclapi.UCLApiConnection
import uclapi.Booking

import java.util.Hashtable

object searchbookings extends App {

    if (args.length != 2) {
        Console.err.println("Run with API key + date as arguments.")
        System.exit(1)
    }

    val apikey = args(0)
    val params = new Hashtable[String, String]
    params.put("date", args(1))

    val conn = new UCLApiConnection(apikey)

    val results = Booking.searchAPI(conn, params)

    for (i <- 0 to (results.length - 1))
        println(results(i).toString())

}