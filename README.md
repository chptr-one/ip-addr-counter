# ip-addr-counter
[Test job](https://github.com/Ecwid/new-job/blob/master/IP-Addr-Counter.md) for the position of the Java developer.

## Task description
A simple text file with IPv4 addresses is given. One line is one address, something like this:

```
145.67.23.4
8.34.5.23
89.54.3.124
89.54.3.124
3.45.71.5
...
```
The file size is not limited and can occupy tens and hundreds of gigabytes.

It is necessary to calculate the number of unique IP addresses in this file, consuming as little memory and time as
possible. There is a "naive" algorithm for solving this task (we read strings and put it in HashSet), it is desirable
that your implementation is better than this simple, naive algorithm.

## Test case

https://ecwid-vgv-storage.s3.eu-central-1.amazonaws.com/ip_addresses.zip

WARNING! This file is about 20 GB, and is unpacking approximately 120 GB. 
It consists of 8 billions lines with 1 billion unique lines.

## Dependencies
* Maven
* JUnit5 Jupiter 
* [Progressbar](https://github.com/ctongfei/progressbar)

## Compilation and run
To compile in .jar with dependencies (Linux) :

`mvn clean compile assembly:single`

To run app:

`java -jar target/ip-addr-counter-1.0-SNAPSHOT-jar-with-dependencies.jar -file path_to_file`