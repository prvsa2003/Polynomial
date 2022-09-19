package pol;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        Polynomial p = new Polynomial();

        System.out.println("Enter first polynomial : ");
        String s = inp.nextLine();
        s = p.split(s);
        String arr1[] = s.substring(1).split(" ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
        p.eachPoly(arr1);

        System.out.println("Enter second polynomial : ");
        String ss = inp.nextLine();
        ss = p.split(ss);
        String arr2[] = ss.substring(1).split(" ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
        p.eachPoly(arr2);

        myLoop:
        while (true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("What do you want to do?");
            System.out.println();
            System.out.println("1: evaluate\n2: sum\n3: subtract\n4: multiply\n5: divide\n6: max degree\n7: sort by degree\n8: Exit\n");
            System.out.print("Enter Your Choice: ");
            int userChoice = inp.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.print("What number do you like to calculate the polynomial with?  ");
                    int h = inp.nextInt();
                    System.out.println("evaluate: " + p.evaluate(p.eachPoly(arr1), h));
                    break;
                case 2:
                    p.a = p.sum(p.eachPoly(arr1), p.eachPoly(arr2));
                    System.out.println("sum: " + p.toString());
                    break;
                case 3:
                    p.a = p.subtract(p.eachPoly(arr1), p.eachPoly(arr2));
                    System.out.println("subtract: " + p.toString());
                    break;
                case 4:
                    p.a = p.multiply(p.eachPoly(arr1), p.eachPoly(arr2));
                    System.out.println("multiply: " + p.toString());
                    break;
                case 5:
                    p.a = p.divide(p.eachPoly(arr1), p.eachPoly(arr2));
                    System.out.println("divide: " + p.toString());
                    break;
                case 6:
                    System.out.println("max degree: " + p.maxDeg(p.a));
                    break;
                case 7:
                    System.out.println("max degree: " + p.sortByDeg(p.a));
                    break;
                case 8:
                    break myLoop;
            }

        }

    }
}
