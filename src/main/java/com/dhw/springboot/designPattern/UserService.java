package com.dhw.springboot.designPattern;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

@Service
@Log4j2
public class UserService implements IListenerable {
    @Resource
    private IListener listener;

    //注册监听器
    @Override
    public void setListener(IListener listener) {
        this.listener = listener;
    }

    //触发监听器
    @Override
    public void triggerListener(IUserEvent event) {
        listener.handle(event);
    }


    @HystrixCommand(fallbackMethod = "saveStudentFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public void saveStudent(User user) throws HystrixTimeoutException {
        log.info("插入了一条数据" + user.toString());
        IUserEvent event = new UserEvent(this, "saveStudent");
        this.triggerListener(event);

        callTimeoutMethod();
    }

    private void callTimeoutMethod() throws HystrixTimeoutException {
        try {
            Thread.sleep(1000l);
            throw new HystrixTimeoutException();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

    }

    public void removeStudent() {
        System.out.println("删除了一条数据");
        IUserEvent event = new UserEvent(this, "removeStudent");
        this.triggerListener(event);
    }

    public void modifyStudent() {
        System.out.println("修改了一条数据");
        IUserEvent event = new UserEvent(this, "modifyStudent");
        this.triggerListener(event);
    }

    public void findStudent() {
        System.out.println("插入了一条数据");
        IUserEvent event = new UserEvent(this, "findStudent");
        this.triggerListener(event);
    }


    public void saveStudentFallback(User user) {
        log.info("saveStudentFallback:" + user.toString());
    }

}
