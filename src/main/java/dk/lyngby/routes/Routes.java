package dk.lyngby.routes;


import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

private final HotelRoute hotelRoute;
private final RoomRoute roomRoute;
EntityManagerFactory emf;

public Routes(EntityManagerFactory emf) {
    hotelRoute = new HotelRoute(emf);
    roomRoute = new RoomRoute(emf);
}
public EndpointGroup getApiRoutes() {
    return () -> {
      path("/hotel", hotelRoute.getHotelRoutes());
      path("/room", roomRoute.getRoomRoutes());
    };

}

}
