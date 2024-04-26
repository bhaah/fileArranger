package project;

public class ActionFactory {
    public static Action buildAction(String type,AllCourses allCourses){
        switch (type) {
            case "mv":
                return new AutoMoving(allCourses);
                break;
        
            default:
                break;
        }
        return null;
    } 
}
