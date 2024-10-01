package dk.lyngby.dao;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dto.HotelDto;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class HotelDao implements IDAO<Hotel> {
    EntityManagerFactory emf;

    public HotelDao(EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public List<Hotel> getAll() {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT d FROM Hotel d", Hotel.class).getResultList();
        }
    }

    @Override
    public Hotel getById(long id) {
        try (var em = emf.createEntityManager()) {
            return em.find(Hotel.class, id);
        }
    }

    @Override
    public void create(Hotel hotel) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (hotel.getId() != null) {
                em.merge(hotel);
            }
            em.persist(hotel);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(Hotel hotel, Hotel updateHotel) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
           Hotel existingHotel =  em.find(Hotel.class, hotel.getId());
            existingHotel.setName(updateHotel.getName());
            existingHotel.setAddress(updateHotel.getAddress());
            existingHotel.setRooms(updateHotel.getRooms());
            em.merge(existingHotel);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Hotel hotel = em.find(Hotel.class, id);
            em.remove(hotel);
            em.getTransaction().commit();
        }
    }


    public List<Room> allRoomsForSpecificHotel(long hotelId) {
       {
            try (var em = emf.createEntityManager()) {
                return em.createQuery("SELECT r FROM Room r WHERE r.hotel.id = :hotelId", Room.class)
                        .setParameter("hotelId", hotelId)
                        .getResultList();
            }
        }
    }


}
