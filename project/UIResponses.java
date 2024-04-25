package project;

import java.util.List;

public class UIResponses {
    // Static Fields
    public static String Welcome;
    public static String StartingManualMoving;
    public static String MainOptions;

    // Static Methods

    // Set welcome message
    public static void setWelcome(String welcome) {
        Welcome = welcome;
    }

    // Get welcome message
    public static String getWelcome() {
        return Welcome;
    }

    // Set starting manual moving message
    public static void setStartingManualMoving(String startingManualMoving) {
        StartingManualMoving = startingManualMoving;
    }

    // Get starting manual moving message
    public static String getStartingManualMoving() {
        return StartingManualMoving;
    }

    // Set main options message
    public static void setMainOptions(String mainOptions) {
        MainOptions = mainOptions;
    }

    // Get main options message
    public static String getMainOptions() {
        return MainOptions;
    }

    // Generate message for files moved
    public static String filesMoved(List<String> files) {
        // Implementation
        return "";
    }

    // Generate message for files not moved
    public static String filesNotMoved(List<String> files) {
        // Implementation
        return "";
    }

    // Generate message for files and folders
    public static List<String> filesAndFolders(List<String> files, List<String> folders) {
        // Implementation
        return null;
    }

    // Generate message for moving into course folder
    public static String movingIntoCourseFolder(String courseName) {
        // Implementation
        return "";
    }

    // Generate message for ending action
    public static String endingAction() {
        // Implementation
        return "";
    }
}
