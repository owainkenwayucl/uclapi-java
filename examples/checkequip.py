# UCL API example in Jython
# This example searches for  for equipment in a room.

# Owain Kenway

import uclapi.UCLApiConnection
import uclapi.Equipment
import java.util.Hashtable

import sys

# Get our key + query.
if len(sys.argv) != 4:
	sys.exit("Run with API key + siteid + roomid as arguments.")

key = sys.argv[1]
params = java.util.Hashtable()
params.put("siteid", sys.argv[2])
params.put("roomid", sys.argv[3])

# Make our connection and get our results.
conn = uclapi.UCLApiConnection(key)
results = uclapi.Equipment.searchAPI(conn, params)

# Print them out.
for a in results:
	print(a.toString())
