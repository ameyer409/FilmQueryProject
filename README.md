# FilmQueryProject

## Project Description
In this program, the user queries a film database. The user is first given a menu to choose to search by movie ID, a keyword, or quit the program. 

If the user selects to search by id, they must provide an integer value for the id to search for. The program will then print the film data, including actors in the film, to the user and then return to the first menu. If the user provides an ID that is not in the database, the program will print a message to the user saying that the id was not found and return them to the first menu. If the user provides an invalid input, the program will tell them the input is invalid, and return to the original menu.

If the user selects to search by keyword, they will be prompted to provide a keyword or phrase to search on. The program will then return all films (and their corresponding data, including actors) that contain the keyword in their title or description, then return to the first menu. If the user supplies a keyword that is not found in any title or description, a message will print saying so and the program will return to the original menu.

The user can end the program by selecting the prompt to exit the program from the first menu.


## Technologies Used
Java
 - Connection, DriveManager, PreparedStatement and ResultSet Classes
 - Bind Variables
 - Try/Catch
mysql
 - SELECT statements
 - JOIN, WHERE, and LIKE
Maven


## Lessons Learned
 - One lesson I learned was to make sure to close all the sql connections after finishing accessing the database. I had an issue where I was calling a second method from inside a first method in a loop, and the method being called wasn't closing the connections so I was getting a too many connections error. At first I thought the issue was the calls themselves, so I rewrote the SELECT statement to use a JOIN instead of calling the second method. Even with that change the issue still occurred and that was when I realized that a method wasn't closing it's connections after opening them.


## How To Run
Run this program by pressing the green 'run' button at the top of eclipse or compile and run from the command line. Type the menu option for how you want to search the film database, then provide the requested id or keyword to search on.