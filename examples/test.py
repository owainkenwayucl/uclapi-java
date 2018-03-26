# UCL API example in Jython

# Owain Kenway

import uclapi.UCLApiConnection
import java.util.Hashtable

import sys

# Get our key + query.
if len(sys.argv) != 2:
	sys.exit("Run with API key as an argument.")

key = sys.argv[1]
params = java.util.Hashtable()
params.put("date", "20171125")
params.put("results_per_page", "1")

# Make our connection and get our results.
conn = uclapi.UCLApiConnection(key)
results = conn.queryAPI(uclapi.UCLApiConnection.RoomBookingsEP, params)

# Print them out.
print(results)
