package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


public class Course {
    // Fields
    private String folderName;
    private File courseDirectory;
    private List<String> relatedNames;
    private CourseNotes notes; 

    public Course(File courseFolder) {
        courseDirectory = courseFolder;
        folderName = courseFolder.getName();
        notes = new CourseNotes(courseDirectory);
        setRelatedNames();
    }

    private void setRelatedNames() {
        relatedNames = new ArrayList<>();
        File relatedNamesFile = new File(courseDirectory, CourseFolderKeys.RELATED_NAMES_FILE_NAME);
        if (relatedNamesFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(relatedNamesFile))) {
                String line;

                while ((line = br.readLine()) != null) {
                    relatedNames.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle any IO exceptions
            }
        } else {
            throw new IllegalArgumentException("there is no relatedNames.txt file in the -" + folderName + "- folder");
        }
    }

    // Methods
    // Add a file to the specified path
    public void addFile(File fileToAdd) {
        // Implementation
        String folderToAddIn = CourseFolderKeys.getRelatedFolder(fileToAdd.getName());
        try {
            Path toAddPath = Paths.get(fileToAdd.getPath());
            String toAddName = fileToAdd.getName();
            String targetPath = courseDirectory.toPath() + "/" + folderToAddIn + "/";
            Files.copy(toAddPath, new File(targetPath, toAddName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Remove a file with the given name from the specified path
    public void removeFile(String fileToRemove, String path) {
        // Implementation
        File fileToDel = new File(path + "/" + fileToRemove);
        if (fileToDel.exists()) {
            fileToDel.delete();
        } else {
            throw new IllegalArgumentException("file to delete is not existed");
        }
    }

    // Check if a file with the given name is related
    public boolean checkRelatedToCourse(String fileName) {
        for (String name : relatedNames) {
            if (fileName.contains(name))
                return true;
        }
        return false;
    }
    public ArrayList<String> getNotes() {
        return notes.getSeparetedLines();
    }
    public void addNote(String note) {
        notes.writeNewLine(note);
    }    
    public void removeLine(int numberToRemove) {
        notes.removeLine(numberToRemove);
    }

    public String getName() {
        return folderName;
    }
}
