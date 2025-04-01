package alo.ui;

// The Ui class manages all printed interactions with the user in the CLI.

public class Ui {

    // Line separator for formatting output
    private static final String LINE = "____________________________________________________________";

    // Prints the greeting message when the chatbot starts
    public static void printGreeting() {
        System.out.println(LINE);
        System.out.println("Hi there! I'm Alo , my name means LIGHT!");
        System.out.println("How may I be of assistance to you today?");
        System.out.println(LINE);
    }
    // Prints the farewell message shown when the chatbot exits
    public static void printExit() {
        System.out.println(LINE);
        System.out.println("Great Helping You! Ciao ^_^");
        System.out.println(LINE);
    }

    // Prints a horizontal line separator for formatting CLI output
    public static void printLine() {
        System.out.println(LINE);
    }

    // Prints a general-purpose message to the CLI
    public static void printMessage(String message) {
        System.out.println(message);
    }

    // Prints an error message
    public static void printError(String message) {
        System.out.println(LINE);
        System.out.println("Error: " + message);
        System.out.println(LINE);
    }


}
