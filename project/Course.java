package project;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Course {
    // Fields
    private String folderName;
    private List<String> relatedNames;

    // Methods
    // Add a file to the specified path
    public void addFile(File fileToAdd, String path) {
        // Implementation
    }

    // Remove a file with the given name from the specified path
    public void removeFile(String fileToRemove, String path) {
        // Implementation
    }

    // Check if a file with the given name is related
    public boolean checkRelated(String fileName) {
        // Implementation
        return false;
    }

    // Move the file with the given name to the related folder
    public boolean moveToRelatedFolder(String fileName) {
        // Implementation
        return false;
    }

    // Get notes
    public Map<Integer, String> getNotes() {
        // Implementation
        return null;
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
