package project;

import java.util.Scanner;

public class KeyboardThread implements Runnable {
    private SafeStream stream;
    private Scanner scanner;
    private boolean shouldTerminate = false;

    public KeyboardThread(SafeStream stream){
        this.stream = stream;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while(!shouldTerminate){
            stream.addInput(scanner.nextLine());
        }
    }

    private void terminate(){ 
        shouldTerminate = true; 
    }
    
}
