package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Employee employee1 = new Employee("Isabel", "Riquelme", "Audix");
            Employee employee2 = new Employee("Luis", "Zuniga", "Nisum");
            Employee employee3 = new Employee("John", "Doe", "Love2Code");

            session.beginTransaction();

            session.save(employee1);
            session.save(employee2);
            session.save(employee3);

            Employee employee4 = session.get(Employee.class, 1);
            System.out.println("Found in database:" + employee4);

            List<Employee> employees = session.createQuery("from Employee").getResultList();

            for (Employee employee: employees) {
                System.out.println(employee);
            }

            session.getTransaction().commit();

            System.out.println("Done!");
        }

        finally {
            factory.close();
        }
    }
}
