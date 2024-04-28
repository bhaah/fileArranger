package project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        StringBuilder toRet = new StringBuilder();
        toRet.append("Starting manual moving ...\n");
        toRet.append("in the left is the files you can move , in the right is the courses");
        toRet.append(getFilesAndCoursesStringList(toMoveManualy,courses));
        return toRet.toString();
    }

    private static String getFilesAndCoursesStringList(List<File> files,AllCourses courses){
        StringBuilder coursesFilesListStrings = new StringBuilder();
        List<String> filesNames = files.stream().map(File::getName).collect(Collectors.toList());
        Integer maxLength = 0;
        filesNames.forEach(file->extracted(maxLength, file.length()));
        int lineNumber = 0;
        List<String> coursesList = courses.getCoursesWithNums();
        
        while(lineNumber<=Math.min(files.size(), coursesList.size())){
            lineNumber++;
            String fileName = filesNames.remove(0);
            fileName = fileName + addSpace(fileName,maxLength);
            coursesFilesListStrings.append("["+lineNumber+"]"+files.remove(0)+"|"+coursesList.remove(0)+"\n");
        }
        if(filesNames.size()==0 && coursesList.size()>0){
            while(!coursesList.isEmpty()) {
                coursesFilesListStrings.append(getSpaceWithLength(maxLength+2)+"|"+coursesList.remove(0)+"\n");
                lineNumber++;
            }
        }
        if(filesNames.size()!=0){
            while(!filesNames.isEmpty()) {
                coursesFilesListStrings.append("|["+lineNumber+"]"+filesNames.remove(0)+"\n");
                lineNumber++;
            }
        }
        return coursesFilesListStrings.toString();

    }
    private static String getSpaceWithLength(int length){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(" ");
        }
        return builder.toString();
    }
    private static String addSpace(String fileName, Integer maxLength) {
        return fileName+getSpaceWithLength(maxLength);
    }

    private static void extracted(Integer maxLength, int file) {
        if(file>maxLength) maxLength=file;
    }
    public static String askToMove() {
        return ("wich file you want to move:");
    }
}
