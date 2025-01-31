import java.util.Scanner;
import java.util.ArrayList;

public class Alo {

    private static class task{
        private String ToDo;
        private boolean isDone;

        public task(String ToDo){
            this.ToDo = ToDo;
            this.isDone = false;
        }

        public void markAsDone(){
            this.isDone=true;
        }

        public void unmarkAsDone(){
            this.isDone=false;
        }

        @Override
        public String toString() {
            String Marking;
            if(isDone){
                Marking = "X";
            }else{
                Marking = " ";
            }
            return "["+ Marking + "]" + ToDo;
        }

    }

    private static ArrayList<task> Tasks= new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hi there! I'm Alo, my name means LIGHT!"); // Greet the user
        System.out.println("How may I be of assistance to you today?");
        System.out.println("____________________________________________________________");

        Scanner scan = new Scanner(System.in);

        while (true) {
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Great Helping You! Ciao ^_^");
                System.out.println("____________________________________________________________");
                break;
            }else if(input.equalsIgnoreCase("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here ya are. This is your list. Enjoy!");
                for(int i= 0; i<Tasks.size();i+=1){
                    System.out.println("      " + (i+1) + ". " + Tasks.get(i));
                }
                System.out.println("____________________________________________________________");
            }else if(input.startsWith("mark")){
                int taskIndexNum = Integer.parseInt(input.substring(5))-1;
                if(taskIndexNum >= 0 && taskIndexNum< Tasks.size()){
                    Tasks.get(taskIndexNum).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Cool! I've marked it as done! Congrats for finishing task: ");
                    System.out.println("    "+ Tasks.get(taskIndexNum));
                    System.out.println("____________________________________________________________");
                }else{
                    System.out.println("Task you want to mark down as done is out of range Dear.");
                }

            }else if(input.startsWith("unmark")) {
                int taskIndexNum = Integer.parseInt(input.substring(7)) - 1;
                if (taskIndexNum >= 0 && taskIndexNum < Tasks.size()) {
                    Tasks.get(taskIndexNum).unmarkAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Alrighty! I've marked it as not done.");
                    System.out.println("    " + Tasks.get(taskIndexNum));
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("Task you want to unmark as done is out of range Dear.");
                }
            }else{
                Tasks.add(new task(input));
                System.out.println("____________________________________________________________");
                System.out.println("         added: "+ input);
                System.out.println("____________________________________________________________");
            }


        }
        scan.close();
    }
}
