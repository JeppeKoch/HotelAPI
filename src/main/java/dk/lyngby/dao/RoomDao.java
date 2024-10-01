package dk.lyngby.dao;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dto.RoomDto;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class RoomDao implements IDAO<Room> {
    EntityManagerFactory emf;
    public RoomDao(EntityManagerFactory emf) {
        this.emf = emf;

    }
    @Override
    public List<Room> getAll() {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT d FROM Room d", Room.class).getResultList();
        }
    }

    @Override
    public Room getById(long id) {
        try (var em = emf.createEntityManager()) {
            return em.find(Room.class, id);
        }
    }

    @Override
    public void create(Room room) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(Room room, Room updateRoom) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            room.setId(updateRoom.getId());
            room.setHotel(updateRoom.getHotel());
            room.setPrice(updateRoom.getPrice());
            room.setHotelId(updateRoom.getHotelId());
            room.setNumber(updateRoom.getNumber());
            em.merge(room);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Room room = em.find(Room.class, id);
            em.remove(room);
            em.getTransaction().commit();
        }
    }
}
