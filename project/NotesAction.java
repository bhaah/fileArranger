package project;

import java.io.File;
import java.util.ArrayList;

public class NotesAction extends Action{
    private CourseNotes courseNotesFile = null;

    private boolean shouldFinish=false; 

    public NotesAction(AllCourses courses) {
        super(courses);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        stream.openAction();
        stream.addToPrint("************************************\nNotes action\n>choose the course:");
        while(!shouldFinish){
            String input = stream.waitForInputAndGet();
            if(courseNotesFile==null) checkCourseAndInit(input);
            else dealWith(input);
        }
        stream.closeAction();
        stream.addToPrint("finishing action...\n");
    }

    private void dealWith(String input) {
        if(input.startsWith("add")) addNote(input.substring(4));
        else if(input.startsWith("get")) printNote(input.length()==3?null:input.substring(3));
        else if(input.startsWith("remove")) removeNote(input.substring(7));
        else if(input.equals("finish")) finishAction();
    }

    private void removeNote(String substring) {
        if(substring!=null){

            courseNotesFile.removeLine(Integer.parseInt(substring));
            stream.addToPrint("line removed :)\n");
        }
    }

    private void printNote(Object object) {
        ArrayList notes = courseNotesFile.getSeparetedLines();  
        stream.addToPrint("printing all the notes:\n");
        for(int lineNum = 0 ; lineNum<notes.size();lineNum++){
            stream.addToPrint("["+lineNum+"]"+notes.get(lineNum)+"\n");
        } 
    }

    private void addNote(String substring) {
        courseNotesFile.writeNewLine(substring);
        stream.addToPrint("adding new line :)");
    }

    private void checkCourseAndInit(String input) {
        String courseDircPath = CourseFolderKeys.courseDirc(input);
        File courseNotes = new File(courseDircPath);
        if(!courseNotes.exists()) stream.addToPrint("there is no course like this!!!\n");
        else initNotes(courseNotes);
    }

    private void initNotes(File courseNotes) {
        courseNotesFile = new CourseNotes(courseNotes);
        stream.addToPrint(" -add <note>:to add new note\n  -get :printing all the notes\n  -remove <number>:removing note with this number\n  -finish : terminate the action\n");
    }

    private void finishAction(){
        shouldFinish=true;
    }
    
}
