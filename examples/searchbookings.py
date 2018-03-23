# UCL API example in Jython
# This example queries the roombookings/rooms endpoint and prints results.

# Owain Kenway

import uclapi.UCLApiConnection
import uclapi.Booking
import java.util.Hashtable

import sys

# Get our key + query.
if len(sys.argv) != 3:
	sys.exit("Call with key and date in [YYYYMMDD] as arguments")

key = sys.argv[1]
params = java.util.Hashtable()
params.put("date", sys.argv[2])

# Make our connection and get our results.
conn = uclapi.UCLApiConnection(key)
results = uclapi.Booking.searchAPI(conn, params)

# Print them out.
for a in results:
	print(a.toString())
