package project;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;


public class OpenAction extends Action{

    private final String WELCOME_STRING = "which course you want to open:\n" ;
    private String courseName = null;

    public OpenAction(AllCourses courses) {
        super(courses);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        openActionOnStream(getAppropriateWelcome());
        if(courseName==null) initCourseNameFromInput();
        String path = CourseFolderKeys.courseDirc(courseName);
        File fileToOpen = new File(path);
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try{
                desktop.open(fileToOpen);
                stream.addToPrint(UIResponses.color(UIResponses.green, courseName+" is opened :)\n"));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        closeActionOnStream();
    }

    private String getAppropriateWelcome() {
        if(courseName==null) return WELCOME_STRING;
        else return "";
    }

    private void initCourseNameFromInput() {
        courseName = stream.waitForInputAndGet();
    }
    public void initCourseNameFromExternalBuild(String name){
        courseName = name;
    }


    
}
