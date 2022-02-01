# Project #1: Playlist Analyzer

* Author: Anthony Goeckner
* Class: CS121 Section #005
* Semester: Fall 2021

## Overview

Program asks the user for 3 different songs, and goes through the info returning the average playtime, the song closest to 4 minutes, and a list of the songs sorted by playtime

## Reflection

I ran into one notable issue, my if statement logic was a bit off. When all three of the songs had the same length, it would duplicate the first song to fill all three spots. This took me the most time to work out, but I found all 3 of my conditions defaulted to the first song when they were equal. To change this I had to add an = in the condition to change it slightly.
To compare song lengths I used a trick we learned when dealing with a decimal tolerance, which was taking the song's playtime and subtracting 240 before taking the absolute value so I did not have to deal with negative numbers.

## Compiling and Using

To use this code you must compile both the Playlist.java file and the Song.java file simultaneously, using the line 
$ javac Playlist.java Song.java
Next, to run the program you can just run the Playlist class, using the line
$ java Playlist
This program will ask for input repeatedly. It asks for the title, artist, album and playtime of three songs, in order. Afterwards it will print the rest of the information on it's own.