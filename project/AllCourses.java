package project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Map;

public class AllCourses {
    
    public ArrayList<Course> courses;
    
    public AllCourses(){
        File[] coursesDirectories = getCoursesInDirectory();
        courses = buildCoursesFromDircs(coursesDirectories);
    }

    private ArrayList<Course> buildCoursesFromDircs(File[] coursesDirectories) {
        ArrayList<Course> coursesToRet = new ArrayList<>();
        for (File courseDirc : coursesDirectories) {
            coursesToRet.add(new Course(courseDirc));
        }
        return coursesToRet;
    }

    private File[] getCoursesInDirectory() {
        File coursesDirectory = new File(CourseFolderKeys.DIRECTORY_PATH);
        if(!coursesDirectory.exists()) throw new IllegalArgumentException("courses folder is not existed");
        return coursesDirectory.listFiles(file->file.isDirectory());
    }

    // Methods
    // Get courses with numbers (alternative name)
    //!!!!!!!!!!!!!!!!!!!!!!! MUST CHANGE TO UI RESPONSE !!!!!!!!!!!!!!!!!!!!!!!!
    public String getCoursesWithNums() {
        StringBuilder theCoursesWithNumbers = new StringBuilder();
        for(int courseNum = 0;courseNum<courses.size();courseNum++){
            addCourseWithNumber(theCoursesWithNumbers, courseNum);
        }
        return theCoursesWithNumbers.toString();
    }

    private void addCourseWithNumber(StringBuilder toAddIn,int courseNum){
        String space=" ";
        String numberPart = getNumberWithBracket(courseNum)+space;
        String courseName = courses.get(courseNum).getName();
        toAddIn.append(numberPart + courseName + "\n");
    }

    private String getNumberWithBracket(int courseNum) {
        return "["+courseNum+"]";
    }

    // Move folder from Downloads to the specified course
    public void moveFolderFromDownloads(String fileName, int courseGoal) {
        String filePathToMove = CourseFolderKeys.DOWNLOADS_DIRC_PATH + "\\" + fileName;
        File fileToMove = new File(filePathToMove);
        courses.get(courseGoal).addFile(fileToMove);
    }

    // Check relation course
    public int checkRelationCourse(String fileName) {
        for (int i=0;i<courses.size();i++) {
            if(courses.get(i).checkRelatedToCourse(fileName)) return i;
        }
        return -1;
    }

    // Check relation and move
    public boolean checkRelationAndMove(String fileName) {
        int relatedCourse = checkRelationCourse(fileName);
        if(relatedCourse!=-1) {
            moveFolderFromDownloads(fileName, relatedCourse);
            return true;
        }
        else {
            moveToRestFiles(fileName);
            return false;
        } 
    }

    private void moveToRestFiles(String fileName) {
        File fileToMoveFromDownloads = new File(CourseFolderKeys.DOWNLOADS_DIRC_PATH+"\\"+fileName);
        if(!fileToMoveFromDownloads.exists()) throw new IllegalArgumentException("file to move not found!!");
        String pathToRestFiles = CourseFolderKeys.DIRECTORY_PATH+"/"+CourseFolderKeys.REST_FILES;
        try {
            Files.copy(fileToMoveFromDownloads.toPath(),new File(pathToRestFiles,fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Get notes for a specific course number
    public ArrayList<String> getNotes(int courseNum) {
        return courses.get(courseNum).getNotes();
    }

    // Add a string line to a specific course
    public void addString(String line, int courseNum) {
        courses.get(courseNum).addNote(line);
    }

    // Remove a line with the given number from a specific course
    public void removeLine(int number, int courseNum) {
        courses.get(courseNum).removeLine(number);
    }


    public File[] getRestFiles(){
        String pathToRestFiles = CourseFolderKeys.DIRECTORY_PATH+"/"+CourseFolderKeys.REST_FILES;
        return new File(pathToRestFiles).listFiles();

    }
}
