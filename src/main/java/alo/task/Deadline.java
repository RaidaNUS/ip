package alo.task;

//Class Deadline
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String date){
        super(description);
        this.by = date;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by:" + by + ")" ;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by; // Include the deadline date
    }
}
