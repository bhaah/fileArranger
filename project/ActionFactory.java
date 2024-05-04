package project;

public class ActionFactory {
    public static Action buildAction(String type,AllCourses allCourses){
        switch (type) {
            case "move":
                return new AutoMoving(allCourses);
                
        
            default:
                break;
        }
        return null;
    } 
}
