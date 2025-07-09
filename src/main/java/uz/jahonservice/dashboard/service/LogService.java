package uz.jahonservice.dashboard.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.jahonservice.dashboard.dto.LogDto;
import uz.jahonservice.dashboard.dto.response.ApiResponse;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface LogService {
    ApiResponse<List<LogDto >> saveLogFromFile(MultipartFile file);

    ApiResponse<LogDto> uploadFromText(String text);

    ApiResponse<Map<Date, Integer>> getDailyLog(LocalDate start, LocalDate end);

    ApiResponse<Map<String, Integer>> getDstCountry(LocalDate startTime, LocalDate endDate);

    ApiResponse<Map<String, Integer>> getServiceCount(LocalDate startDate, LocalDate endDate);

    ApiResponse<Page<LogDto>> getAllLog(Integer page, Integer size, LocalDate startDate, LocalDate endDate, String ip);

//    ApiResponse<Page<LogDto>> getAllLog1(Integer page, Integer size);
//
//    ApiResponse<Page<LogDto>> getWithTime(Integer page, Integer size, LocalDate start, LocalDate end);
//
//    ApiResponse<Page<LogDto>> getSortedListIp(Integer page, Integer size, String ip, LocalDate startTime, LocalDate endTime);

}
