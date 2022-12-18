package com.demo.exportcsvdemo.service;

import com.demo.exportcsvdemo.annotation.MsgSender;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

@Service
public class AnnotationReflection {

    public void test() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MsgSender.class));

        for (BeanDefinition bd: scanner.findCandidateComponents("com.demo.exportcsvdemo")) {
            String beanName = bd.getBeanClassName();
            Class c = Class.forName(beanName);
            Annotation an = c.getAnnotation(MsgSender.class);

            if( an != null && an.annotationType() == MsgSender.class){
                MsgSender senderAn = (MsgSender) an;
                String type = senderAn.type();
                String subType = senderAn.subType();

                System.out.println("type -> " + type);
                System.out.println("subType -> " + subType);
            }
        }

    }

}
