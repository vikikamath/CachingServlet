#Caching Servlet 

## Requirements

Answering my own question on [StackOverflow](http://stackoverflow.com/questions/42522611/how-can-tomcat-server-cache-a-post-request)

- Create a Java Server Example that caches POST Response and responds to GET with identical URL

- I wanted to Create a Backend-less option so I am using Simple File based caching.

- Create a barebone example without any frameworks on either sides (client or Server). However, I had to use log4j to get logging work. If you have any suggestions or PR I am open to "fixing" this.

- Use Maven so that it can be run with IDE.


This was more of an idea I wanted to tinker around. **Do not do this in Production Environment**. Any suggestions welcome!


## Flow

| Request Method | URI | Process / Result|
|----------------|-----|-----------------|
| GET            |/hello| Landing Page|
|POST            |/hello/abc with `{JSON: data}`| Cache Request UI -> `{JSON: data}`|
|GET(in another Tab)| /hello/abc| Respond with Cached Data: `{JSON: data}`|


> Server Shutdown : Write all accumulated cache to Java Properties File

> Server Startup : Read Properties file into HashMap to serve requests.



## Clean Install and Run

> Prerequisite: Maven Installation

```shell
git clone <<repoName>> && cd <<repoName>> && mvn install tomcat7:run
```
