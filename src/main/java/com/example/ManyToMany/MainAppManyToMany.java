package com.example.ManyToMany;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainAppManyToMany {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            ProductDaoImpl productDao = new ProductDaoImpl();
            productDao.setSessionFactory(sessionFactory);

            // Create students and classes
            Student student1 = new Student();
            student1.setName("John");

            Student student2 = new Student();
            student2.setName("Alice");

            Class class1 = new Class();
            class1.setName("Math");

            Class class2 = new Class();
            class2.setName("History");

            // Enroll students in classes
            student1.getClasses().add(class1);
            student1.getClasses().add(class2);

            student2.getClasses().add(class1);

            class1.getStudents().add(student1);
            class1.getStudents().add(student2);

            class2.getStudents().add(student1);

            // Save students and classes to the database
            productDao.saveStudent(student1);
            productDao.saveStudent(student2);

            productDao.saveClass(class1);
            productDao.saveClass(class2);

            // Retrieve students and their enrolled classes from the database
            System.out.println("Students and their enrolled classes:");
            List<Student> students = productDao.getAllStudents();
            for (Student student : students) {
                System.out.println(student.getName() + " - Enrolled in classes:");
                for (Class enrolledClass : student.getClasses()) {
                    System.out.println("\t" + enrolledClass.getName());
                }
            }

            // Retrieve classes and their enrolled students from the database
            System.out.println("\nClasses and their enrolled students:");
            List<Class> classes = productDao.getAllClasses();
            for (Class classObj : classes) {
                System.out.println(classObj.getName() + " - Enrolled students:");
                for (Student enrolledStudent : classObj.getStudents()) {
                    System.out.println("\t" + enrolledStudent.getName());
                }
            }
        }
    }
}
