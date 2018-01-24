// Person Search example in Scala.
// Owain Kenway

package examples

import uclapi.UCLApiConnection
import uclapi.Room

import java.util.Hashtable

object searchrooms extends App {

    if (args.length != 2) {
        Console.err.println("Run with API key + date [YYYYMMDD] as arguments.")
        System.exit(1)
    }

    val apikey = args(0)
    val params = new Hashtable[String, String]
    params.put("date", args(1))

    val results = Room.searchAPI(new UCLApiConnection(apikey), params)

    for (i <- 0 to (results.length - 1))
        println(results(i).toString())
}