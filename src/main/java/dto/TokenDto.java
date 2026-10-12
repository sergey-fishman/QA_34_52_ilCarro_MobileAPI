package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString

public class TokenDto {
    private String accessToken;
}
