package project;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
public class SafeStream {
    // Fields
    private ConcurrentLinkedQueue<String> input;
    private ConcurrentLinkedQueue<String> output;
    private boolean reqOpened;
    private boolean waitingForInput;
    private Object lockAction = new Object();
    // Constructor
    public SafeStream() {
        input = new ConcurrentLinkedQueue<>();
        output = new ConcurrentLinkedQueue<>();
        reqOpened = false;
        waitingForInput=false;
    }

    // Methods
    // Get the contents to print
    public String getToPrint() {
        synchronized(output){
            if(output.size()>0){
                return output.remove();
            }
        }
        return null;
    }

    // Add content to print
    public void addToPrint(String toPrint) {
        synchronized(output){
            if(output.size()>0){
                output.add(toPrint);
            }
        }
    }

    // Add input content
    public void addInput(String inputContent) {
        synchronized(input){
            input.add(inputContent);
        }
    }

    // Close the action
    public void closeAction() {
        waitingForInput=false;
        reqOpened=false;
    }

    // Check if the action is opened
    public boolean isActionOpened() {
        
        return reqOpened;
    }

    // Open the action
    public void openAction() {
        reqOpened =true;
    }

    public void waitForInput(){
        waitingForInput=true;
    }

    
    public void printAndWaitForAllOutputs() {
        while(isActionOpened() || !output.isEmpty()){
            String toPrint = output.remove();
            System.out.println(toPrint);
        }
    }

    public String getInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInput'");
    }
}
