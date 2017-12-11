; Person Search example in Scala.
; Owain Kenway

package examples

import uclapi.UCLApiConnection
import uclapi.Person

object search extends App {

    if (args.length != 2) {
        Console.err.println("Run with API key + query as arguments.")
        System.exit(1)
    }

    val apikey = args(0)
    val query = args(1)

    val results = Person.searchAPI(new UCLApiConnection(apikey), query)

    for (i <- 0 to (results.length - 1))
        println(results(i).toString())
}