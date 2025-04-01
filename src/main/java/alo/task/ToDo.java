package alo.task;

//Class ToDo
//Creates Todos in the list
public class ToDo extends Task {
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return super.toFileString();  // Only description needed
    }
}
