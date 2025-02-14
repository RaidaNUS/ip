package alo.exceptions;

public class TaskExceptions extends Exception{
    //String err_description; //DOES not need its own one. its more of a passing mechanism to the Exception java file.
    //This is so that we can use the throw new of Exceptions class easily as now, TaskException falls under Exception class
    // Since it falls under exception class, the other exceptions can access the throw method [Heigherarchy]

    //Made so that all forms of eceptions like invalid command, task number, etc all fall under once category,
    //then the try cath form of code in alo.ui.Alo.java file is also cleaner!

    public TaskExceptions(String err_description) {
        super(err_description);
    }
}
