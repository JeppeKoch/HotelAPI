package dk.lyngby.routes;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.HotelController;
import dk.lyngby.controller.RoomController;
import dk.lyngby.dao.HotelDao;
import dk.lyngby.dao.RoomDao;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class RoomRoute {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("hotel");
    RoomDao roomDao = new RoomDao(emf);
    RoomController roomController = new RoomController(roomDao);

    public EndpointGroup getRoomRoutes(){
        return () ->{
            get("/", roomController::getAll);
            get("/{id}", roomController::getById);
            post("/", roomController::create);
            put("/{id}", roomController::update);
            delete("/{id}", roomController::delete);};
    };
}
