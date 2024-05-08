package project;

import java.io.File;
import java.util.ArrayList;

public class NotesAction extends Action{
    private static final String WELCOME_STRING = ">this is the note action \n>choose a course:";
    private String param = null;
    private CourseNotes courseNotesFile = null;
    private String actionType = null;
    private boolean shouldFinish=false; 

    public NotesAction(AllCourses courses) {
        super(courses);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        openActionOnStream(WELCOME_STRING);
        while(!shouldFinish){
        
            if(courseNotesFile==null) initCourseFromInput() ;
            if(actionType!=null) dealWithAction();
            else initActionType(stream.waitForInputAndGet());
        }
        closeActionOnStream();
    }

    private void initActionType(String input) {
        if(input.startsWith("add")) {
            actionType="add";
            param = input.substring(4);
        }
        else if(input.startsWith("get")) {
            actionType = "get";
        }
        else if(input.startsWith("remove")) {
            actionType="remove";
            param=input.substring(7);
        }
        
    }

    private void initCourseFromInput() {
        String input = stream.waitForInputAndGet();
        checkCourseAndInit(input);
    }

    private void dealWithAction() {
        if(actionType.equals("add")) addNote();
        else if(actionType.equals("get")) printNote();
        else if(actionType.equals("remove")) removeNote();
        else if(actionType.equals("finish")) finishAction();
        actionType=null;
    }

    private void removeNote() {
        if(param!=null){

            courseNotesFile.removeLine(Integer.parseInt(param));
            stream.addToPrint("line removed :)\n");
        }
    }

    private void printNote() {
        ArrayList notes = courseNotesFile.getSeparetedLines();  
        stream.addToPrint("printing all the notes:\n");
        for(int lineNum = 0 ; lineNum<notes.size();lineNum++){
            stream.addToPrint("["+lineNum+"]"+notes.get(lineNum)+"\n");
        } 
    }

    private void addNote() {
        courseNotesFile.writeNewLine(param);
        stream.addToPrint("adding new line :)\n");
    }

    private void checkCourseAndInit(String input) {
        String courseDircPath = CourseFolderKeys.courseDirc(input);
        File courseNotes = new File(courseDircPath);
        if(!courseNotes.exists()) stream.addToPrint("there is no course like this!!!\n>choose again :");
        else initNotes(courseNotes);
    }

    private void initNotes(File courseNotes) {
        courseNotesFile = new CourseNotes(courseNotes);
        if(stream!=null) stream.addToPrint(" -add <note>:to add new note\n  -get :printing all the notes\n  -remove <number>:removing note with this number\n  -finish : terminate the action\n");
    }

    private void finishAction(){
        shouldFinish=true;
    }

    public void setParams(String paramsString) {
        ArrayList<String> params = splitParams(paramsString);
        checkCourseAndInit(params.remove(0));
        if(courseNotesFile!=null) initActionType(merge(params));
    }

    private String merge(ArrayList<String> params) {
        StringBuilder builder = new StringBuilder();
        while(!params.isEmpty()){
            builder.append(params.remove(0)+" ");
        }
        return builder.toString();
    }

    private ArrayList<String> splitParams(String params) {
        ArrayList<String> toRet = new ArrayList<>();
        StringBuilder paramBuider = new StringBuilder();
        for(int index = 0 ; index<params.length();index++){
            if((params.charAt(index)==' '&& !paramBuider.isEmpty()) ) {
                toRet.add(paramBuider.toString());
                paramBuider = new StringBuilder();
            }else if(params.charAt(index)!=' ') {
                paramBuider.append(params.charAt(index));
            }
            if(index==params.length()-1){
                toRet.add(paramBuider.toString());
            }
        }
        return toRet;
    }
    
}
