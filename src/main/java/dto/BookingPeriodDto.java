package dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BookingPeriodDto {
    private String startDate;
    private String endDate;
}
