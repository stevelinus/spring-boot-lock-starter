package org.springframework.boot.autoconfigure.klock.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.exception.LockFailedException;
import org.springframework.boot.autoconfigure.klock.lock.Lock;
import org.springframework.boot.autoconfigure.klock.lock.LockFactory;
import org.springframework.stereotype.Component;

/**
 * Created by kl on 2017/12/29.
 * Content :给添加@KLock切面加锁处理
 */
@Aspect
@Component
public class KlockAspectHandler {

    private LockFactory lockFactory;

    @Autowired
    public KlockAspectHandler(LockFactory lockFactory) {
        this.lockFactory = lockFactory;
    }

    @Around(value = "@annotation(klock)")
    public Object around(ProceedingJoinPoint joinPoint, Klock klock) throws Throwable {
        Lock lock = lockFactory.getLock(joinPoint, klock);
        boolean currentThreadLock = false;
        try {
            currentThreadLock = lock.acquire();
            if (currentThreadLock) {
                return joinPoint.proceed();
            } else {
                throw new LockFailedException();
            }
        } finally {
            if (currentThreadLock) {
                lock.release();
            }
        }
    }

}
