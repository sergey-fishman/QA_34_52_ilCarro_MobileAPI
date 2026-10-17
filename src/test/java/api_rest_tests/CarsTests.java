package api_rest_tests;

import controllers.CarController;
import data_providers.CarDataProvider;
import dto.BookingPeriodDto;
import dto.Car;
import dto.Cars;
import dto.SearchDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.TestNGListener;
import java.time.LocalDate;
import java.util.Arrays;
import static utils.CarFactory.*;
@Listeners(TestNGListener.class)

public class CarsTests extends CarController {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void addNewCarPositiveTest() {
        Car car = positiveCar();
        Response response = addNewCar(car);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void addNewCarWrongTokenNegativeTest() {
        Car car = positiveCar();
        Response response = addNewCar(car, "token");
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void addNewCarUnauthorizedNegativeTest() {
        Car car = positiveCar();
        Response response = addNewCar(car, null);
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void addNewCarOnlyMandatoryFieldsPositiveTest() {
        Car car = positiveCar();
        car.setAbout(null);
        car.setLat(null);
        car.setLng(null);
        car.setImage(null);
        car.setOwner(null);
        car.setBookedPeriods(null);
        Response response = addNewCar(car);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dataProvider = "dataProviderBlankValues", dataProviderClass = CarDataProvider.class)
    public void addNewCarWithEmptyFieldsWithIteratorNegativeTest(Car car) {
        Response response = addNewCar(car);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void addNewCarWrongMethodNegativeTest(){
        Car car = positiveCar();
        Response response = addNewCarWithMethodAndContentType(car, "get", ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void addNewCarWrongContentTypeNegativeTest(){
        Car car = positiveCar();
        Response response = addNewCarWithMethodAndContentType(car, "post", ContentType.TEXT);
        Assert.assertEquals(response.getStatusCode(), 500);
    }

    @Test
    public void getUserCarsPositiveTest() {
        Response response = getUserCars();
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is 200");
        Cars cars = response.getBody().as(Cars.class);
        softAssert.assertNotNull(cars.getCars(), "Cars list is not null");
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

        Response userCarsResponse = getUserCars();
        softAssert.assertEquals(userCarsResponse.getStatusCode(), 200,
                "Get user cars has status code 200");

        Cars cars = userCarsResponse.getBody().as(Cars.class);
        boolean carStillPresent = cars.getCars() != null && Arrays.stream(cars.getCars())
                        .anyMatch(c -> car.getSerialNumber().equals(c.getSerialNumber()));
        softAssert.assertFalse(carStillPresent, "Deleted car is in the list");

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
                .allMatch(c -> searchDto.getCity().equals(c.getCity()));

        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is 200");
        softAssert.assertTrue(allInCity, "Requested cities match returned cities");
        softAssert.assertAll();
    }

}
