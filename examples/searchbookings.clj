; Room Search example in Clojure
; Owain Kenway

; Make sure we have the right number of command line arguments.
(if (= (count *command-line-args*) 2)
  (def token (first *command-line-args*))
  ((.println System/err "Run with API key and date as arguments.")(System/exit 1))
)

; Import necessary classes.
(import uclapi.UCLApiConnection)
(import uclapi.Booking)
(import java.util.Hashtable)

; Put search parameters into a Hashtable.
(def params (Hashtable.))
(.put params "date" (second *command-line-args*))

; Do our search and print results.
(println (clojure.string/join "\n" (Booking/searchAPI (UCLApiConnection. token) params)))