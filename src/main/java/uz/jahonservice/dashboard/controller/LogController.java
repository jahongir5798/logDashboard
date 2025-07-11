package uz.jahonservice.dashboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.jahonservice.dashboard.dto.LogDto;
import uz.jahonservice.dashboard.dto.response.ApiResponse;
import uz.jahonservice.dashboard.repository.LogRepository;
import uz.jahonservice.dashboard.service.LogService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/log/")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    private final LogRepository logRepository;

    /*todo:log qabul qilib olish ustida amallar bajarish kerak(qidirish, filtrlash)*/
    @PostMapping("upload/fromFile")
    public ApiResponse<List<LogDto >> saveLogFromFile(
            @RequestParam("file") MultipartFile file
    ) {
        log.info("log controller upload from file invoked");
        ApiResponse<List<LogDto>> response = logService.saveLogFromFile(file);
        log.info("log controller upload from file response");
        return response;
    }

    @PostMapping("upload/fromText")
    public ApiResponse<LogDto> saveLogFromText(
            @RequestParam("text") String text
    ) {
        log.info("log controller upload from text invoked");
        ApiResponse<LogDto> response = logService.uploadFromText(text);
        log.info("log controller upload from text response");
        return response;
    }

    @GetMapping("get-all-log")
    public ApiResponse<Page<LogDto>> getAllLog(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String ip
    ) {
        return logService.getAllLog(page, size, startDate, endDate, ip);
    }

   /* @GetMapping("get-all-log")
    public ApiResponse<Page<LogDto>> getAllLog(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        log.info("log controller get-all-log invoked");
        ApiResponse<Page<LogDto>> allLog = logService.getAllLog1(page, size);
        log.info("log controller get-all-log response");
        return allLog;
    }*/

   /* @GetMapping("get-with-time")
    public ApiResponse<Page<LogDto>> getWithTime(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        log.info("log controller get-with-time invoked");
        ApiResponse<Page<LogDto>> withTime = logService.getWithTime(page, size, start, end);
        log.info("log controller get-with-time response");
        return withTime;
    }*/

   /* @GetMapping("get-sorted-ip-log")
    public ApiResponse<Page<LogDto>> getSortedIpLog(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String ip,
            @RequestParam LocalDate startTime,
            @RequestParam LocalDate endTime
    ) {
        log.info("log control get sorted ip logs invoked");
        ApiResponse<Page<LogDto>> sortedListIp = logService.getSortedListIp(page, size, ip, startTime, endTime);
        log.info("log control get sorted ip logs response");
        return sortedListIp;
    }*/

     @GetMapping("get-daily-log")
    public ApiResponse<Map<Date, Integer>> dailyLog(
            @RequestParam Integer range
    ) {
        log.info("log control get daily logs invoked");
        ApiResponse<Map<Date, Integer>> dailyLog = logService.getDailyLog(range);
        log.info("log control get daily logs response");
        return dailyLog;
    }

    @GetMapping("get-dst-country")
    public ApiResponse<Map<String, Integer>> dstCountry(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ){
        log.info("log control get dst country invoked");
        ApiResponse<Map<String, Integer>> dstCountry = logService.getDstCountry(startDate, endDate);
        log.info("log control get dst country response");
        return dstCountry;
    }

     @GetMapping("get-services-count")
    public ApiResponse<Map<String, Integer>> getServicesCount(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ){
        log.info("log control get-services-count invoked");
        ApiResponse<Map<String, Integer>> serviceCount = logService.getServiceCount(startDate, endDate);
        log.info("log control get-services-count response");
        return serviceCount;
    }

}
