import java.util.Scanner;

public class Cal {

    public static void main(String[] args) {
        double Op1 = 0;
        double Op2 = 0;
        String Opt;


        Scanner input = new Scanner(System.in);

        System.out.println("Enter the Operator [ Add, Sub, Mul, Div, Prime]");
        Opt = input.next();
        if (Opt.equals("Prime")){
            System.out.println("Enter Only One Operand !");
            Op1 = input.nextInt();
        }
        else if (!Opt.equals("quit")) {
            System.out.println("Enter Op1 & Op2 One After Another !");
            Op1 = input.nextDouble();
            Op2 = input.nextDouble();
        }

        while (!Opt.equals("quit")) {

            switch (Opt) {
                case "Prime":
                    int PCheck = 0;
                    for(int i = 2; i < Op1; i++) {
                        if (Op1 % i == 0)
                            PCheck = 1;
                    }
                    if(PCheck == 1) {
                        System.out.printf("'%.0f' is not Prime Number", Op1);
                        System.out.println("");
                        System.out.println("Previous Prime Numbers :");
                        for(int i = 2; i < Op1; i++){
                            int Check = 0;
                            for(int j = 2; j < i; j++){
                                if(i % j == 0)
                                    Check = 1;
                            }
                            if(Check == 0) {
                                System.out.printf("%d", i);
                                System.out.println("");
                            }
                        }
                    }
                    else {
                        System.out.printf("'%.0f' is a Prime Number", Op1);
                        System.out.println("");
                    }
                    break;
                case "Add":
                    System.out.printf("%.3f + %.3f = %.3f", Op1, Op2, Op1 + Op2);
                    break;
                case "Sub":
                    System.out.printf("%.3f - %.3f = %.3f", Op1, Op2, Op1 - Op2);
                    break;
                case "Mul":
                    System.out.printf("%.3f * %.3f = %.3f", Op1, Op2, Op1 * Op2);
                    break;
                case "Div":
                    if (Op2 != 0) {
                        System.out.printf("%.3f / %.3f = %.3f", Op1, Op2, Op1 / Op2);
                    } else {
                        System.out.println("Zero Division Error");
                    }
                    break;
                default:
                    System.out.printf("Error 404, '%s' Isn't An Operation !", Opt);
            }

            System.out.println("");
            System.out.println("Enter the Operator [ Add, Sub, Mul, Div, Prime]");
            Opt = input.next();
            if (Opt.equals("Prime")){
                System.out.println("Enter Only One Operand !");
                Op1 = input.nextInt();
            }
            else if (!Opt.equals("quit")) {
                System.out.println("Enter Op1 & Op2 One After Another !");
                Op1 = input.nextDouble();
                Op2 = input.nextDouble();
            }

        }
        input.close();
    }
}