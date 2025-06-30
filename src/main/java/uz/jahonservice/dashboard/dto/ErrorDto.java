package uz.jahonservice.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class ErrorDto {

    private String field;

    private String message;


}
