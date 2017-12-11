UCL API Java library
====================

This repository contains code for accessing the [UCL API](https://uclapi.com/) using Java.  I started this work as part of [#LearnHack 4.0](https://sites.google.com/site/ucllearnhack/learnhack) but it shall continue!

*(You can find the tagged release from #LearnHack 4.0 [here](https://github.com/owainkenwayucl/uclapi-java/releases/tag/LearnHack4.0))*

This library provides access to and classes to represent objects returned by all the public (i.e. non-OAuth authenticated) UCL API end points.  As well as Java this libary of course works in languages that are hosted in the JVM, for example Clojure, Scala and Jython.

Installation:
------------

### Requirements:

* Java JDK (tested on 8, should work on 7)
* GNU Make (automated build)
* An API key (instructions on [UCL-API](https://uclapi.com/) website)
* JSON.simple (https://code.google.com/archive/p/json-simple/ or Ubuntu package `libjson-simple-java` to parse JSON objects.  If you only want to use `uclapi.UCLApiConnection` you don't need this, but will need to build that class manually in the normal way.)

Clone the repository and run `make`.

Add the directory to your java class path. <- **IMPORTANT**

You can then write a simple example program (`examples/search.java`) to do a query, as per the examples on the UCL API website:

```java
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
```

Compile this code and run it with your API key as the first argument, and part of a person's name as the second.

```none
$ javac examples/search.java
$ java examples/search <insert your API key here> Kenway
```

If all things are working this should print out a list of people whose names contain "Kenway".

### Clojure

Since Clojure can access Java classes, you also call this library from Clojure.  The following code does essentially the same as the above java code.

```clojure
; Person Search example in Clojure.
; Owain Kenway

; Get command line arguments (should be key followed by query)
(if (not= (count *command-line-args*) 2)
  ((.println System/err "Run with API key + query as arguments.")
   (System/exit 1))
)

(def token (first *command-line-args*))
(def query (second *command-line-args*))

; Now we've got this far import our library.

(import uclapi.UCLApiConnection)
(import uclapi.Person)

; Do our search and print out results in a single line!
(println (clojure.string/join "\n" (Person/searchAPI (UCLApiConnection. token) query)))
```

You can then run this code with:

```none
$ clojure examples/search.clj <insert your API key here> Kenway
```

### Scala

As with Clojure, you can call this library from Scala.  The following code does the same as the above two examples.

```scala
// Person Search example in Scala.
// Owain Kenway

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
```

You can compile this code with `scalac` and then run it.

```none
$ scalac examples/search.scala
$ scala examples.search <insert your API key here> Kenway
```

### Jython

And of course you can use Jython:

```python
# UCL API example in Jython
# This example searches for people.

# Owain Kenway

import uclapi.UCLApiConnection
import uclapi.Person

import sys

# Get our key + query.
if len(sys.argv) != 3:
	sys.exit("Call with query as argument")

key = sys.argv[1]
query = sys.argv[2]

# Make our connection and get our results.
conn = uclapi.UCLApiConnection(key)
results = uclapi.Person.searchAPI(conn, query)

# Print them out.
for a in results:
	print(a.toString())
```

And run it through `jython`.

```none
$ jython examples/search.py <insert your API key here> Kenway
```

Questions/Comments?
-------------------

The best way to contact me is on [Twitter](https://twitter.com/owainkenway), although you can open an Issue too.