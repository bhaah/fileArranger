package project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UIResponses {
    // Static Fields
    public static String StartingManualMoving = adminSay("finishing auto moving ... \nstarting manual moving:");
    public static String openGuide = " -open: to open course folder \n";
    public static String notesGuide = " -note: to write or remove or check the notes for each course\n";
    public static String movingGuid = " -move: will try to move the files according to the name of the file and start MANUAL moving\n";
    public static String reset = "\u001B[0m";
    public static String red = "\u001B[31m";
    public static String green = "\u001B[32m";
    public static String yellow = "\u001B[33m";
    public static String blue = "\u001B[34m";
    public static String help = adminSay(openGuide + notesGuide + movingGuid);
    public static String Welcome ="#################################################\n##########"+yellow+" WELCOME TO MY FILE ARRANGER "+reset+"##########\n#################################################\n";
    // Static Methods


    public static String adminSay(String message){
        return yellow+message+reset;
    }

    public static String fileMovedSuccessfuly(String name) {
        return (green+"One file moved successfuly - " + name + " \n"+reset);
    }

    public static String fileMovedFailed(String name) {
        return (red+"One file failed moved - " + name + " \n"+reset);
    }

    public static String StartingManualMoving(List<File> toMoveManualy, AllCourses courses) {
        StringBuilder toRet = new StringBuilder();
        toRet.append(color(yellow,"Starting manual moving ...\n"));
        toRet.append(color(yellow,"in the left is the files you can move , in the right is the courses\n"));
        toRet.append(getFilesAndCoursesNames(toMoveManualy, courses));
        return toRet.toString();
    }

    private static String getFilesAndCoursesNames(List<File> files, AllCourses courses) {
        StringBuilder coursesFilesListStrings = new StringBuilder();
        List<String> filesNames = getFilesNames(files);
        List<String> coursesList = courses.getCoursesWithNums();
        Integer maxLength = getMaxFileNameLength(filesNames);
        int lineNumber = 0;
        while (filesNames.size() > 0 && coursesList.size() > 0) {
            String fileName = filesNames.remove(0);
            fileName = addSpace(fileName, maxLength);
            coursesFilesListStrings.append("[" + lineNumber + "] " + color(blue,fileName) + "|" + coursesList.remove(0) + "\n");
            lineNumber++;
        }
        if (coursesList.size() > 0) {
            while (!coursesList.isEmpty()) {
                coursesFilesListStrings.append(getSpaceWithLength(maxLength + 3) + "|" + coursesList.remove(0) + "\n");
                lineNumber++;
            }
        }
        if (filesNames.size() != 0) {
            while (!filesNames.isEmpty()) {
                coursesFilesListStrings.append("[" + lineNumber + "] " +color(blue, filesNames.remove(0)) + "\n");
                lineNumber++;
            }
        }
        return coursesFilesListStrings.toString();

    }

    private static List<String> getFilesNames(List<File> files) {
        List<String> toRet = new ArrayList<>();
        for (File file : files)
            toRet.add(file.getName());
        return toRet;
    }

    private static Integer getMaxFileNameLength(List<String> filesNames) {
        int maxLength = 0;
        for (String file : filesNames) {
            maxLength = Math.max(maxLength, file.length());
        }
        return maxLength;
    }

    private static String getSpaceWithLength(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private static String addSpace(String fileName, Integer maxLength) {
        return fileName + getSpaceWithLength(maxLength - fileName.length());
    }


    public static String askToMove() {
        return color(yellow,"wich file you want to move:");
    }

    public static String startingAutoMoving() {
        return color(yellow,"Starting auto moving ...\n");
    }

    public static String color(String color,String message){
        return color+message+reset;
    }
}
