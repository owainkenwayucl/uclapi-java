UCL API Java library
====================

This repository contains code for accessing the [UCL API](https://uclapi.com/) using Java.  I am doing this work as part of [#LearnHack 4.0](https://sites.google.com/site/ucllearnhack/learnhack).

Plan:
----

* Basic access to non-private (i.e. no oauth) data (because I don't know anything about oauth from a development perspective).

* *[stretch]* Classes to represent JSON data in Java, parsed by a JSON parser, probably JSON.simple.

* *[stretch]* Example code in Java.

* *[stretch]* Example code in Clojure.

Installation:
------------

### Requirements:

* Java JDK (tested on 8, should work on 7)
* GNU Make (automated build)
* An API key (instructions on [UCL-API](https://uclapi.com/) website)

Clone the archive and run `make`.

You can then write a simple example program to do a query, as per the examples on the UCL API website:

```java
import uclapi.UCLApiConnection;
import java.util.Hashtable;

public class test {
    public static void main(String[] args) {
        if (args.length == 1) {
            UCLApiConnection conn = new UCLApiConnection(args[0]);
            Hashtable<String, String> params = new Hashtable<String, String>();
            params.put("date", "20171125");
            params.put("results_per_page", "1");
            String response = conn.queryAPI(UCLApiConnection.RoomBookingsEP, params);
            System.out.println(response);
        } else {
            System.err.println("Run with API key as argument.");
            System.exit(1);
        }
    }
}
```

Compile this code and run it with your API key as the first argument:

```none
$ javac test.java
$ java test <insert your API key here>
```

If all things are working this should print out a JSON file with the room  bookings for the date above.