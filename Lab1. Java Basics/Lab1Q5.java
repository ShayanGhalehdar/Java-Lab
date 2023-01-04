package lab1code;
import java.util.Scanner;

public class Lab1Q5
{
    public static void main(String[] args) //main method
    {
        while(true) // continue until the user wants to quit
        {
            Scanner scanObj= new Scanner(System.in); // create a scanner object
            System.out.println("Enter the operation amongst 'add' , 'subtract' , 'multiply' , 'divide' , 'prime' or stop by typing 'quit': ");
            String operation = scanObj.nextLine();  // get the operation
            if(operation.equals("quit")) //check if user wanted to quit
                break;
            if(operation.equals("prime"))
            {
                System.out.println("Enter the number: ");
                int number = scanObj.nextInt(); // get the number
                int i,j; //i is the number to examine and j are numbers to be tested on and divided to 
                boolean prime=true;
                for(i=2;i<=number;i++)
                {
                    prime=true;
                    for(j=2;j<i;j++)
                    {
                        if(i%j==0) // checking if it is dividable
                        {
                            prime=false; // when one number found so it is not prime then we break the loop
                            break;
                        }
                    }
                    if(prime==true && i!=number) // priniting prime numbers before the number
                        System.out.print(i + ",");
                }
                if(prime==true && number!=1) // check the number status and printing it also number 1 is not prime
                    System.out.println("\ntrue");
                else
                    System.out.println("\nfalse");
            }
            else
            {
                System.out.println("Enter the first number: ");
                int firstnumber = scanObj.nextInt(); // get the first number
                System.out.println("Enter the second number: ");
                int secondnumber = scanObj.nextInt(); // get the second number
            
                if(operation.equals("add"))
                {
                    int sum = firstnumber + secondnumber;
                    System.out.println("result is: " + sum);
                }
                else if(operation.equals("subtract"))
                {
                    int sub = firstnumber - secondnumber;
                    System.out.println("result is: " + sub);
                }
                else if(operation.equals("multiply"))
                {
                    int mult = firstnumber * secondnumber;
                    System.out.println("result is: " + mult);
                }
                else if(operation.equals("divide"))
                {
                    double dev = (double)firstnumber / secondnumber; // we define the result as double and we cast the operation to double
                    if(secondnumber != 0)
                        System.out.println("result is: " + dev);
                    else
                        System.out.println("result is undefined.");
                } // when the second number is 0, the answer is "Infinity". We can add a condition which 
                  // if the second number is 0, the result will be printed "undefined" 
            }
        }
    }  
}
