package com.sivalabs.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionJdkProxy<T> implements InvocationHandler {

    private final T target;

    public TransactionJdkProxy(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println("Start transaction");
            Object result = method.invoke(target, args);
            System.out.println("Commit transaction");
            return result;
        } catch (RuntimeException | InvocationTargetException e) {
            System.out.println("Rollback transaction");
            throw e;
        } finally {
            System.out.println("Close connection");
        }
    }
}