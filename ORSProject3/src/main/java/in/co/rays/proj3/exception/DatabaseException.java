package in.co.rays.proj3.exception;



/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * @author shubham sharma
 *
 */
public class DatabaseException extends Exception {

    /**
     * @param msg
     * Error message
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}