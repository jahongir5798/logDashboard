package uz.jahonservice.dashboard.service;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.jahonservice.dashboard.dto.LogDto;
import uz.jahonservice.dashboard.dto.PageRequestDto;
import uz.jahonservice.dashboard.dto.response.ApiResponse;
import uz.jahonservice.dashboard.dto.response.PageResponse;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface LogService {
    ApiResponse<Void> saveLogFromFile(MultipartFile file);

    ApiResponse<LogDto> uploadFromText(String text);


    ApiResponse<Page<LogDto>> getAllLog(PageRequestDto dto);

    ApiResponse<Page<LogDto>> getWithTime(PageRequestDto dto, LocalDate start, LocalDate end);

    ApiResponse<Map<Date, Integer>> getDailyLog(LocalDate start, LocalDate end);

    ApiResponse<Map<String, Integer>> getDstCountry(LocalDate startTime, LocalDate endDate);

    ApiResponse<Page<LogDto>> getSortedListIp(PageRequestDto dto, String ip, LocalDate startTime, LocalDate endTime);
}
