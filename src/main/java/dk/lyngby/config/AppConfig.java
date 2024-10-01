package dk.lyngby.config;


import dk.lyngby.controller.ExceptionController;
import dk.lyngby.exception.ApiException;
import dk.lyngby.routes.Routes;
import dk.lyngby.util.ApiProps;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AppConfig {

   private static final ExceptionController exceptionController = new ExceptionController();
   private static final Routes routes = new Routes();
   private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

   private static void configuration(JavalinConfig config){
       config.router.contextPath = ApiProps.API_CONTEXT; //Base path for alle routes

       config.bundledPlugins.enableRouteOverview(("/routes"));
       config.bundledPlugins.enableDevLogging();

       config.router.apiBuilder(routes.getApiRoutes());
   }

   private static void setExceptionController(Javalin app){
       app.exception(ApiException.class, exceptionController::apiExceptionHandler);
       app.exception(Exception.class, exceptionController::exceptionHandler);
   }

    public static void startServer(){
        var app = Javalin.create(AppConfig::configuration);
        setExceptionController(app);
        app.start(ApiProps.PORT);
        log.info("Server started on port {}", ApiProps.PORT);
    }

}

