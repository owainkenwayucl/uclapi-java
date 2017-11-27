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
