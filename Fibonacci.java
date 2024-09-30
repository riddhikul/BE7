//0,1,1,2,3,......,N
import java.util.*;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number till which you want to print the fibonacci series: ");
        int N=sc.nextInt();
        System.out.println("*".repeat(20));
        System.out.println("Iterative Fibonacci: ");
        iterFibo(N);
/*  Time Complexity: O(N) 
Auxiliary Space: O(1) for iterative */

        System.out.println();
        System.out.println("*".repeat(20));
        System.out.println("Recursive Fibonacci: ");

        /*T ime Complexity: O(2^N)  
Auxiliary Space: O(n) for recursion */
        for(int i=0;i<N;i++){
            System.out.print(recFibo(i)+" ");
        }
        System.out.println();
        
        System.out.println("*".repeat(20));

    }

    static int recFibo(int N){
            if(N<2)
            return N;
            else{
                return recFibo(N-1)+recFibo(N-2);
            }

    }

    static void iterFibo(int N){
        int a=0,b=1;
        int Count=2;
        System.out.print(a+" "+b);
        while(Count<N){
           int c=a+b;
           System.out.print(" " + c);
           a=b;
           b=c;  
           Count++;
        }
    }
}
