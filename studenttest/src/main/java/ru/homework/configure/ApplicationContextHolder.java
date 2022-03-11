package ru.homework.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder {
    private static AnnotationConfigApplicationContext ctx;

    @Autowired
    public ApplicationContextHolder(final AnnotationConfigApplicationContext applicationContext) {
        ApplicationContextHolder.ctx = applicationContext;
    }

    public static AnnotationConfigApplicationContext getApplicationContext() {
        return ctx;
    }
}

