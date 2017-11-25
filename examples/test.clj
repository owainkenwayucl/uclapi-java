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