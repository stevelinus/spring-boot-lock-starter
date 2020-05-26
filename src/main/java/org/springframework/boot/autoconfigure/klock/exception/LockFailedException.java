package org.springframework.boot.autoconfigure.klock.exception;

/**
 * @author weijuntao
 * <p>
 * 加锁失败和没有拿到锁, 就会抛出这个异常。这个异常必须处理
 */
public class LockFailedException extends RuntimeException {

    private static final long serialVersionUID = 7160531484988272093L;

    public LockFailedException() {
        super();
    }

    public LockFailedException(String message) {
        super(message);
    }

    public LockFailedException(Throwable cause) {
        super(cause);
    }

    public LockFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
