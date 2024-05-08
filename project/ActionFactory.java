package project;

public class ActionFactory {
    public static Action buildAction(String type,AllCourses allCourses){
        switch (type) {
            case "move":
                return new AutoMoving(allCourses);
            case "open":
                return new OpenAction(allCourses);
            case "note":
                return new NotesAction(allCourses);
            default:
                if(type.startsWith("open")&&type.length()>5) {
                    OpenAction toRet =  new OpenAction(allCourses);
                    toRet.initCourseNameFromExternalBuild(type.substring(5));
                    return toRet;
                }else if(type.startsWith("note")){
                    NotesAction toRet = new NotesAction(allCourses);
                    toRet.setParams(type.substring(4));
                    return toRet;
                }
                

        }
        return null;
    } 
}
