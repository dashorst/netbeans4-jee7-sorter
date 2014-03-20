netbeans4-jee7-sorter
=====================

Example project for a stateless bean that couldn't be scaled...

Contains an ear/ejb project for deployment on glassfish (4), and a
client project that connects to the ejb bean and tries to sort 100,000
integers remotely in multiple threads.

I set the ejb poolsize max to 1 and expected glassfish to serialize all
calls to the sort ejb.
