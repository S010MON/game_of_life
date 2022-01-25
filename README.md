# Conway's Game of Life

A simple zero player game, made in Java with Swing, that follows the rules of the original game devised by John Conway

   - Any live cell with two or three live neighbours survives.
   - Any dead cell with three live neighbours becomes a live cell.
   - All other live cells die in the next generation. Similarly, all other dead cells stay dead.

More information: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

## Installation Instructions
Clone the repository and move into the game_of_life directory:
```bash
git clone https://github.com/S010MON/game_of_life.git
cd game_of_life
```
Compile the files:
```bash
javac Game.java View.java
```
Run the Game class to launch:
```bash
java Game
```

## Screenshot
![image](https://github.com/S010MON/game_of_life/blob/main/GOL_screenshot.png)
