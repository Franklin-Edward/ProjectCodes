import java.util.*;
import java.lang.Math;
import java.io.*;
class MenuDriven{
    public void fibonacci(Double n){
        System.out.println("The Fibonacci Series till "+n+" elements:");
        if(n==1){
            System.out.println(0);
            return;
        }
        if(n==2){
            System.out.println(0+" "+1);
            return;
        }
        Double x=0d,y=1d;
        System.out.print(0+" "+1+" ");
        for(int i=2;i<n;++i){
            y=x+y;
            x=y-x;
            System.out.print(y+" ");
        }
        System.out.println();
    }
    public boolean prime(int n){
        if(n<=1){
            return false;
        }
        if(n<=3){
            return true;
        }
        if(n%2==0 || n%3==0){
            return false;
        }
        for(int i=5;i<Math.sqrt(n);i+=6){
            if(n%i==0 || n%(i+2)==0){
                return false;
            }
        }
        return true;
    }
    public boolean palindrome(String s){
        StringBuilder reverse = new StringBuilder(s);
        reverse.reverse();
        if(reverse.toString().equals(s)){
            return true;
        }
        return false;
    }
}
class MenuDrivenMain{
    public static void main(String args[]){
        MenuDriven menu = new MenuDriven();
        Scanner scan = new Scanner(System.in);
        int option=0;
        do{
            System.out.println("Menu");
            System.out.println("1.)Generate Fibonacci Series");
            System.out.println("2.)Check Prime or not");
            System.out.println("3.)Check Palindrome of a number or string");
            System.out.println("4.)Exit");
            try{
                option=scan.nextInt();
            }catch(InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
                scan.nextLine();
            }
            switch(option){
                case 1: {
                    try{
                        System.out.println("Enter number of elements(n>0)");
                        Double n=scan.nextDouble();
                        menu.fibonacci(n);
                    }catch(InputMismatchException ex) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.nextLine();
                    }
                    break;
                }
                case 2: {
                    try{
                        System.out.println("Enter a number to check prime or not");
                        int n=scan.nextInt();
                        boolean flag = menu.prime(n);
                        if(flag==false){
                            System.out.println(n+" is not a prime number");
                        }
                        else{
                            System.out.println(n+" is a prime number");
                        }
                    }catch(InputMismatchException ex) {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.nextLine();
                    }
                    break;
                }
                case 3:{
                    System.out.println("Enter a number or string");
                    scan.nextLine();
                    String input = scan.nextLine();
                    boolean flag = menu.palindrome(input);
                    if(flag==false){
                        System.out.println(input+" is not Pallindrome");
                    }
                    else{
                        System.out.println(input+" is a Pallindrome");
                    }
                    break;
                }
                case 4:{
                    System.out.println("Exiting...");
                    break;
                }
                default: System.out.println("Enter a Valid Option");
            }
        }while(option!=4);
    }
}