package project;

import java.io.File;
import java.util.List;

public class ManualMoving extends MovingAction {
    private List<File> toMoveManualy;
    private boolean shouldFinish;

    public ManualMoving(List<File> failedFilesToMove, AllCourses courses) {
        super(courses);
        toMoveManualy = failedFilesToMove;
    }

    @Override
    public void run() {
        stream.addToPrint(UIResponses.StartingManualMoving(toMoveManualy, courses));
        stream.waitForInput();
        while (!shouldFinish) {
            stream.addToPrint(UIResponses.askToMove());
            
            String input = stream.getInput();
            if(input.equals("finish")){
                shouldFinish=true;
            }else{
                moveRequestedFile(input);
            }
        }
    }

    private void moveRequestedFile(String input){
        int fileNumber = getFileNumber(input);
        int courseNumber = getCourseNumber(input);
        courses.moveFromRestToCourse(toMoveManualy.get(fileNumber).getName(), courseNumber);
    }

    private int getCourseNumber(String input) {
        StringBuilder stringNumber= new StringBuilder();
        int index = 0;
        while(input.charAt(index)>='0'&&input.charAt(index)<='9'){
          stringNumber.append(input.charAt(index));
          index++;
        }   
        return Integer.parseInt(stringNumber.toString());  
    }

    private int getFileNumber(String input) {
        StringBuilder stringNumber= new StringBuilder();
        int index = input.length()-1;
        while(input.charAt(index)>='0'&&input.charAt(index)<='9'&& index>=0){
          stringNumber.append(input.charAt(index));
          index--;
        }   
        return Integer.parseInt(stringNumber.toString());  
    }

}
