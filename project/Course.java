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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    // Fields
    private String folderName;
    private File courseDirectory;
    private List<String> relatedNames;
    private File[] filesContained;

   

    public Course(File courseFolder){
        courseDirectory= courseFolder;
        folderName = courseFolder.getName();
        filesContained = courseFolder.listFiles();
        File relatedNamesFile = new File(courseFolder,CourseFolderKeys.RELATED_NAMES_FILE_NAME);
        if(relatedNamesFile.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(relatedNamesFile))) {
                String line;
                
                while ((line = br.readLine()) != null) {
                    relatedNames.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle any IO exceptions
            }
        }else{
            throw new IllegalArgumentException("there is no relatedNames.txt file in the -"+folderName+"- folder");
        }
    }
    

    // Methods
    // Add a file to the specified path
    public void addFile(File fileToAdd, String path) {
        // Implementation
        String folderToAddIn = CourseFolderKeys.getRelatedFolder(fileToAdd.getName());
        try{
            Path toAddPath = Paths.get(fileToAdd.getPath());
            String toAddName = fileToAdd.getName();
            String targetPath = courseDirectory.toPath()+"/"+folderToAddIn+"/";
            Files.copy(toAddPath, new File(targetPath, toAddName).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    // Remove a file with the given name from the specified path
    public void removeFile(String fileToRemove, String path) {
        // Implementation
        File fileToDel = new File(path+"/"+fileToRemove);
        if(fileToDel.exists()){
            fileToDel.delete();
        }else{
            throw new IllegalArgumentException("file to delete is not existed");
        }
    }

    // Check if a file with the given name is related
    public boolean checkRelated(String fileName) {
        // Implementation
        for (String name : relatedNames) {
            if(fileName.contains(name)) return true;
        }
        return false;
    }

    // // Move the file with the given name to the related folder
    // public boolean moveToRelatedFolder(String fileName) {
    //     // Implementation
    //     return false;
    // }

    // Get notes
    public Map<Integer, String> getNotes() {
        File notesFile = new File(courseDirectory,CourseFolderKeys.NOTES_FILE_NAME);
        Map<Integer,String> toRet = new HashMap<>();
        if(notesFile.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(notesFile))) {
                String line;
                int index=0;
                while ((line = br.readLine()) != null) {
                    toRet.put(index,line);
                    index++;
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle any IO exceptions
            }
        }else{
            throw new IllegalArgumentException("there is no relatedNames.txt file in the -"+folderName+"- folder");
        }
        return toRet;
    }

    // Add a string line
    public void addString(String line) {
        // Implementation

    }

    // Remove a line with the given number
    public void removeLine(int number) {
        // Implementation
    }
}
