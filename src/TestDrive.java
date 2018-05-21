/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vincent Bownes
 */
import java.lang.reflect.*;
import static java.lang.System.out;
import java.lang.Math;
import java.util.Random;


public class TestDrive {
    
    
    //Final variable used in randomAlphanumeric function
    private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    //
    
    //Function to generate a random alphanumeric String
    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character)); 
        }
        return builder.toString();
    }//End of randomAlphaNumeric function
    
    //variable to decide the depth of recursion for the genRandomValue function
    private int depth = 0; 
    //
    
    //function to generate random values for the different argument types
    public Object genRandomValue(Type type, int depthLimit, int randomNumLimit){
        
        Random rand = new Random();
         
        try{       
            
                switch (type.getTypeName()){
                    case "int": return rand.nextInt(randomNumLimit) - 2;
                    case "java.lang.String": return randomAlphaNumeric(rand.nextInt(randomNumLimit));
                    case "boolean": return rand.nextInt(2);
                    case "char": return randomAlphaNumeric(1);
                    default : 
                                if(depth < depthLimit){
                                    Class<?> c = Class.forName(type.getTypeName());
                                    Constructor[] cons = c.getConstructors();
                                    Object[] arguments;
                                    Type[] param = cons[0].getGenericParameterTypes();
                                    arguments = new Object[param.length];
                                    depth += 1;
                                    for(int k=0;k<param.length;k++)
                                    {
                                        out.println(param[k].getTypeName()+"recursive"+depth);                                           //////////
                                        arguments[k] = genRandomValue(param[k],depthLimit, randomNumLimit);                                          
                                    }
                                    Object obj = cons[0].newInstance(arguments);
                                    depth = 0;
                                    return obj;
                                }
                }
            
            return null;
        }
            catch (ClassNotFoundException x) {System.out.println("Class not found 2");}
            catch (InstantiationException x){out.println("InstantiationException");}
            catch (IllegalAccessException y){out.println("IllegalAccessException");}
            catch (InvocationTargetException z){out.println("InvocationTargetException");}	
        return null;
            
    }//End genRandomValue function 

    //Function to create instances of a class. One for every different constructor
    public Object[] makeObjects(String className, int randomNumLimit, int depthLimit){
            try {
                Class<?> c = Class.forName(className);
                Constructor[] cons = c.getConstructors();
                Object[] obj = new Object[cons.length];
                    Object[] arguments;                   
                    for(int i=0;i<cons.length;i++){
                            Type[] param = cons[i].getGenericParameterTypes();
                            arguments = new Object[param.length];
                            for(int k=0;k<param.length;k++)
                            {
                                    out.println("The type of paramter "+ (k + 1) +  " in constructor " + (i + 1) + " is: " + param[k].getTypeName());                                    
                                    arguments[k] = genRandomValue(param[k],depthLimit,randomNumLimit);
                            }
                            obj[i] = cons[i].newInstance(arguments);
                    }
                return obj;    
            }
                catch (ClassNotFoundException x) {System.out.println("Class not found");}
                catch (InstantiationException x){out.println("InstantiationException");}
                catch (IllegalAccessException y){out.println("IllegalAccessException");}
                catch (InvocationTargetException z){out.println("InvocationTargetException");}	
            return null;
        }//End makeObjects function
    
    //Function to randomlly call all methods
    public void callMethods(Object[] obj,String className, int numOfCalls, int randomNumLimit){
        Random rand = new Random();
        
        try{
            Class<?> c = Class.forName(className);
            Method[] mthds = c.getDeclaredMethods();
            for(int g=0; g<mthds.length; g++){
                out.println(mthds[g].toGenericString() + g);                                                          ///////////
            }
            Object[] arguments;
            for(int whichObject=0; whichObject<obj.length; whichObject++){
                for(int k=0; k<numOfCalls; k++){
                    int whichMethod = rand.nextInt(mthds.length);
                    Type[] param = mthds[whichMethod].getGenericParameterTypes();
                    arguments = new Object[param.length];
                    for(int q=0;q<param.length;q++){  
                                    arguments[q] = genRandomValue(param[q],3,randomNumLimit);
                    }
                    mthds[whichMethod].invoke(obj[whichObject], arguments);
                   
                }
            }
           
        }
            catch (ClassNotFoundException m) {System.out.println("Class not found");}
            catch (IllegalAccessException b){out.println("IllegalAccessException");}
            catch (InvocationTargetException z){out.println("InvocationTargetException in callMethods");}
    }//End callMethods function

    public static void main(String[] args) {
	
        TestDrive td = new TestDrive();
        
        td.callMethods(td.makeObjects(args[0],10,5), args[0], 1000, 10);
        
    }
}


    


