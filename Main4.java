import java.lang.reflect.*;

public class Main4 {

    public static void main(String[] args){
        MyClass myClass = new MyClass();
        Class<?> c = myClass.getClass(); //needs to be a command line argument but testing with hard case

        Method[] methods = c.getDeclaredMethods();
        for(int i =0;i<methods.length;i++){
            String methodName = methods[i].getName();
            if((methodName.startsWith("test")) && (methods[i].getReturnType().equals(Boolean.TYPE)) && (methods[i].getParameterCount() ==0)){
                //System.out.println("Found the method "+ methodName);
                try {
                    if(methods[i].invoke(myClass).toString() == "true"){
                        System.out.println("OK: "+methods[i].getName()+" suceeded"); //call the method
                    }
                    else{
                        System.out.println("FAILED: "+methods[i].getName()+" failed"); //call the method
                    }

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }




}
class MyClass{ //example temp class
    public boolean test1(){ return true;} //correct
    public boolean test2(){ return false;} //correct
    public boolean testTAMU(boolean tamu){return tamu;}
    public int testInt(){return 314;}
    public boolean badtest(){return true;}
}