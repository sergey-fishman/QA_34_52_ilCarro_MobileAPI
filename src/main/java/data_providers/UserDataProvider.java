package data_providers;

import dto.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {

    @DataProvider
    public Iterator<User> dataProviderPositiveCombinations() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ("src/test/resources/test_data/positive_combination.csv"))){
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                userList.add(User.builder()
                        .username(splitLine[0])
                        .password(splitLine[1])
                        .firstName(splitLine[2])
                        .lastName(splitLine[3])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return userList.listIterator();
    }

    @DataProvider
    public Iterator<User> dataProviderWrongPassword() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ("src/test/resources/test_data/wrong_password.csv"))){
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                userList.add(User.builder()
                        .username(splitLine[0])
                        .password(splitLine[1])
                        .firstName(splitLine[2])
                        .lastName(splitLine[3])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return userList.listIterator();
    }

    @DataProvider
    public Iterator<User> dataProviderWrongUsername() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                ("src/test/resources/test_data/wrong_username.csv"))){
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitLine = line.split(",");
                userList.add(User.builder()
                        .username(splitLine[0])
                        .password(splitLine[1])
                        .firstName(splitLine[2])
                        .lastName(splitLine[3])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created exception");
        }
        return userList.listIterator();
    }
}
