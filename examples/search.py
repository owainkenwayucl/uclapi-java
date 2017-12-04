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
