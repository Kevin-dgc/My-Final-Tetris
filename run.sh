#!/bin/bash
cd game || exit  # Change to the 'game' directory and exit if it fails
javac *.java     # Compile all Java files in the 'game' directory
java Main        # Run the Main class

