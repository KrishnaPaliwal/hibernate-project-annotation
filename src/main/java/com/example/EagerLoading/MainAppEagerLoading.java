package com.example.EagerLoading;

import org.hibernate.Session;

public class MainAppEagerLoading {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession()) {
        	
        	Employee employee = new Employee();
        	employee.setId(1L);
        	employee.setName("KK");
        	Address address = new Address();
        	address.setCity("Indore");
        	address.setId(1L);
        	address.setState("MP");
        	address.setZipCode("33333");
        	employee.setAddress(address);
        	session.beginTransaction();
        	session.save(address);
        	session.save(employee);
        	session.getTransaction().commit();
            Employee employee1 = session.get(Employee.class, 1L);

            if (employee1 != null) {
                System.out.println("Employee Name: " + employee1.getName());
                Address address1 = employee1.getAddress();
                System.out.println("Address: " + address1.getStreet() + ", " + address1.getCity() +
                        ", " + address1.getState() + ", " + address1.getZipCode());
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //HibernateUtil.shutdown();
        }
    }
}
