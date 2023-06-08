import java.util.Scanner;

public class Sudu {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int count=0;
            for (int j = 1; j <= i; j++) {
                if (i%j==0){
                   count++;
                }
            }
            if (count==2){
                System.out.println(i);
            }
        }


    }
}


    /*int i=0;
        System.out.println(i++); //>>>>>>>>>> 0
        System.out.println("i > "+i); //>>>>>>>>>> 1

        int num=0;
        num=num++;
        System.out.println("num > "+num); //>>>>>>>  0
        System.out.println("num > "+num); //>>>>>>>  0*/
        /*
        String result="fail";
        int num1=80;
        int num2=60;

       if (num1>=50 && num2>=50){
            result="pass";
        }else {
            result=result;
        }
        System.out.println(num1>=50 && num2>=50 ? "pass" : result); // pass
        System.out.println(result);// pass*/

        /*Scanner input = new Scanner(System.in);

       System.out.print("input english letter ");
        int num = input.nextInt();*/

  /* if (letter.equals("a") | letter.equals("A")) {
            System.out.print("A");
        } else if (letter.equals("b") | letter.equals("B")) {
            System.out.print("B");
        } else if (letter.equals("c") | letter.equals("C")) {
            System.out.print("C");
        } else if (letter.equals("d") | letter.equals("D")) {
            System.out.print("D");
        } else {
            System.out.print("invalid input");
        }*/

        /*switch (letter){
            case "A": System.out.println("A");break;
            case "a": System.out.println("A");break;
            case "B": System.out.println("B");break;
            case "b": System.out.println("B");break;
            case "C": System.out.println("C");break;
            case "c": System.out.println("C");break;
            case "D": System.out.println("D");break;
            case "d": System.out.println("D");break;
            default: System.out.print("invalid input");
        }*/

      /*  for (int i = 0; i < 15; ++i) {
            System.out.println(num+" x "+(i+1)+" = "+(num*(i+1)));
        }*/


    /*    public static void main(String[] args) {
            Scanner scanner=new Scanner(System.in);
            System.out.print("Input your number : ");
            int  num =scanner.nextInt();
            int i=0;
            //=======================================

            //your code >>>>>>>>>>>>>>>>>>>

            //=======================================
            System.out.println("Your digits : "+i);
        }*/
/*    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.print("Input DBMS marks : ");
        int dbms =scanner.nextInt();
        System.out.print("Input PRF marks : ");
        int prf =scanner.nextInt();

        if (dbms>=50){
//            System.out.println(" in the if >>>> ");
            System.out.println(" pass ");
        }else {
//            System.out.println(" in the else >>>>");
            System.out.println(" fail");
        }

        if (prf>=50){
//            System.out.println(" in the if >>>> ");
            System.out.println(" pass ");
        }else {
//            System.out.println(" in the else >>>>");
            System.out.println(" fail");
        }

        if ( dbms>=50 || prf>=50 ){
            *//**
 * '  /  '  >>>>>>> check a all condition
 * '  //  '  >>>>>>> check a dynamic
 * <p>
 * '  &  '  >>>>>>> check a all condition
 * '  &&  '  >>>>>>> check a dynamic
 * <p>
 * '  &  '  >>>>>>> check a all condition
 * '  &&  '  >>>>>>> check a dynamic
 * <p>
 * '  &  '  >>>>>>> check a all condition
 * '  &&  '  >>>>>>> check a dynamic
 *//*
             System.out.println(" in the if >>>> ");
            System.out.println("sem pass ");
        }else {
            System.out.println(" in the else >>>>");
            System.out.println("sem fail");
        }

        if ( dbms>=50 && prf>=50 ){
            *//**
 *   '  &  '  >>>>>>> check a all condition
 *   '  &&  '  >>>>>>> check a dynamic
 * *//*
//            System.out.println(" in the if >>>> ");
            System.out.println("sem pass ");
        }else {
//            System.out.println(" in the else >>>>");
            System.out.println("sem fail");
        }

    }*/

       /* for (   ;num!=0; i++) {
            num=num/10;
        }*/
