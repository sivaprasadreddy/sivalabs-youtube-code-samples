package com.sivalabs.di;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private final Map<Class<?>, Object> BEANS = init();

    //scan all the classes and identify beans annotated with @Component
    //Identify what are the dependencies for each component using Reflection

    private Map<Class<?>, Object> init() {
        Map<Class<?>, Object> beanMap = new HashMap<>();
        String impl = "MockBackgroundVerificationService";
        IBackgroundVerificationService bgvs = null;
        if(impl.equals("MockBackgroundVerificationService")) {
            bgvs = new MockBackgroundVerificationService();
        } else {
            bgvs = new BackgroundVerificationService();
        }
        LoanService loanService = new LoanService(bgvs);

        beanMap.put(IBackgroundVerificationService.class, bgvs);
        beanMap.put(LoanService.class, loanService);
        return beanMap;
    }

    public <T> T getBean(Class type) {
        return (T)BEANS.get(type);
    }
}
