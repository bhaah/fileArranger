import java.util.Scanner;
import java.util.stream.Stream;

import project.*;

public class Main {

    public static void main(String[] args) {
        AllCourses allCourses = new AllCourses();
        SafeStream stream = new SafeStream();
        KeyboardThread keyboardThread = new KeyboardThread(stream);
        Thread keyThread = new Thread(keyboardThread);
        System.out.print(UIResponses.Welcome);
        System.out.print(UIResponses.help);
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