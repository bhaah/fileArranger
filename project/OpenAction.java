package project;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;


public class OpenAction extends Action{

    public OpenAction(AllCourses courses) {
        super(courses);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        stream.addToPrint("********************************************\nwhich course you want to open?\n");
        stream.openAction();
        String input = stream.waitForInputAndGet();
        String path = CourseFolderKeys.courseDirc(input);
        File fileToOpen = new File(path);
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try{
                desktop.open(fileToOpen);
                stream.addToPrint(input+" is opened :)\n");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        stream.closeAction();
        stream.addToPrint("closing action...\n");
    }


    
}
