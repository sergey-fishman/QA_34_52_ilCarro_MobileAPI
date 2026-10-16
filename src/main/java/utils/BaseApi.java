package utils;

public interface BaseApi {
    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    String REGISTRATION_URL = "/v1/user/registration/usernamepassword";
    String LOGIN_URL = "/v1/user/login/usernamepassword";
    String CARS_URL = "/v1/cars";
    String CARS_MY_URL = "/v1/cars/my";
    String CARS_SEARCH_URL = "/v1/cars/search";

    String AUTH = "Authorization";
}
