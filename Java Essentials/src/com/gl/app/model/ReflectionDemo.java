package com.gl.app.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Person class
class Person {
    private String name;
    private int age;

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Private method
    private void sayHello() {
        System.out.println("Hello, my name is " + name);
    }
}

// ReflectionUtil class
class ReflectionUtil {

    // Print all field names and their values
    public static void printFieldNamesAndValues(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Access private fields
            try {
                System.out.println(field.getName() + " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // Invoke private method
    public static void invokePrivateMethod(Object obj, String methodName, Object... args) {
        try {
            Class<?> clazz = obj.getClass();

            // Determine parameter types
            Class<?>[] paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }

            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true); // Access private method
            method.invoke(obj, args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Main class
public class ReflectionDemo {
    public static void main(String[] args) {

        // Create Person object and set fields
        Person person = new Person();
        person.setName("Alice");
        person.setAge(25);

        // Print field names and values using reflection
        System.out.println("Person fields:");
        ReflectionUtil.printFieldNamesAndValues(person);

        // Invoke private sayHello() method using reflection
        System.out.println("\nInvoking private method:");
        ReflectionUtil.invokePrivateMethod(person, "sayHello");
    }
}
