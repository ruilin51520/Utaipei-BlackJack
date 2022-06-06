import java.util.*;

public class Utilities {
    public static final Scanner input = new Scanner(System.in);

    //copyright: GitHub @brianchou452
    public static boolean askYesOrNo(String question, boolean printNewLine) {
        while (true) {
            System.out.print(question + " ");
            String answer = input.nextLine();
            if (printNewLine) System.out.println();
            if (answer.equals("Y") || answer.equals("y"))
                return true;
            else if (answer.equals("N") || answer.equals("n"))
                return false;
            else
                System.out.println("Wrong input, please try again.");
        }
    }

    public static int askAnInt(String question, boolean printNewLine) {
        while (true) {
            try {
                System.out.print(question);
                int answer = Integer.parseInt(input.nextLine(), 10);
                if (printNewLine) System.out.println();
                if (answer > 0)
                    return answer;
                else
                    System.out.println("Please enter a positive number.");
            } catch (Exception NumberFormatException) {
                System.out.println("Wrong input, please try again.");
            }
        }
    }

    public static void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
