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
* JSON.simple (https://code.google.com/archive/p/json-simple/ or Ubuntu package `libjson-simple-java` to parse JSON objects.  If you only want to use `uclapi.UCLApiConnection` you don't need this, but will need to build that class manually in the normal way.)

Clone the repository and run `make`.

Add the directory to your java class path.

You can then write a simple example program to do a query, as per the examples on the UCL API website:

```java
// UCL API example in Java
// Owain Kenway

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

### Clojure

Since Clojure can access Java classes, you also call this library from Clojure.  The following code does essentially the same as the above java code.

```clojure
; UCL API example in Clojure
; Owain Kenway

(if (= (count *command-line-args*) 1)
  (def token (first *command-line-args*))
  ((.println System/err "Run with API key as argument.")(System/exit 1))
)

(import uclapi.UCLApiConnection)
(import java.util.Hashtable)

(def conn (UCLApiConnection. token))
(def params (Hashtable.))
(.put params "date" "20171125")
(.put params "results_per_page" "1")

(def response (.queryAPI conn UCLApiConnection/RoomBookingsEP params))
(println response)
```

You can then run this code with:

```none
$ clojure test.clj <insert your API key here>
```
