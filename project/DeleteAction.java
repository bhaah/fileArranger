package project;

import java.io.File;

public class DeleteAction extends MovingAction{
    private boolean shouldFinish =false;

    public DeleteAction(AllCourses courses) {
        super(courses);
    }

    @Override
    public void run() {
        
        while(!shouldFinish && !downloads.isEmpty()){
            stream.addToPrint(UIResponses.getNamesWithNumbers(downloads));
            stream.addToPrint(UIResponses.askToDelete);
            String numOfFileToDel = stream.waitForInputAndGet();
            dealWithInput(numOfFileToDel);
        }
    }

    private void dealWithInput(String input) {
        if(input.startsWith("done")) shouldFinish =true;
        else{
            File fileToDelete = getTheFileWithInputNumber(input);
            if(fileToDelete!=null && fileToDelete.exists()){
                fileToDelete.delete();
                stream.addToPrint(UIResponses.adminSay(fileToDelete.getName() +" deleted ;)\n") );
            } 
        }
    }

    private File getTheFileWithInputNumber(String input) {
      try{
            int number = Integer.parseInt(input);
            return downloads.remove(number);
        }catch(NumberFormatException ex){
            stream.addToPrint(UIResponses.color(UIResponses.red, "your input is illegal!!"));
        }catch(NullPointerException ex ){
            stream.addToPrint(UIResponses.color(UIResponses.red, "there are no file in this number"));
        }
        return null;
    }
    
}
