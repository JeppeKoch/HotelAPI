package dk.lyngby.routes;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.controller.HotelController;
import dk.lyngby.dao.HotelDao;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class HotelRoute {


    HotelController hotelController;

    public HotelRoute(EntityManagerFactory emf) {
       hotelController = new HotelController(new HotelDao(emf));
    }

    public EndpointGroup getHotelRoutes(){
        return () ->{
            get("/", hotelController::getAll);
            get("/{id}", hotelController::getById);
            post("/", hotelController::create);
            put("/{id}", hotelController::update);
            delete("/{id}", hotelController::delete);};
        };
    }

