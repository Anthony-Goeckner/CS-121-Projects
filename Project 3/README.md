# Project 3: Social Media Site Data

* Author: Anthony Goeckner
* Class: CS121 Section #005
* Semester: Fall 2021

## Overview

Textbook is a text based social media platform, made from solely text posts
and comments. This application allows for the user to create, remove,
comment on, and display posts from the terminal.

## Reflection

I chose to do my development from the bottom up. It seemed the easiest 
to start this way, as I would not have to worry about testing the 
driver class with dummy classes for Textbook and Post. The inclusion
of the Unit testers made this much more doable as well. I had one
problem working with Post, when the Unit tester was telling me I 
failed the test, but the actual and expected values were identical.
It took me some time to figure out that when getting the file contents,
the nextLine() function of a Scanner automatically trims the newline
from the String it returns.
Textbook gave me little to no issue, but I had some trouble using it 
alongside the driver. When creating a Textbook object, I accidentally 
placed it inside of the main while loop of the driver, causing it to 
be recreated every time instead of saving the post. I also had trouble
getting integers from the Scanner, not realizing that using nextInt() 
left the newline, which would get pushed through into the main menu,
which left a seemingly random "invalid selection" notice.

## Compiling and Using

To use this program, first ensure that all of the files are in the same directory.
This includes Post.java, TextBook.java, TextBookDriver.java, PostInterface.java,
TextBookInterface.java, and an empty directory named posts. In this directory, 
call the function
javac *.java
to compile all of the files. Next, use
java TextBookDriver
to run the program. From here, the program will ask you to enter your name, and provide 
you with a menu to select options from. Note that you must have added posts with (A)
before you can use any of the other functionalities of the program.