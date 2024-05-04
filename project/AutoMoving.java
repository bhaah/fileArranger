package project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AutoMoving extends MovingAction{
    private boolean shouldFinish;
    
    public AutoMoving(AllCourses courses) {
        super(courses);
        shouldFinish = false;
    }

    @Override
    public void run() {
        List<File> failedFilesToMove = new ArrayList<>();
        stream.addToPrint(UIResponses.startingAutoMoving());
        while(!shouldFinish && !downloads.isEmpty()){
            File fileToMove = downloads.remove(0);
            if(courses.checkRelationAndMove(fileToMove.getName())){
                stream.addToPrint(UIResponses.fileMovedSuccessfuly(fileToMove.getName()));
            }else{
                stream.addToPrint(UIResponses.fileMovedFailed(fileToMove.getName()));
                failedFilesToMove.add(fileToMove);
            }
        }
        ManualMoving manualMovingToStart = new ManualMoving(failedFilesToMove,courses);
        manualMovingToStart.setStream(stream);
        manualMovingToStart.run();
    }
    
}
