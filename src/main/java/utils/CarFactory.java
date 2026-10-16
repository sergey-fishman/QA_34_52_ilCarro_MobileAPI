package utils;
import dto.BookedDto;
import dto.Car;
import net.datafaker.Faker;
import java.time.LocalDate;
import static utils.PropertiesReader.*;
public class CarFactory {

    public static Faker faker = new Faker();
    static String startDate = LocalDate.now().toString();
    static String endDate = LocalDate.now().plusDays(2).toString();
    static String username = getProperty("base.properties", "username");

    public static Car positiveCar() {
        return Car.builder()
                .city("Qiryat Bialik")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween
                        (0, LocalDate.now().getYear())))
                .fuel("Diesel")
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().style())
                .serialNumber(faker.vehicle().licensePlate())
                .pricePerDay(faker.number().randomDouble(2, 0, 1000))
                .about(faker.text().text(0,100))
                .lat(faker.number().randomDouble(2, 1,5))
                .lng(faker.number().randomDouble(2, 1,5))
                .image("random image URL")
                .owner(faker.breakingBad().character())
                .bookedPeriods(new BookedDto[] {new BookedDto(username, startDate, endDate)})
                .build();
    }
}

