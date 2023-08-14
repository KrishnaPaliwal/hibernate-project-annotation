package com.example.LazyLoading;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainLazyLoading {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotelPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Hotel hotel = new Hotel();
            hotel.setName("Luxury Resort");

            Room room1 = new Room();
            room1.setType("Standard");
            room1.setHotel(hotel);

            Room room2 = new Room();
            room2.setType("Suite");
            room2.setHotel(hotel);

            hotel.getRooms().add(room1);
            hotel.getRooms().add(room2);

            em.persist(hotel);

            em.getTransaction().commit();

            em.getTransaction().begin();
            Hotel fetchedHotel = em.find(Hotel.class, hotel.getId());
            List<Room> rooms = fetchedHotel.getRooms(); // Lazy loading triggered here

            for (Room room : rooms) {
                System.out.println("Room Type: " + room.getType());
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}
