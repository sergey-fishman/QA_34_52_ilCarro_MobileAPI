package dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SearchDto {
    private String city;
    private String startDate;
    private String endDate;
}
