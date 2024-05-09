package project;

public abstract class Action implements Runnable{
    // Fields
    protected AllCourses courses;
    protected SafeStream stream;

    // Constructor
    public Action(AllCourses courses) {
        this.courses = courses;
    }

    // Abstract method to be implemented by subclasses
    public abstract void run();

    // Set the stream
    public void setStream(SafeStream stream) {
        this.stream = stream;
    }

    // Wait until a message is received and collect it
    public String waitUntilMessageAndCollect() {
        // Implementation
        return null;
    }

    public void openActionOnStream(String welcomeString){
        stream.addToPrint("8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8-8\n");
        stream.addToPrint(UIResponses.adminSay( welcomeString));
        stream.openAction();
    }

    public void closeActionOnStream(){
        stream.addToPrint(UIResponses.adminSay( "closing the action ...\n"));
        stream.closeAction();
    }
}

