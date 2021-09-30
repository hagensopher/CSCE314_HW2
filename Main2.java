//package Q2;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
public class Main2{


    public static void main(String[] args){
        Thread counter = new Thread(new timePrinting());
        counter.start();

        Thread t1 = new Thread(new message1());
       t1.start();

    //    Thread t2 = new Thread(new message1());
    //    t2.start();

    
    }

}

class timePrinting implements Runnable{
    

    int count = 0; //the shared counter

    public void run(){
        while(true){ //startst the running thread?
            System.out.println(count); //run this thread alwywas until
            try {
                Thread.sleep(1000); //wait a second
                wait(); 
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count ++;
        }
    }
}

class message1 implements Runnable{ //only print when count is at a factor of 7
    //temp test value
    int time = 0;
    public void run(){
        while(true){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(time%7 == 0){ //a facotr of 7
                System.out.println(time);
            }
            time++;
        }

    }
}

class message2 implements Runnable{ //only print when count is a factor of 15
    int time = 0;
    public void run(){
        while(true){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(time%15 == 0){ //a facotr of 7
                System.out.println(time);
            }
            time++;
        }
        
    }

}