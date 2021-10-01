import java.lang.reflect.*;
import java.util.*;
public class Main3{

    public static void main(String[] args){
        A class1 = new A();
        A.displayMethodInfo(class1);
    }

}
class A{
    int a =0;
    int b=0;
    double c =0.0;

    int integerTester(int a,int b ){
        return a+b;
    }
    double bar(double c,int b ){
        return c+b;
    }
    static String String(){
        return "Hello";
    }

    static void displayMethodInfo(Object obj){
        Class tempClass = obj.getClass();
        
        Method[] listMethods = tempClass.getDeclaredMethods(); //this is a method
        for(int i =0;i<listMethods.length;i++){
            //output methodName ( Param1, Param2) -> retrun type
            Parameter[] paramList = listMethods[i].getParameters();
            System.out.print(listMethods[i].getName()+" ("); 
            for(int j=0;j<paramList.length;j++){
                System.out.print(paramList[j].getType()+", "); 
            }
            System.out.println(") -> " + listMethods[i].getReturnType());
        }
    }
}