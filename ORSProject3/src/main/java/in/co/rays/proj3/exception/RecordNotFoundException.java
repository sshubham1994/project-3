package in.co.rays.proj3.exception;



/**
 * RecordNotFoundException thrown when a record not found occurred
 * @author shubham sharma
 *
 */
public class RecordNotFoundException extends Exception{

    /**
     * @param msg
     * error message
     */
    public RecordNotFoundException(String msg) {
        super(msg);

    }
}