import java.util.Scanner;
import java.util.ArrayList;

public class Alo {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hi there! I'm Alo, my name means LIGHT!"); // Greet the user
        System.out.println("How may I be of assistance to you today?");
        System.out.println("____________________________________________________________");

        Scanner scan = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Great Helping You! Ciao ^_^");
                System.out.println("____________________________________________________________");
                break;
            }

            if(input.equalsIgnoreCase("list")){
                System.out.println("____________________________________________________________");
                for(int i= 0; i<tasks.size();i+=1){
                    System.out.println("      " + (i+1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
                continue;
            }

            tasks.add(input);
            System.out.println("____________________________________________________________");
            System.out.println("         added: "+ input);
            System.out.println("____________________________________________________________");
        }
        scan.close();
    }
}
