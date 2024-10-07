# Lesson 4 (Networking)

Threads are indepedent of each other, meaning they do not talk or synchronise with each other as to who will do the work first. Its based on whichever is the fastest.

Threads only has one method: **public void run(){}**

Concurrent does not equal to Parallel

Non-blocking IO can be used to perform concurrency. Detect if the thread is not writing then is it reading? There is no fixed format compared to blocking IO where you must receive the msg from client before server respond back or vice versa. NIO allows for either client or server to send msg first, no fixed format.