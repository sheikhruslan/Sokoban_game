# Sokoban Game

## Overview
Sokoban is a classic puzzle game where the player pushes boxes to designated target locations. The player can only push the boxes and cannot pull them. The objective is to place all the boxes on the target locations to win the game. This project is a Java implementation of Sokoban, designed to enhance coding skills in arrays, 2D arrays, parameter passing, and method construction and usage.

## Game Explanation
Sokoban is a role-playing game where the player navigates a grid-based map. The player can move up, down, left, or right, but can only push boxes, not pull them. The player can only push one box at a time and can only move to an empty space or a target location. The game is won when all target locations have a box on them. The game is lost if the player gets stuck and cannot make any more valid moves.

## Features
Directional Movement: The player can move in four directions using the keys W (up), A (left), S (down), and D (right).
Box Pushing: The player can push boxes to empty spaces or target locations.
Map Reading: The game reads the map layout from a text file.
Game State Management: The game checks for win conditions and allows for restarting or quitting the game.
Help Menu: A help menu is available to guide the player on controls and game elements.
Multiple Maps: The project includes multiple map files, allowing for different levels and challenges.


## This project provides practice in the following areas:

Arrays and 2D Arrays: The game map is represented using 2D arrays, and various operations are performed on these arrays.
Parameter Passing: Methods are designed to take parameters and return values, facilitating modular code.
Method Construction and Usage: The project involves constructing and using multiple methods to handle different aspects of the game, such as reading the map, moving the player, and checking game conditions.
How to Play
Start the Game: Run the Sokoban class to start the game.
Move the Player: Use the keys W, A, S, and D to move the player up, left, down, and right, respectively.
Push Boxes: Move the player next to a box and push it towards an empty space or a target location.
Win the Game: Place all the boxes on the target locations to win the game.
Restart or Quit: Press 'r' to restart the game or 'q' to quit.


## Code Structure
Main Class: Sokoban - Contains the main method and the core game loop.
Methods:
runApp(): Initializes the game and handles the main game loop.
printHelp(): Displays the help menu.
readValidInput(): Reads and validates user input.
gameOver(): Checks if the game is over.
numberOfRows(): Counts the number of rows in the map file.
readmap(): Reads the map from a file.
findPlayer(): Finds the player's position on the map.
printMap(): Prints the current state of the map.
isValid(): Checks if a move is valid.
movePlayer(): Moves the player on the map.
moveBox(): Moves a box on the map.
fixMap(): Fixes the map by updating certain elements based on the contents of another map.

## Maps
The project includes multiple map files located in the maps folder. Each map file represents a different level with unique challenges. You can switch between maps by changing the map file name in the runApp() method.

## Conclusion
This Sokoban project was a great way to practice and enhance coding skills in Java, particularly in the areas of arrays, 2D arrays, parameter passing, and method construction and usage. Enjoy the challenge of solving different levels and improving your problem-solving skills!
