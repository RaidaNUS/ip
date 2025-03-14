package alo.task;

//Class Event
public class Event extends Task {
    String from;
    String to;

    public Event(String description, String from, String to ){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + from + " to:"+ to +")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + "-" + to; // Include event start & end time
    }
}
