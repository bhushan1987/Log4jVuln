

### The Log4J Issue
This is an extremely basic Spring Boot app which demonstrate the Log4j vulnerability.
The world knows now that there is a security vulnerability in log4j versions 2.10 to 2.14.1 (please check the apache site for exact versions).

This code uses log4j 2.14.1. A REST API accepts the user parameter and simply logs it.
For simple input values, nothing can be seen and program works fine.

However, if we change the param value to something like this -> *_${jndi:ldap://localhost:8080/test}_*,
the logger starts contacting this jndi endpoint.<br>I've no idea why its done like this, may be developer wanted to provide a good feature but ended up putting in this vulnerability. 
Encode the above jndi url and pass it as param as follows: <br>
http://localhost:8080/logme?input=%24%7Bjndi%3Aldap%3A%2F%2Flocalhost%3A8080%2Ftest%7D

**Mitigation**<br>
One way is to pass LOG4J_FORMAT_MSG_NO_LOOKUPS environment variable by setting its value to true.
<br>Other workaround it by setting a system property _log4j2.formatMsgNoLookups=true_
Note that these are workarounds and may not solve the vulnerability properly<br>
https://logging.apache.org/log4j/2.x/security.html
