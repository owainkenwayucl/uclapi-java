# UCL API example in Jython
# This example searches for free desktops in cluster rooms.

# Owain Kenway

import uclapi.UCLApiConnection
import uclapi.ClusterRoom
import java.util.Hashtable

import sys

# Get our key + query.
if len(sys.argv) != 4:
	sys.exit("Call with key and start/end dates as arguments")

key = sys.argv[1]
params = java.util.Hashtable()
params.put("start_datetime", sys.argv[2])
params.put("end_datetime", sys.argv[3])

# Make our connection and get our results.
conn = uclapi.UCLApiConnection(key)
results = uclapi.ClusterRoom.searchAPI(conn, params)

# Print them out.
for a in results:
	print(a.toString())
