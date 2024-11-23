package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) 
        throws
        ClassNotFoundException, 
        NoSuchMethodException, 
        InvocationTargetException, 
        InstantiationException, 
        IllegalAccessException, 
        IllegalArgumentException{
       final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberSwingView());
        try {
            Class<?> clazz = Class.forName("it.unibo.mvc.view.DrawNumberSTDOView");
            Constructor<?>[] ctr = clazz.getConstructors();  
            for(Constructor<?> c: ctr){
                if(c.getParameterCount() == 0);
                app.addView((DrawNumberView)c.newInstance());
                app.addView((DrawNumberView)c.newInstance());
                app.addView((DrawNumberView)c.newInstance());
            }        
        } catch (ClassNotFoundException | SecurityException e) {
            System.out.println("Class not found");
        }
    }
        
}

