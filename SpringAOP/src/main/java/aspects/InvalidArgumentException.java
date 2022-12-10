package aspects;

public class InvalidArgumentException extends Exception{

    public InvalidArgumentException(String message) {
        super(message);
        System.out.println(message);
    }
}
