package org.springframework.boot.autoconfigure.klock.test;

import org.springframework.boot.autoconfigure.klock.annotation.Klock;
import org.springframework.boot.autoconfigure.klock.annotation.KlockKey;
import org.springframework.boot.autoconfigure.klock.exception.LockFailedException;
import org.springframework.stereotype.Service;

/**
 * Created by kl on 2017/12/29.
 */
@Service
public class TestService {

    @Klock(waitTime = 10, leaseTime = 60, keys = {"#param"})
    public String getValue(String param) throws InterruptedException, LockFailedException {
        //  if ("sleep".equals(param)) {//线程休眠或者断点阻塞，达到一直占用锁的测试效果
        Thread.sleep(1000);
        //}
        return "success";
    }

    @Klock(keys = {"#userId"}, waitTime = 1000, leaseTime = 1000)
    public String getValue(String userId, @KlockKey int id) throws InterruptedException, LockFailedException {
        Thread.sleep(1000);
        return "success";
    }

    @Klock(keys = {"#user.name", "#user.id"})
    public String getValue(User user) throws InterruptedException, LockFailedException {
        Thread.sleep(1000);
        return "success";
    }

}
