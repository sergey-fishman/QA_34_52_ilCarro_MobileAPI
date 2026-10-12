package api_rest_tests;

import controllers.CarController;
import dto.Car;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static utils.CarFactory.*;

public class CarsTests extends CarController {
    @Test
    public void addNewCarPositiveTest(){
        Car car = positiveCar();
        Response response = addNewCar(car);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
