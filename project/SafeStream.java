import java.util.concurrent.ConcurrentHashMap;

public class SafeStream {
    // Fields
    private ConcurrentHashMap<String, String> input;
    private ConcurrentHashMap<String, String> output;
    private boolean reqOpened;

    // Constructor
    public SafeStream() {
        input = new ConcurrentHashMap<>();
        output = new ConcurrentHashMap<>();
        reqOpened = false;
    }

    // Methods
    // Get the contents to print
    public String getToPrint() {
        // Implementation
        return null;
    }

    // Add content to print
    public void addToPrint(String toPrint) {
        // Implementation
    }

    // Add input content
    public void addInput(String inputContent) {
        // Implementation
    }

    // Close the action
    public void closeAction() {
        // Implementation
    }

    // Check if the action is opened
    public boolean isActionOpened() {
        // Implementation
        return false;
    }

    // Open the action
    public void openAction() {
        // Implementation
    }
}
