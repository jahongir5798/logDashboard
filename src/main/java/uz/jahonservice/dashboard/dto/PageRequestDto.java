package uz.jahonservice.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequestDto {

    private int page;

    private int size;

}
