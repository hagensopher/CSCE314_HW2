import java.lang.reflect.*;

public class Main4 {

    public static void main(String[] args){
    
        try{
            Class<?> c = Class.forName(args[0]);
            //System.out.println(c.getClass().);
            //MyClass myClass = new MyClass();
            //Class<?> c = myClass.getClass(); //needs to be a command line argument but testing with hard case
            System.out.println(args[0]);
            Method[] methods = c.getDeclaredMethods();
            for(Method m : methods){
                String methodName = m.getName();
                if((methodName.startsWith("test")) && (m.getReturnType().equals(Boolean.TYPE)) && (m.getParameterCount() ==0) && Modifier.isStatic(m.getModifiers())){
                //System.out.println("Found the method "+ methodName);
                try {
                    System.out.println(m.getClass());
                    if(m.invoke(null).toString() == "true"){
                        System.out.println("OK: "+m.getName()+" suceeded"); //call the method
                    }
                    else{
                        System.out.println("FAILED: "+m.getName()+" failed"); //call the method
                    }

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println("Error: Method Name not Found");
                    System.out.println(e);
                }
            }
        }

        }
        catch(ClassNotFoundException e ){
            System.out.println("Error: Class Name not Found");
        }
        catch(ExceptionInInitializerError e){
            System.out.println("Error: Class Name not Found");
        }
        catch(LinkageError e){
            System.out.println("Error: Class Name not Found");
        }
       

        

    }




}
class MyClass{ //example temp class
    public static boolean test1(){ return true;} //correct
    public static  boolean test2(){ return false;} //correct
    public static boolean testTAMU(boolean tamu){return tamu;}
    public boolean testCSE(){return false;}
    public static int testInt(){return 314;}
    public static boolean badtest(){return true;}
}