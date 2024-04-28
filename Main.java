import java.util.Scanner;
import java.util.stream.Stream;

import project.*;

public class Main {
    private Boolean ActionInProgress;
    public static void main(String[] args) {
        AllCourses allCourses = new AllCourses();
        SafeStream stream = new SafeStream();
        Boolean shouldFinish = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println(UIResponses.Welcome);
        while(!shouldFinish){
            stream.printAndWaitForAllOutputs();

            String input = scanner.nextLine();
            if(input.toLowerCase().equals("done")) shouldFinish=true;
            else{
                if(stream.isActionOpened()){
                    stream.addInput(input);
                }else{
                    Action action = ActionFactory.buildAction(input, allCourses);
                    System.out.println(input);
                    action.setStream(stream);
                    Thread threadAction = new Thread(action);
                    threadAction.start();
                    
                }
                
            }
        }
    }
}