package dk.lyngby.dao;

import dk.lyngby.config.AppConfig;
import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dao.RoomDao;
import dk.lyngby.dto.RoomDto;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomDaoTest {
    private static Javalin app;
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final RoomDao roomDao = new RoomDao(emf);
    private static final String BASE_URL = "http://localhost:7777/api/v1";

    private static RoomDto r1, r2, r3;



    @BeforeAll
    void setupAll(){
        app = AppConfig.startServer(7777, emf);
    }
    @BeforeEach
    void setUp() {

        r1 = new RoomDto(1, 10, 100);
        r2 = new RoomDto(2, 20, 200);
        r3 = new RoomDto(3, 30, 300);
        r1 = roomDao.create(r1);
        r2 = roomDao.create(r2);
        r3 = roomDao.create(r3);


    }
    @AfterEach
    void tearDown() {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Room ").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE room_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    void tearDownAll(){
        AppConfig.stopServer(app);
    }

    @Test
    void getAll(){
        int actual = roomDao.getAll().size();
        assertEquals(3, actual);


    }
/*
    @Test
    void getById(){
        RoomDto actual = roomDao.getById(4);
        assertNotNull(actual);


    }

 */

    @Test
    void create(){
        RoomDto room = new RoomDto(4, 40, 400);
        RoomDto actual = roomDao.create(room);
        assertNotNull(actual);
        assertEquals(room.getNumber(), actual.getNumber());
        assertEquals(room.getPrice(), actual.getPrice());
    }
/*
    @Test
    void update(){


        r1.setPrice(500);
        roomDao.update(r1);
        RoomDto updated = roomDao.getById(r1.getId());
        assertEquals(500, updated.getPrice());
    }

 */
}