package org.example.framework.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.example.framework.attribute.ContextAttributes.SPRING_CONTEXT;

@jakarta.servlet.annotation.WebListener
public class WebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final var context = new AnnotationConfigApplicationContext("org.example.app");
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute(SPRING_CONTEXT, context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        final var servletContext = sce.getServletContext();
        final AnnotationConfigApplicationContext springContext = (AnnotationConfigApplicationContext) servletContext.getAttribute(SPRING_CONTEXT);
        springContext.close();
    }
}