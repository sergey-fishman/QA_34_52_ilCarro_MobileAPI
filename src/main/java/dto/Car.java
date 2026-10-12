package dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private Integer seats;
    private String carClass;
    private Double pricePerDay;
    private String about;
    private String city;
    private Double lat;
    private Double lng;
    private String image;
    private String owner;
    private BookedDto[] bookedPeriods;
}