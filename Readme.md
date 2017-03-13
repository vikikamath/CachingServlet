## Requirements

This emerged out of a question I posted on [StackOverflow | http://stackoverflow.com/questions/42522611/how-can-tomcat-server-cache-a-post-request]

- Create a Java Server Example that caches POST Response and responds to GET with identical URL

- I wanted to Create a Backend-less option so I am using Simple File based caching.

- Create a barebone example without any frameworks on either sides (client or Server). However, I had to use log4j to get logging work. If you have any suggestions or PR I am open to "fixing" this.

- Use Maven so that it can be run with IDE.


This was more of an idea I wanted to tinker around. Do not do this in Production Environment. Any suggestions welcome!


## Flow

GET /  ==> Landing Page
Landing Page ==> POST /hello/abc  with {{data}} ==>  Cache POST data and Respond with {{data}}

... in another browser instance (or tab)

GET /hello/abc ==> Respond with Cached {{data}}

... Server Shutdown : Write all accumulated cache to Java Properties File

... Server Startup : Read Properties file into HashMap to serve requests.



## Clean Install and Run

!! Must have Maven installed !!

`git clone <<repoName>> && cd <<repoName>> && mvn install tomcat7:run`