import java.util.Scanner;
import java.util.ArrayList;

public class Alo {

    private static class Task {
        private String toDo;
        private boolean isDone;

        public Task(String toDo) {
            this.toDo = toDo;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            String Marking;
            if (isDone) {
                Marking = "X";
            } else {
                Marking = " ";
            }
            return "[" + Marking + "] " + toDo;
        }

    }

    // Learnt from: https://github.com/nus-cs2113-AY2425S2/ip/pull/76/files

    private static ArrayList<Task> tasks = new ArrayList<>();


    private static void Greeting() {
        System.out.println("____________________________________________________________");
        System.out.println("Hi there! I'm Alo, my name means LIGHT!"); // Greet the user
        System.out.println("How may I be of assistance to you today?");
        System.out.println("____________________________________________________________");
    }


    private static void exitProgram() {
        System.out.println("____________________________________________________________");
        System.out.println("Great Helping You! Ciao ^_^");
        System.out.println("____________________________________________________________");
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here ya are. This is your list. Enjoy!");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println("      " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTask(String argument) {
        try {
            int taskIndexNum = Integer.parseInt(argument) - 1;
            if (taskIndexNum >= 0 && taskIndexNum < tasks.size()) {
                tasks.get(taskIndexNum).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Cool! I've marked it as done! Congrats for finishing task: ");
                System.out.println("    " + tasks.get(taskIndexNum));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Task you want to mark down as done is out of range Dear.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number my dear.");
        }
    }

    private static void unmarkTask(String argument) {
        try {
            int taskIndexNum = Integer.parseInt(argument) - 1;
            if (taskIndexNum >= 0 && taskIndexNum < tasks.size()) {
                tasks.get(taskIndexNum).unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Alrighty! I've marked it as not done.");
                System.out.println("    " + tasks.get(taskIndexNum));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("Task you want to unmark as done is out of range Dear.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number my dear.");
        }

    }

    private static void addTask(String inputs) {
        tasks.add(new Task(inputs));
        System.out.println("____________________________________________________________");
        System.out.println("         added: " + inputs);
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {

        //Greeting the user
        Greeting();

        //Inputting the user input
        Scanner scan = new Scanner(System.in);

        while (true) {
            String input = scan.nextLine().trim();
            String words[] = input.split(" ", 2);
            String command = words[0].toLowerCase();
            String arguments = words.length > 1 ? words[1] : ""; // This contains the number that i have to mark or unmark.
            //Tenery conditions implemented for practice

            switch (command) {

                case "bye":
                    exitProgram();
                    return;

                case "list":
                    listTasks();
                    break;

                case "mark":
                    markTask(arguments);
                    break;

                case "unmark":
                    unmarkTask(arguments);
                    break;

                default:
                    addTask(input);
                    break;

            }
        }
    }
}

