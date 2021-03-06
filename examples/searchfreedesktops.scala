// UCL API example in Scala.
// This example searchs for free desktops.
//
// Owain Kenway

package examples

import uclapi.UCLApiConnection
import uclapi.ClusterRoom

import java.util.Hashtable

object searchfreedesktops extends App {

    if (args.length != 3) {
        Console.err.println("Run with API key + start + end datetimes as arguments.")
        System.exit(1)
    }

    val apikey = args(0)
    val params = new Hashtable[String, String]
    
    params.put("start_datetime", args(1))
    params.put("end_datetime", args(2))

    val conn = new UCLApiConnection(apikey)

    val results = ClusterRoom.searchAPI(conn, params)

    for (i <- 0 to (results.length - 1))
        println(results(i).toString())

}