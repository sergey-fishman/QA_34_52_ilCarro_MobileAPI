package dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BookedDto {
    private String email;
    private String startDate;
    private String endDate;
}
