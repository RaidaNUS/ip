package alo.task;

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; //DEFAULT setting for the tasks
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
        return "[" + Marking + "] " + description;
    }

}