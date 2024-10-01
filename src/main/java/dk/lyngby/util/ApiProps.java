package dk.lyngby.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiProps {

    // == HIBERNATE CONFIG FILE ==
    public static final String DB_NAME = "dog";
    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "postgres";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;

    // == API CONFIG ==
    public static final int PORT = 7777;
    public static final String API_CONTEXT = "/api/v1";

}
