# uscFit
Alina Abidi, Anjan Amarnath, Rupali Bahl, Devika Kumar, Qianze Zhang

Device: Pixel 2
username: tianqinz@usc.edu
password: uscfit


To deploy and run application:
1. Clone this repository and open it in Android Studio (File > Open)
2. Run the application (Run > Run...) selecting the above device as the target device.
3. When the application opens, log in with the above credentials.

Improvements since 2.3:
-New user flow (when a user signs up, they are taken to a new screen, where they enter their personal info and select a picture, and this is all saved in the database)

-Workout notifications are now scheduled for 3 hours before a planned workout and upon the completion of a workout 

-Workouts have been fully integrated within Firebase

-Users can now indicate whether a workout is complete or not. By selecting complete, the background of the workout display is changed (creating a visual difference between completed and not completed workouts) and updates the database with the completion status of the respective workout.

-Badge Functionality/Display -(badges are stored and read from database and badges are added whenever a workout is finished and a user achieves his or her goals, different types of badges are generated for different types of achievements, set up the functionality so that it can be used/referenced by other parts of the app)

-Storing daily steps in database (this is not accessible by the user and cannot be demoed)

-Displaying steps data for the past week on a bar graph on StepsDisplay interface
