// UCL API example in Scala.
// This example searchs for equipment in a room.
//
// Owain Kenway

package examples

import uclapi.UCLApiConnection
import uclapi.Equipment

import java.util.Hashtable

object checkequip extends App {

    if (args.length != 3) {
        Console.err.println("Run with API key + siteid + roomid as arguments.")
        System.exit(1)
    }

    val apikey = args(0)
    val params = new Hashtable[String, String]
    params.put("roomid", args(2))
    params.put("siteid", args(1))

    val conn = new UCLApiConnection(apikey)

    val results = Equipment.searchAPI(conn, params)

    for (i <- 0 to (results.length - 1))
        println(results(i).toString())

}