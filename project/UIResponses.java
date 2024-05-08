package project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UIResponses {
    // Static Fields
    public static String Welcome="#################################################\n########## WELCOME TO MY FILE ARRANGER ##########\n#################################################\n";
    public static String StartingManualMoving="finishing auto moving ... \nstarting manual moving:";
   public static String openGuide = " -open: to open course folder \n";
    public static String notesGuide = " -note: to write or remove or check the notes for each course\n";
    public static String movingGuid = " -move: will try to move the files according to the name of the file and start MANUAL moving\n";
    public static String help = openGuide+notesGuide+movingGuid;
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
        StringBuilder toRet = new StringBuilder();
        toRet.append("Starting manual moving ...\n");
        toRet.append("in the left is the files you can move , in the right is the courses\n");
        toRet.append(getFilesAndCoursesNames(toMoveManualy,courses));
        return toRet.toString();
    }

    private static String getFilesAndCoursesNames(List<File> files,AllCourses courses){
        StringBuilder coursesFilesListStrings = new StringBuilder();
        List<String> filesNames = getFilesNames(files);
        List<String> coursesList = courses.getCoursesWithNums();
        Integer maxLength = getMaxFileNameLength(filesNames);
        int lineNumber = 0;
        while(filesNames.size()>0&&coursesList.size()>0){
            String fileName = filesNames.remove(0);
            fileName = addSpace(fileName,maxLength);
            coursesFilesListStrings.append("["+lineNumber+"]"+fileName+"|"+coursesList.remove(0)+"\n");
            lineNumber++;
        }
        if(coursesList.size()>0){
            while(!coursesList.isEmpty()) {
                coursesFilesListStrings.append(getSpaceWithLength(maxLength+3)+"|"+coursesList.remove(0)+"\n");
                lineNumber++;
            }
        }
        if(filesNames.size()!=0){
            while(!filesNames.isEmpty()) {
                coursesFilesListStrings.append("["+lineNumber+"]"+filesNames.remove(0)+"\n");
                lineNumber++;
            }
        }
        return coursesFilesListStrings.toString();

    }
    private static List<String> getFilesNames(List<File> files) {
        List<String> toRet = new ArrayList<>();
        for(File file:files) toRet.add(file.getName());
        return toRet;
    }

    private static Integer getMaxFileNameLength(List<String> filesNames) {
        int maxLength=0;
        for(String file:filesNames){
            maxLength = Math.max(maxLength, file.length());
        }
        return maxLength;
    }

    private static String getSpaceWithLength(int length){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(" ");
        }
        return builder.toString();
    }
    private static String addSpace(String fileName, Integer maxLength) {
        return fileName+getSpaceWithLength(maxLength-fileName.length());
    }

    private static void extracted(Integer maxLength, int file) {
        if(file>maxLength) maxLength=file;
    }
    public static String askToMove() {
        return ("wich file you want to move:");
    }

    public static String startingAutoMoving() {
        return "Starting auto moving ...";
    }
}
