//package Q2;
import java.util.concurrent.*;
public class Main2{


    public static void main(String[] args){
        counter count = new counter();
        
        Thread printing = new Thread(new timePrinting(count));
        printing.start();

        Thread m1 = new Thread(new message1(count));
        m1.start();

    //    Thread t2 = new Thread(new message1());
    //    t2.start();

    
    }

}
class counter {
    int counter =0;
}
class timePrinting implements Runnable{
    private counter count; //the shared counter
    public timePrinting(counter count){
        this.count = count;
    }

    public void run(){
        while(true){ //startst the running thread?
            
                try {
                    Thread.sleep(500); //wait a second
                    System.out.print(count.counter+" "); //run this thread alwywas until
                    synchronized(count){
                        count.notifyAll(); //wakes all threads to check if they are to print 
                    }
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.counter++;
            
        }
    }
}

class message1 implements Runnable{ //only print when count is at a factor of 7
    //temp test value
    //int time = 0;
    private counter count;
    public message1(counter counter){
        this.count = counter;
    }
    public void run(){
        while(true){
            synchronized(count){
                try {
                    //System.out.println("Message 1 is waiting");
                    count.wait();
                } catch (InterruptedException e) {
                    //System.out.println("the count is " + count.counter);
                    e.printStackTrace();
                }
                if(count.counter%7 == 0){ //a facotr of 7
                    System.out.println("\n Message 1 is printing the value "+count.counter);
                    //System.out.println(count.counter);
                }
            }  
        }

    }
}

class message2 implements Runnable{ //only print when count is a factor of 15
    //int time = 0;
    int count =0;
    public message2(int count){
        this.count = count;
    }
    public void run(){
        while(true){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count%15 == 0){ //a facotr of 7
                System.out.println(count);
            }
            count++;
        }
        
    }

}