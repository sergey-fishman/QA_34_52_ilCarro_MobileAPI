package data_providers;

import dto.Car;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarDataProvider {

    @DataProvider
    public Iterator<Car> dataProviderBlankValues() {
        List<Car> carList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ("src/test/resources/test_data/blank_values_car.csv"))){
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] split = line.split(",", -1);
                carList.add(Car.builder()
                        .serialNumber(split[0])
                        .manufacture(split[1])
                        .model(split[2])
                        .year(split[3])
                        .fuel(split[4])
                        .seats(split[5].isBlank() ? null : Integer.parseInt(split[5]))
                        .carClass(split[6])
                        .pricePerDay(split[7].isBlank() ? null : Double.parseDouble(split[7]))
                        .city(split[8])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read test data", e);
        }
        return carList.listIterator();
    }
}
