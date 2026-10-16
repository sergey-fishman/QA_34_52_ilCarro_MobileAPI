package api_rest_tests;

import controllers.CarController;
import dto.BookingPeriodDto;
import dto.Car;
import dto.Cars;
import dto.SearchDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.LocalDate;
import java.util.Arrays;

import static utils.CarFactory.*;

public class CarsTests extends CarController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void addNewCarPositiveTest() {
        Car car = positiveCar();
        Response response = addNewCar(car);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getUserCarsPositiveTest() {
        Response response = getUserCars();
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        Cars cars = response.getBody().as(Cars.class);
        softAssert.assertNotNull(cars.getCars(), "Cars list is null");
        softAssert.assertAll();
    }

    @Test
    public void bookCarBySerialNumberPositiveTest() {
        Car car = positiveCar();
        softAssert.assertEquals(addNewCar(car).getStatusCode(), 200);

        BookingPeriodDto period = BookingPeriodDto.builder()
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().plusDays(2).toString())
                .build();
        Response response = bookCarBySerialNumber(car.getSerialNumber(), period);
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();
    }

    @Test
    public void deleteCarBySerialNumberPositiveTest() {
        Car car = positiveCar();
        softAssert.assertEquals(addNewCar(car).getStatusCode(), 200);
        Response response = deleteCarBySerialNumber(car.getSerialNumber());
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();
    }

    @Test
    public void searchCarByCityAndPeriodPositiveTest() {
        SearchDto searchDto = SearchDto.builder()
                .city(positiveCar().getCity())
                .startDate(LocalDate.now().toString())
                .endDate(LocalDate.now().plusDays(2).toString())
                .build();

        Response response = searchCarByCityPeriod(searchDto);
        Cars cars = response.getBody().as(Cars.class);
        boolean allInCity = Arrays.stream(cars.getCars())
                .allMatch(c -> positiveCar().getCity().equals(c.getCity()));

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertTrue(allInCity);
        softAssert.assertAll();
    }

}
