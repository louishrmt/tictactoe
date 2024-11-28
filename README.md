"Tic-Tac-Toe" application for Easi.

To launch the application, you'll need _Android Studio_ on your device.

Once you have it, clone this repository and open it on _Android Studio_. Then, on "File", click on "Synch Project with Gradle Files".

After the synchronization, you can launch the application to start a game.

Enjoy ;)

PS : If you want to change the grid/matrix of the game, here's the different code part you need to change.
1. First, in app>Model>AppConstants, you need to change the size of the matrix ("MATRIX_SIZE");
2. Then, in res>layout>activity_game, you must change the "numColumns" number to match the matrix size;
3. Last but not least, you need to adjust the size of the square in res>layout>game_square_item to fit in an area of 316dp by editing the height and the width of the CardView (you can simply calculate this by dividing 316 by the size of your matrix);
