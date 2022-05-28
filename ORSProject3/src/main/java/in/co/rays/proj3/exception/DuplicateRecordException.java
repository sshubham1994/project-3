package in.co.rays.proj3.exception;


/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * @author shubham sharma
 *
 */
public class DuplicateRecordException extends Exception {

    /**
     * @param msg
     * error message
     */
    public DuplicateRecordException(String msg) {
        super(msg);
    }

}