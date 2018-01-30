# UCL API example in Jython
# This example searches for rooms.

# Owain Kenway

import uclapi.UCLApiConnection
import uclapi.Room
import java.util.Hashtable

import sys

# Get our key + query.
if len(sys.argv) != 3:
	sys.exit("Call with key and date as argument")

key = sys.argv[1]
params = java.util.Hashtable()
params.put("date", sys.argv[2])

# Make our connection and get our results.
conn = uclapi.UCLApiConnection(key)
results = uclapi.Room.searchAPI(conn, params)

# Print them out.
for a in results:
	print(a.toString())
