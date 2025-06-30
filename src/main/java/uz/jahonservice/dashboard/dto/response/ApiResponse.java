package uz.jahonservice.dashboard.dto.response;

import lombok.*;
import uz.jahonservice.dashboard.dto.ErrorDto;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private int code;  //todo: -2 validate error; -3 database exception

    private String message;

    private boolean success;

    private T log;

    private List<ErrorDto> errorList;

}

