# uscFit
Alina Abidi, Anjan Amarnath, Rupali Bahl, Devika Kumar, Qianze Zhang

Device: Pixel 2
username: tianqinz@usc.edu
password: uscfit


To deploy and run application:
1. Clone this repository and open it in Android Studio (File > Open)
2. Run the application (Run > Run...) selecting the above device as the target device.
3. When the application opens, log in with the above credentials.

# uscFit
Alina Abidi, Anjan Amarnath, Rupali Bahl, Devika Kumar, Qianze Zhang

Device: Pixel 2
username: tianqinz@usc.edu
password: uscfit


To deploy and run application:
1. Clone this repository and open it in Android Studio (File > Open)
2. Run the application (Run > Run...) selecting the above device as the target device.
3. When the application opens, log in with the above credentials.

Improvements since 2.4:
Sign out and persisting login data
Day At a Glance no longer hardcoded (pulls from DB and updates based on workout completion, steps)
Deadlines added to goals (User can edit deadlines and they are displayed with each goal on the goals page)
Tested Steps on an Android device (vs emulator) to ensure it was calculating and displaying steps properly (* We have a video demo since none of use Androids)
Badge Functionality completely integrated into code (when a user completes a workout that completes a goal, a new badge is added, changed criteria for adding different levels of badges for different types of achievements)
Goal Functionality enhanced (user must now pick from a preset list of activities when setting a goal in order to ensure that workouts and goals are in sync)
Goal/Badge UI enhanced (integrated Badges UI into GoalsDisplay class to ensure smoother UI and fixed the bottom navigation bar issues that were persisting since the last sprint. Back button now always redirects to the main activity)
Workouts display updates with Calendar UI
Adding Custom Activities
Sorting of workouts in workoutlist by date
