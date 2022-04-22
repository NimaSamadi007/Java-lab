# **Java Laboratory Course**
<font size="4"> <strong> 1400-1401 Spring Semester </strong> </font>

<font size="4"> <strong> Dr.Matin Hashemi </strong> </font>

<font size="3"> <strong> Nima Samadi & Milad Saadat </strong> </font>

<font size="3"> <strong> Group 2 </strong> </font>

___
Each Lab is in a seperate folder. Here some additional explanations are provided to help you better understand the structure of the files and codes.


## LAB1:
Q1 folder is the `Hello World` program. 

Q2 folder is `getting input from stdin` program.

And finally, Q3 is `mathematical computations` program. Plus, we have done the extra part!

- If first operand is divided by zero, you will be shown "Infinity" but the program still works!

- `Prime Command` will print every single Prime number lower than the given number, regardless whether it is Prime or not.

## LAB2:
This folder contains two sub folder namely `App` and `jaba2`. The `App` folder is my (Nima Samadi) implementation of this labarotary. It contains both mandatory and extra parts of this lap. 

`jaba2` folder is Milad Saadat's implementations of the lab. It also contains extra parts of the lab along with the mandatory ones.

The codes are pretty self-explanatory and easy to understand :)

## LAB3:
All classes are in the `src` folder and executable files are in `out` folder. To test the performance of the code, we have used the following tree:

![](./Images/1.png)

The code is figuring out if there is a path between node 0 and 3 or vice versa. 

Also we have tested the `DirectedGraph` class. The following graph has been tested:

![](./Images/2.png)

All tests are done in `Main.java` class.

## LAB4:
Just like previous labs, all codes are in the `src` folder and executables are in the `bin` folder. `App.java` file is used to test whole system. All 10 tests are provided in this file. `FileHandler.java` class is the main class to handle interactions with file and is used to contruct the tree. 

Along with the give testcase, I tested the code with these two trees. Plus there are more tests to check if exceptions are handled correctly.

|![](./Images/1.png)|![](./Images/3.png)|
|:-------:|:-------:|

Following exceptions are handled:

1. No find directive written
2. Empty find section
3. More or less than 2 elements in fine or tree section
4. Nodes in find section that don't exist in Tree section
5. Unabling to write to file or read it
