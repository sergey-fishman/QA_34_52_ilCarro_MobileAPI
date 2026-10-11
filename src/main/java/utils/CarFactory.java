package utils;
import dto.Car;
import net.datafaker.Faker;
import java.time.LocalDate;

public class CarFactory {
    public static Faker faker = new Faker();

    public static Car positiveCar() {
        return Car.builder()
                .city("Berlin")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween
                        (0, LocalDate.now().getYear())))
                .fuelTypeLocators("Diesel")
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().style())
                .serial(faker.vehicle().licensePlate())
                .price(faker.number().randomDouble(2, 0, 1000))
                .about(faker.text().text(0,500))
                .build();
    }
}

