#Fruchterman & Reingold Algorithm Implementation

This algorithm discovered from Fruchterman and Reingold and is a Force-directed
graph drawing algorithm. 

##Getting Started

In order to run this project from a linux environment you need the files below:

1. **Node.java**, 
2. **myList.java**,
3. **Point.java**,
4. **ReadGraph.java**,
5. **FruchtermanReingold.java**,
6. **GraphImplementation.java**

###Compile command
**```
javac *.java
```**

###Run command
Without filename type <br/><br/>
**```java GraphImplementation```** 
<br/> <br/> or type <br/><br/>
**```java GraphImplementation filename```**
<br/><br/> if you want to add input file from command line.

##Compile and Run with Bash
If you want to use the bash files existing in the project (compile.sh, 
run.sh/runFile.sh) follow the instructions below. You need to add the files in
the same directory with **.java** files.

###Compile from Bash
In order to compile with bash file execute the commands below, in the given order

**```chmod +x compile.sh```**<br/>
**```./compile.sh```**

###Run from Bash

**```chmod +x run.sh**```**<br/>
**```./run.sh**```**


###Run from Bash with input from command line
**```chmod +x runFile.sh```**<br/>
**```./runFile.sh```**

####!Attention
File runFile.sh already contains a test file. If you want to test with other
file you need to change filename in runFile.sh.

##Input File Format
The input file format used in this implementation is DIMACS. An example for an 
input graph follows,<br/>
```
c Input Graph
```
<br/>
```
p edge 6 5
```
<br/>
```
e 5 1
```
<br/>
```
e 6 1
```
<br/>
```
e 1 2 
```
<br/>
```
e 2 3
```
<br/>
```
e 2 4
```

##Output
The output is a .dot file with the calculated coordinates foe each vertex of the 
input graph. If you want to visualize your result you can use GraphViz. A link is
added below and the input of this program is .dot file.

 [GraphViz](https://www.graphviz.org/)

After installing **GraphViz** you execute the command below:<br/>
**```fdp -s file.dot -Tpdf -o result.pdf```** 