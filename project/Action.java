package project;

public abstract class Action implements Runnable{
    // Fields
    private AllCourses courses;
    private SafeStream stream;

    // Constructor
    public Action(AllCourses courses, SafeStream stream) {
        this.courses = courses;
        this.stream = stream;
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
}

