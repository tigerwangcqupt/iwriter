
package com.yryz.writer.modules.id.exception;

/**
 * UidGenerateException
 * 
 * @author
 */
public class IdGenerateException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -27048199131316992L;

    /**
     * Default constructor
     */
    public IdGenerateException() {
        super();
    }

    /**
     * Constructor with message & cause
     * 
     * @param message
     * @param cause
     */
    public IdGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     * 
     * @param message
     */
    public IdGenerateException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message format
     * 
     * @param msgFormat
     * @param args
     */
    public IdGenerateException(String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
    }

    /**
     * Constructor with cause
     * 
     * @param cause
     */
    public IdGenerateException(Throwable cause) {
        super(cause);
    }

}
