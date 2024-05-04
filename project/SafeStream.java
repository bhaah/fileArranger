package project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class SafeStream {
    // Fields
    private ConcurrentLinkedQueue<String> input;
    private ConcurrentLinkedQueue<String> output;
    private boolean reqOpened;
    private Action actionToRun;
    private boolean shouldContinue = true;
    private String actionStringReq;
    private Object actionReq=new Object();

    // Constructor
    public SafeStream() {
        input = new ConcurrentLinkedQueue<>();
        output = new ConcurrentLinkedQueue<>();
        reqOpened = false;
    }

    // Methods
    // Get the contents to print
    public String getToPrint() {
        synchronized (output) {
            if (output.size() > 0) {
                return output.remove();
            }
        }
        return null;
    }

    // Add content to print
    public void addToPrint(String toPrint) {
        synchronized (output) {
            output.add(toPrint);
            output.notifyAll();
        }
    }

    // Add input content
    public void addInput(String inputContent) {
        synchronized (input) {
            if (inputContent.equals("done"))
                shouldContinue = false;
            else if (isActionOpened()) {
                input.add(inputContent);
            } else {
                actionStringReq = inputContent;
                synchronized(actionReq){ actionReq.notifyAll();}
            }
            input.notifyAll();
        }
    }

    // Close the action
    public void closeAction() {
        reqOpened = false;
    }

    // Check if the action is opened
    public boolean isActionOpened() {
        return reqOpened;
    }

    // Open the action
    public void openAction() {
        reqOpened = true;
    }

    public void printAndWaitForAllOutputs() {

        synchronized (output) {
            while (isActionOpened() || !output.isEmpty()) {
                if (output.size() == 0)
                    try {
                        output.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                String toPrint = output.remove();
                System.out.print(toPrint);
            }
        }
    }

    public String getNewActionRequested() {
        String toRet;
        if (isActionOpened())
            return null;
        synchronized(actionReq){try {
            actionReq.wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}
        toRet = actionStringReq;
        actionStringReq = null;
        return toRet;

    }

    public String getInput() {
        synchronized (input) {
            while (input.size() == 0)
                try {
                    input.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        return input.remove();
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }
}
