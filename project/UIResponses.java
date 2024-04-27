package project;

import java.io.File;
import java.util.List;

public class UIResponses {
    // Static Fields
    public static String Welcome="Welcome to my file arranger ...\n";
    public static String StartingManualMoving="finishing auto moving ... \nstarting manual moving:";
    public static String MainOptions = "you can arrange your files to the related courses using <mv>\nyou can check your notes using the word <note>";

    // Static Methods



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

    public static String fileMovedSuccessfuly(String name) {
        return ("One file moved successfuly - "+name+" \n");
    }

    public static String fileMovedFailed(String name) {
        return ("One file failed moved - "+name+" \n");
    }

    public static String StartingManualMoving(List<File> toMoveManualy, AllCourses courses) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'StartingManualMoving'");
    }

    public static String askToMove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askToMove'");
    }
}
