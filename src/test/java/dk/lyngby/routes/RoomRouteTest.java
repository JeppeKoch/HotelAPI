package dk.lyngby.routes;

import dk.lyngby.config.AppConfig;
import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dao.RoomDao;
import dk.lyngby.dto.RoomDto;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoomRouteTest {
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

    /*
@Test
    void getRoomsEasy(){
        given()
                .when()
                .get(BASE_URL + "/room")
                .then()
                .statusCode(200)
                .body("size()", is(3));
}

@Test
    void getRoomsHard(){
        RoomDto[] rooms = given()
                .when()
                .get(BASE_URL + "/room")
                .then()
                .statusCode(200)
                .extract()
                .as(RoomDto[].class);
    List<RoomDto> dtos = Arrays.asList(rooms);
        assertThat(dtos, containsInAnyOrder(r1, r2, r3));
}

     */
}