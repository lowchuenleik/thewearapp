Your code should run without you being present – i.e., you should provide a Readme file that
explains how to run your weather app, what libraries you have used, and if it runs on any Windows / Mac / Linux computer etc.;


== Requirements
* External libraries required: javafx and JSON-Java 
   * JavaFX = https://gluonhq.com/products/javafx/ 
   * JSON-Java = https://github.com/stleary/JSON-java


== How to use
* The project was compiled and run on Intellij. It should work on Operating Systems that can run a Java Virtual Machine (JVM), which includes Windows/Mac/Linux
* To run on Intellij:
   * Create a new project with folder ID_Code. 
   * Set src as source directory, out as output directory.
   * In the Project Structure, add the javafx lib folder and the json-java jar file as “Libraries”
   * Build the project.
   * Compile and run the Main class, with the following additional arguments to use javafx when compiling with Intellij: --module-path "path_to_javafx_lib_folder" --add-modules javafx.controls,javafx.fxml
   * Example: --module-path "C:\Users\Admin\GitHub_Projects\JAR_Files\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml
* To run on Windows:
   * Replace the appropriate paths, and run from the parent ID_Code folder on a CLI (e.g. cmd on Windows); 
"path_to_java_sdk" --module-path path_to_javafx_lib_folder --add-modules javafx.controls,javafx.fxml --add-modules javafx.base,javafx.graphics --add-reads javafx.base=ALL-UNNAMED --add-reads javafx.graphics=ALL-UNNAMED -classpath path_to_output_folder;path_to_json-20180813.jar; Main


*** path_to_output_folder is currently \ID_Code\out\production\ID_Code


* Example command follows
"C:\Program Files\Java\jdk-11.0.1\bin\java.exe" --module-path "C:\Users\chuen\Desktop\Uni\Paper 3\Interaction Design\weatherapp\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml --add-modules javafx.base,javafx.graphics --add-reads javafx.base=ALL-UNNAMED --add-reads javafx.graphics=ALL-UNNAMED -classpath "C:\Users\chuen\Desktop\Uni\Paper 3\Interaction Design\weatherapp\ID_Code\out\production\ID_Code;C:\Users\chuen\Desktop\Uni\Paper 3\Interaction Design\weatherapp\json-20180813.jar" Main


* The GUI will then appear with current weather information displayed, and navigate using the buttons.
   * Clicking HOURLY will display weather predictions for the next 24 hours.
   * Clicking WEEKLY will display the next week’s predictions.
   * Clicking the LEFT and RIGHT arrow buttons on the clothes pane will give alternative clothing suggestions.
   * Clicking REFRESH will update the weather with any updated information from the API.
   * Clicking SETTINGS in the top right corner will bring up a series of options that can be changed.


== Folder structure (under ID_Code/src), and what the classes do
* data/ folder: contains all the icons and background images used in the project
* .fxml files: give the xml outline for the four pages in the GUI (mainPage for home page, daily for daily weather page, hourly for hourly weather page, settings for settings page)
* Controller, HourlyController, DailyController, SettingsController java files: Controller classes for users of the GUI to interact with the GUI. Stores variables declared in the .fxml files, and has methods that define actions when buttons are clicked
* API: makes API calls to weatherbit.io
* ScreenController: switches pages in the GUI (each page is a root Pane)
* Main: where the GUI is run from, by calling the start method. Produces the GUI window
* Settings: stores user preferences as static fields
* Summary: produces the summary of a day’s weather
* Today: produces useful information about a day, including information from a Summary object and the Settings class
* Hourly: generates output for the hourly page of the GUI
* Weekly: generates output for the daily page of the GUI