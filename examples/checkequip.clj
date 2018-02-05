; Check equipment example in Clojure
; Owain Kenway

; Make sure we have the right number of command line arguments.
(if (= (count *command-line-args*) 3)
  (def token (first *command-line-args*))
  ((.println System/err "Run with API key, siteid and roomid as arguments.")(System/exit 1))
)

; Import necessary classes.
(import uclapi.UCLApiConnection)
(import uclapi.Equipment)
(import java.util.Hashtable)

; Put search parameters into a Hashtable.
(def params (Hashtable.))
(.put params "siteid" (second *command-line-args*))
(.put params "roomid" (.get *command-line-args* 2))

; Do our search and print results.
(println (clojure.string/join "\n" (Equipment/searchAPI (UCLApiConnection. token) params)))