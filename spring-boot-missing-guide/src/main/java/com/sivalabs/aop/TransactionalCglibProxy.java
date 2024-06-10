package com.sivalabs.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class TransactionalCglibProxy {

    public <T> T createProxy(T object) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(getMethodInterceptor());
        return (T)enhancer.create();
    }

    private static MethodInterceptor getMethodInterceptor() {
        return (obj, method, args, proxy) -> {
            try {
                System.out.println("Start transaction");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("Commit transaction");
                return result;
            } catch (RuntimeException e) {
                System.out.println("Rollback transaction");
                throw e;
            } finally {
                System.out.println("Close connection");
            }
        };
    }
}
