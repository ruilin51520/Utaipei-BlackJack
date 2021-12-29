import java.util.*;

public class Utilities {
    public static final Scanner input = new Scanner(System.in);

    //copyright: GitHub @brianchou452
    public static boolean askYesOrNo(String question) {
        while (true) {
            System.out.print(question + " ");
            String answer = input.nextLine();
            System.out.println();
            if (answer.equals("Y") || answer.equals("y"))
                return true;
            else if (answer.equals("N") || answer.equals("n"))
                return false;
            else
                System.out.println("Wrong input, please try again.\n");
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
