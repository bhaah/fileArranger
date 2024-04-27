package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CourseNotes {
    private File noteFile;
    private BufferedReader reader;

    public CourseNotes(File CourseDirectory){
        File notesFile = new File(CourseDirectory, CourseFolderKeys.NOTES_FILE_NAME);
        if (notesFile.exists()) {
            this.noteFile= notesFile;
        } else {
            throw new IllegalArgumentException("note file not exist in course : " + CourseDirectory.getName());
        }
    }

    public ArrayList<String> getSeparetedLines(){
        ArrayList< String> toRet = new ArrayList<>();
        Runnable toDo = ()->{
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    toRet.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        readFileAndDo(toDo);
        return toRet;
    } 
    private void readFileAndDo(Runnable toDoWithFile){
        try {
            reader = new BufferedReader(new FileReader(noteFile));
            toDoWithFile.run();
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions
        }

    }
    public void writeNewContent(ArrayList<String> newContent){
        String contentToWrite = mergeLines(newContent);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(noteFile))){
            writer.write(contentToWrite);
        }catch(IOException e){}
    }

    private String mergeLines(ArrayList<String> lines) {
        StringBuilder toRet = new StringBuilder();
        for(String line:lines){
            toRet.append(line).append("\n");
        }
        return toRet.toString();
    }

    public void writeNewLine(String line){
        ArrayList<String> content = getSeparetedLines();
        content.add(line);
        writeNewContent(content);
    }

    public void removeLine(int lineNum){
        ArrayList<String> content = getSeparetedLines();
        if(lineNum<content.size()) content.remove(lineNum);
        writeNewContent(content);
    }
}
