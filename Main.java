import java.util.Scanner;
import java.util.stream.Stream;

import project.*;

public class Main {
    private Boolean ActionInProgress;

    public static void main(String[] args) {
        AllCourses allCourses = new AllCourses();
        SafeStream stream = new SafeStream();
        Boolean shouldFinish = false;
        KeyboardThread keyboardThread = new KeyboardThread(stream);
        Thread keyThread = new Thread(keyboardThread);
        System.out.println(UIResponses.Welcome);
        keyThread.start();
        while (stream.shouldContinue()) {
            stream.printAndWaitForAllOutputs();
            String actionString = stream.getNewActionRequested();
            if(actionString!=null){
                Action action = ActionFactory.buildAction(actionString, allCourses);
                if(action!=null){
                    action.setStream(stream);
                    Thread threadAction = new Thread(action);
                    threadAction.start();
                    stream.openAction();
                }
            }

        }
    }
}