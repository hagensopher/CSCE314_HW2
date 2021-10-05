// This is the starter code. You are free to add methods and fields
// to this class.

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PostBox implements Runnable {
    private final int MAX_SIZE;
    
    ReentrantLock sharedMessages;

    class Message {
        String sender;
        String recipient;
        String msg;
        Message(String sender, String recipient, String msg) {
            this.sender = sender;
            this.recipient = recipient;
            this.msg = msg;
        }
    }

    private final LinkedList<Message> messages;
    private LinkedList<Message> myMessages;
    private String myId;
    private volatile boolean stop = false;

    public PostBox(String myId, int max_size) {
        messages = new LinkedList<Message>();
        this.myId = myId;
        this.myMessages = new LinkedList<Message>();
        this.MAX_SIZE = max_size;
        new Thread(this).start();
    }

    public PostBox(String myId, int max_size, PostBox p) {
        ReentrantLock privateMessages;
        this.myId = myId;
        this.messages = p.messages;
        this.MAX_SIZE = max_size;
        this.myMessages = new LinkedList<Message>();
        new Thread(this).start();
    }

    public String getId() { return myId; }

    public void stop() {
        // make it so that this Runnable will stop when it next wakes
        stop = true;
    }

    public void send(String recipient, String msg) {
        // add a message to the shared message queue i.e message
        
        messages.add(new Message(this.myId, recipient, msg)); //this should add the message to shared?
    }

    public List<String> retrieve() {
        // 1. return the contents of myMessages
        //loop through all the myMessages?
        List<String> retrivedMessages = new ArrayList<String>();
        synchronized(myMessages){ //this all needs to be locked because if it gives the lock
                                    //before the clear then the data is wrong
            for(int i =0;i<myMessages.size();i++){
                //System.out.println(myMessages.get(i).msg);
                //need to return the list of strings
                
                retrivedMessages.add(myMessages.get(i).msg);
                
                //UNLOCK????
                
            }
            // 2. and empty myMessages
            myMessages.clear();
        }

        return retrivedMessages;
        
    }

    public void run() {
        // loop while not stopped
        while(!stop){
            //   1. approximately once every second move all messages
        //      addressed to this post box from the shared message
        //      queue to the private myMessages queue
                for(int i=0;i<messages.size();i++){
                    
                    //lock and grab the ones that MATCH ID
                    if(this.myId == messages.get(i).sender){
                        //System.out.println("Test");
                        synchronized(myMessages){
                        myMessages.add(messages.get(i)); //move this messages?
                        //System.out.println("myMessages Size is "+ myMessages.size());
                        }
                    }
                    
                }
                 //   2. also approximately once every second, if the private or
            //      shared message queue has more than MAX_SIZE messages,
            //      delete oldest messages so that the size of myMessages
            //      and messages is at most MAX_SIZE.
                synchronized(myMessages){ 
                    while(myMessages.size() > MAX_SIZE){ //checks myMessages
                        myMessages.removeLast();
                        
                    }
                }
                synchronized(messages){
                    while(messages.size() > MAX_SIZE){ //checks messages
                        messages.removeLast();
                    }
                }
        //wait every one second 
            try {
                //System.out.println(this.myId +" Sleep one second");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }     

        }
                
       
        
    }
}