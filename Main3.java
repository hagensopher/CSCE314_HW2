import java.lang.reflect.*;

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
        Method[] listMethods = tempClass.getDeclaredMethods();
        for(int i =0;i<listMethods.length;i++){
            System.out.println(listMethods[i]);
        }
    }
}