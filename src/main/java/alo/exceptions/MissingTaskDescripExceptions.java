package alo.exceptions;

public class MissingTaskDescripExceptions extends TaskExceptions {

    public MissingTaskDescripExceptions(String commandType){
        super("Oh No! The description of a " + commandType +" CANNOT be empty! Please provide me with an instruction so I can help you note down a alo.task.Task. >v<");
    }
}
