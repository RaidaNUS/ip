import java.util.Scanner;

public class Alo {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hi there! I'm Alo, my name means LIGHT!"); // Greet the user
        System.out.println("How may I be of assistance to you today?");
        System.out.println("____________________________________________________________");

        Scanner scan = new Scanner(System.in);

        while (true) {
            String input = scan.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Great Helping You! Ciao ^_^");
                System.out.println("____________________________________________________________");
                break;
            } else {
                //ECHOING
                System.out.println("____________________________________________________________");
                System.out.println("       " + input);
                System.out.println("____________________________________________________________");
            }
        }
        scan.close();
    }
}
