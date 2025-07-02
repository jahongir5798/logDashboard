package uz.jahonservice.dashboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.jahonservice.dashboard.dto.LogDto;
import uz.jahonservice.dashboard.dto.PageRequestDto;
import uz.jahonservice.dashboard.dto.response.ApiResponse;
import uz.jahonservice.dashboard.repository.LogRepository;
import uz.jahonservice.dashboard.service.LogService;

import java.sql.Date;
import java.time.LocalDate;
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
    public ApiResponse<Void> saveLogFromFile(
            @RequestParam("file") MultipartFile file
    ) {
        log.info("log controller upload from file invoked");
        ApiResponse<Void> response = logService.saveLogFromFile(file);
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
    public ApiResponse<Page<LogDto>> getAllLog(@RequestBody PageRequestDto dto) {
        log.info("log controller get-all-log invoked");
        ApiResponse<Page<LogDto>> allLog = logService.getAllLog(dto);
        log.info("log controller get-all-log response");
        return allLog;
    }

    @GetMapping("get-with-time")
    public ApiResponse<Page<LogDto>> getWithTime(
            @RequestBody PageRequestDto dto,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        log.info("log controller get-with-time invoked");
        ApiResponse<Page<LogDto>> withTime = logService.getWithTime(dto, start, end);
        log.info("log controller get-with-time response");
        return withTime;
    }

    @GetMapping("get-daily-log")
    public ApiResponse<Map<Date, Integer>> dailyLog(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        log.info("log control get daily logs invoked");
        ApiResponse<Map<Date, Integer>> dailyLog = logService.getDailyLog(startDate, endDate);
        log.info("log control get daily logs response");
        return dailyLog;
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


    @GetMapping("get-dst-country")
    public ApiResponse<Map<String, Integer>> dstCountry(
            @RequestParam LocalDate startTime,
            @RequestParam LocalDate endDate
    ){
        log.info("log control get dst country invoked");
        ApiResponse<Map<String, Integer>> dstCountry = logService.getDstCountry(startTime, endDate);
        log.info("log control get dst country response");
        return dstCountry;
    }

    @GetMapping("get-sorted-ip-log")
    public ApiResponse<Page<LogDto>> getSortedIpLog(
            @RequestBody PageRequestDto dto,
            @RequestParam String ip,
            @RequestParam LocalDate startTime,
            @RequestParam LocalDate endTime
    ) {
        log.info("log control get sorted ip logs invoked");
        ApiResponse<Page<LogDto>> sortedListIp = logService.getSortedListIp(dto, ip, startTime, endTime);
        log.info("log control get sorted ip logs response");
        return sortedListIp;
    }



   /* @GetMapping("get-all-log")
    public ApiResponse<org.hibernate.query.Page> getAllLog(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String pattern
    ) {
        log.info("log controller get all log method invoked");
        ApiResponse<org.hibernate.query.Page<LogDto>> response = logService.getAllLog(page, size, pattern);
        log.info("log controller get all log method response");
        return response;
    }

    @GetMapping("get-all-log-with-time")
    public PageResponse<List<LogDto>> getAllLogWithTime(
            @RequestParam Integer size,
            @RequestParam Integer page,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime
    ){
        log.info("log controller get all log with-time method invoked");
        PageResponse<List<LogDto>> allLogWithTime = logService.getAllLogWithTime(size, page, startTime, endTime);
        log.info("log controller get all log with-time method response");
        return allLogWithTime;
    }*/

    /*@GetMapping("GET")
    public ApiResponse<Page<LogEntity>> getLog(@RequestBody PageRequestDto dto){

        PageRequest pageable = PageRequest.of(dto.getPage(), dto.getSize());

        Page<LogEntity> all = logRepository.findAll(pageable);
        return ApiResponse.<Page<LogEntity>>builder()
                .code(0)
                .message("true")
                .success(true)
                .errorList(null)
                .log(all)
                .build();

    }*/


}
