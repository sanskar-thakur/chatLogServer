# Chat Log Server
A Spring(java) based microservice to save/retrieve chat logs on/from the server.

This server presents an HTTP interface with the following commands. 

## POST /chatlogs/{user}
Creates a new chatlog entry for the user with userID as {user}. <br />
The POST data can either be url encoded or JSON encoded. <br />
<br />
The data contains the following fields: <br />
■ message - a String representing the message text <br />
■ timestamp - a Long representing the timestamp <br />
■ isSent - a Boolean/Integer representing if this message was sent by the user or received by the user. <br />
<br />
The response from the message is a unique messageID that we can refer to the message by.

## GET /chatlogs/{user}
Returns chatlogs for the given user. <br />
These are returned in reverse timeorder (most recent messages first). <br />
<br />
Takes two optional parameters. <br />
■ limit - an Integer stating how many messages should return. Default to 10. <br />
■ start - a key of the same type as messageID to determine where to start from.  <br />
This is to help implement pagination. If not set, it returns the most recent messages. <br />

## DELETE /chatlogs/{user} 
Deletes all the chat logs for a given user. 

## DELETE /chatlogs/{user}/{msgid}
Delete just the given chatlog for a given user. <br />
Returns an appropriate HTTP error response if the msgid is not found.
