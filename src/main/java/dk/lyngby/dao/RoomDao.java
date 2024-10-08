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
public class RoomDao {
    EntityManagerFactory emf;
    public RoomDao(EntityManagerFactory emf) {
        this.emf = emf;

    }

    public List<Room> getAll() {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT new dk.lyngby.dto.RoomDto(d) FROM Room d", Room.class).getResultList();
        }
    }


    public RoomDto getById(long id) {
        try (var em = emf.createEntityManager()) {
            return em.find(RoomDto.class, id);
        }
    }


    public RoomDto create(RoomDto roomDto) {
        try (var em = emf.createEntityManager()) {
            Room room = new Room(roomDto);
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
            return new RoomDto(room);
        }
    }


    public RoomDto update(RoomDto roomDto) {
        try (var em = emf.createEntityManager()) {
            Room room = new Room(roomDto);
            em.getTransaction().begin();
            Room updatedRoom = em.merge(room);
            em.getTransaction().commit();
            return new RoomDto(updatedRoom);
        }
    }


    public void delete(long id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            RoomDto room = em.find(RoomDto.class, id);
            em.remove(room);
            em.getTransaction().commit();
        }
    }
}
