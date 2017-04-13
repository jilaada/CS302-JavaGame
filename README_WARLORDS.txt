------------------------------------------------------------
-------README - COMPSYS 302 - WARLORDS GAME FINAL SUB-------
------------------------------------------------------------

This README would normally document the steps are necessary
to get your application up and running. This game was 
created by Group 10.

The MVC diagram, Final Submission Table, Report and 
PowerPoint can be found in the additional_documents folder
under the root directory

---------INSTRUCTIONS----------
Press A to move paddle left
Press S to move paddle right
Press cross to exit the game
-------------------------------

-------SETUP INFORMATION-------
-------------------------------
STARTING ECLIPSE
-------------------------------
1. 	Download the tagged commit and save into an appropriate 
	location 
2. 	Press "Control-Alt-T", the terminal should pop up
3. 	Copy and paste each command line by line:
    	PATH=$JAVA_HOME/jre/bin:$PATH
       	export PATH
       	eclipse-mars-java
4. 	The last line will open Eclipse Mars. A pop-up will 
	appear asking for a workspace ->
       	Click "Browse..." ->
       	Create a new folder in a directory of your choice 
       	(Desktop recommended) ->
       	Select the new folder as the new workspace ->
       	Click "Ok"
5. 	Close the welcome screen

-------------------------------
CHECK IF JAVAFX IS INSTALLED
-------------------------------
1. 	Click "New" (located below the exit button) ->
		Type "JavaFX" in the Wizard search ->
		If JavaFX appears go to step 3. else go to step 2.
2. 	Go to "Help" ->
		Eclipse Marketplace ->
		Search "JavaFX" on the search bar ->
		Find E(fx)clipse 2.4.0 ->
		Click "Install" ->
		Click "Confirm" and accept terms and conditions ->
		Download should start
3.  	From step 1. ->
		Select "JavaFX Project" in JavaFX folder ->
		Click "Next" ->
		In project name box type in "Prototype" ->
		Check that execution environment = JavaSE-1.8 ->
		Click "Next" ->
		Click the "Libraries" tab ->
		Click "Add Library..." ->
		Select JUnit ->
		Select JUnit4 ->
		Click "Finish" -> 
		Click "Finish" again
4. 	Expand "src" folder ->
		Right-click Applications package ->
		Click Delete and confirm
5. 	Go to file explorer -> 
		Navigate to the folder of the downloaded project ->
		Select all the packages ->
		Drag and drop to src folder -> 
		Select "Copy files and folders" ->
		Click "Ok"

-------------------------------
TO RUN APPLICATION
-------------------------------
1. 	In eclipse, select the project folder "Prototype" ->
		Right-click ->
		Click "Run As" ->
		Click "Java Application" ->
		Select "Main - model_classes" ->
		Click "Ok" ->
		Refer to how to play in INSTRUCTIONS

-------------------------------
TO RUN JUNIT TESTS
-------------------------------
1. 	Select the project folder "Prototype" ->
		Right-click ->
		Click "Run As" ->
		Click "JUnit Test"

-------------------------------
NOTES
-------------------------------
JUnit tests are available in Test_Root
We understand that the JUnit tests should implement the 
methods we have in our actual game, however because some 
methods were incomplete or have slightly varying 
implementations, we decided to take parts of our game code 
to complete the tests. You can. however, see that we have 
implemented the methods from Ball.java in BallAdapter.java














