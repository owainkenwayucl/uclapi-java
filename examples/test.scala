// UCL API example in Scala.
// Owain Kenway

package examples

import uclapi.UCLApiConnection
import java.util.Hashtable

object test extends App {

    if (args.length != 1) {
        Console.err.println("Run with API key as argument.")
        System.exit(1)
    }

    val apikey = args(0)
    val params = new Hashtable[String, String]
    params.put("date", "20171125")
    params.put("results_per_page", "1")

    val conn = new UCLApiConnection(apikey)
    val results = conn.queryAPI(UCLApiConnection.RoomBookingsEP, params)

    println(results)
}