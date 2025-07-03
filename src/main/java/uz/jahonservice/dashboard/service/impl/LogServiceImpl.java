package uz.jahonservice.dashboard.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.jahonservice.dashboard.dto.LogDto;
import uz.jahonservice.dashboard.dto.PageRequestDto;
import uz.jahonservice.dashboard.dto.response.ApiResponse;
import uz.jahonservice.dashboard.dto.response.PageResponse;
import uz.jahonservice.dashboard.entity.LogEntity;
import uz.jahonservice.dashboard.exception.DatabaseException;
import uz.jahonservice.dashboard.repository.LogRepository;
import uz.jahonservice.dashboard.service.LogService;
import uz.jahonservice.dashboard.service.mapper.LogMapper;
import uz.jahonservice.dashboard.service.validation.LogParser;
import uz.jahonservice.dashboard.service.validation.LogValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogValidator logValidator;
    private final LogMapper logMapper;
    private final LogParser logParser;

    @Override
    public ApiResponse<List<LogDto>> saveLogFromFile(MultipartFile file) {
        try {
            List<String> lines = new BufferedReader(new InputStreamReader(file.getInputStream()))
                    .lines()
                    .toList();
            List<String> logs = new ArrayList<>();
            List<LogDto> logDtos = new ArrayList<>();

            StringBuilder currentLog = new StringBuilder();

            for (String line : lines) {
                if (line.startsWith("date=")) {
                    if (!currentLog.isEmpty()) {
                        logs.add(currentLog.toString());
                        currentLog.setLength(0);
                    }
                }
                currentLog.append(line).append(" ");
            }

            if (!currentLog.isEmpty()) {
                logs.add(currentLog.toString());
            }

            for (int i = 0; i < logs.size(); i++) {
                LogEntity logEntity = new LogEntity();
//                System.out.println("Log #" + (i + 1));
//                System.out.println(logs.get(i));
//                System.out.println("----------------------------------------------------");
                logDtos.add(
                        logMapper.toDto(
                                logRepository.save(
                                        logParser.toLogEntity(
                                                logParser.parseLog(
                                                        logs.get(i)), logEntity
                                        )
                                )
                        )
                );
            }


            return ApiResponse.<List<LogDto>>builder()
                    .code(0)
                    .message("OK")
                    .success(true)
                    .log(logDtos)
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponse.<List<LogDto>>builder()
                .code(-1)
                .message("ERROR")
                .success(false)
                .build();
    }

//    @Override
//    public ApiResponse<LogDto> uploadFromText(String text) {
//        LogEntity logEntity = logValidator.textToLogEntity(new LogEntity(), text);
//        try {
////            logRepository.save(logEntity);
//            return ApiResponse.<LogDto>builder()
//                    .code(0)
//                    .message("Successfully saved log")
//                    .success(true)
//                    .log(
//                            logMapper.toDto(
//                                    logRepository.
//                                            save(
//                                                    logEntity
//                                            )
//                            )
//                    )
//                    .build();
//        } catch (Exception e) {
//            throw new DatabaseException("Database exception while saving long from text");
//        }
//    }    //

    @Override
    public ApiResponse<LogDto> uploadFromText(String text) {

        LogEntity logEntity = new LogEntity();

        Map<String, Object> keyValueLog = logParser.parseLog(text);

        logEntity = logParser.toLogEntity(keyValueLog, logEntity);

        try {

            return ApiResponse.<LogDto>builder()
                    .code(0)
                    .message("Log uploaded successfully")
                    .success(true)
                    .log(
                            logMapper.toDto(
                                    logRepository.save(
                                            logEntity
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database error while saving log" + e.getMessage());
        }
    }

    @Override
    public ApiResponse<Page<LogDto>> getAllLog(PageRequestDto dto) {

        if (dto.getPage() == 0) dto.setPage(1);
        if (dto.getSize() == 0) dto.setSize(10);

        try {
            Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());

            Page<LogEntity> all = logRepository.findAll(pageable);
            Page<LogDto> response = all.map(logMapper::toDto);

            return ApiResponse.<Page<LogDto>>builder()
                    .code(0)
                    .message("Successfully retrieved all logs")
                    .success(true)
                    .log(response)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database exception while getting log");
        }

    }

    @Override
    public ApiResponse<Page<LogDto>> getWithTime(PageRequestDto dto, LocalDate start, LocalDate end) {

        if (dto.getPage() == 0) dto.setPage(1);
        if (dto.getSize() == 0) dto.setSize(10);

        try {
            Page<LogEntity> allByDateBetween = logRepository.findAllByDateBetween(start, end, PageRequest.of(dto.getPage(), dto.getSize()));
            Page<LogDto> response = allByDateBetween.map(logMapper::toDto);
            return ApiResponse.<Page<LogDto>>builder()
                    .code(0)
                    .message("Successfully retrieved all logs")
                    .success(true)
                    .log(response)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database exception while getting log");
        }
    }

    @Override
    public ApiResponse<Map<Date, Integer>> getDailyLog(LocalDate start, LocalDate end) {
        try {
            Timestamp startTime = Timestamp.valueOf(start.atStartOfDay());
            Timestamp endTime = Timestamp.valueOf(end.atStartOfDay().plusHours(23).minusMinutes(59).plusSeconds(59));
            List<Object[]> dailylog = this.logRepository.countLogsByDateRange(startTime, endTime);

            Map<java.sql.Date, Integer> map = new HashMap<>();
            for (Object[] row : dailylog) {
                map.put((Date) row[0], ((Number) row[1]).intValue());
            }
            return ApiResponse.<Map<Date, Integer>>builder()
                    .code(0)
                    .message("Successfully retrieved all logs")
                    .success(true)
                    .log(map)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database exception while getting daily log");
        }
    }

    @Override
    public ApiResponse<Map<String, Integer>> getServiceCount(LocalDate startDate, LocalDate endDate) {
        Timestamp startTime = Timestamp.valueOf(startDate.atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(endDate.atStartOfDay().plusHours(23).minusMinutes(59).plusSeconds(59));
        try {
            List<Object[]> objects = this.logRepository.servicesCount(startTime, endTime);
            Map<String, Integer> map = new HashMap<>();
            for (Object[] row : objects) {
                map.put((String) row[0], ((Number) row[1]).intValue());
            }

            return ApiResponse.<Map<String, Integer>>builder()
                    .code(0)
                    .message("Successfully retrieved all logs")
                    .success(true)
                    .log(map)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database exception while getting daily log");
        }
    }

    @Override
    public ApiResponse<Map<String, Integer>> getDstCountry(LocalDate start, LocalDate end) {
        try {
            Timestamp startTime = Timestamp.valueOf(start.atStartOfDay());
            Timestamp endTime = Timestamp.valueOf(end.atStartOfDay().plusHours(23).minusMinutes(59).plusSeconds(59));
            List<Object[]> logsByCountry = logRepository.getLogsByCountry(startTime, endTime);
            Map<String, Integer> map = new HashMap<>();
            for (Object[] row : logsByCountry) {
                map.put((String) row[0], ((Number) row[1]).intValue());
            }
            return ApiResponse.<Map<String, Integer>>builder()
                    .code(0)
                    .message("Successfully retrieved all logs")
                    .success(true)
                    .log(map)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database exception while getting dst country");
        }
    }

    @Override
    public ApiResponse<Page<LogDto>> getSortedListIp(PageRequestDto dto, String ip, LocalDate startTime, LocalDate endTime) {
        try {
            Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());
            Page<LogEntity> allByDateBetweenAndSrcIPContainingIgnoreCase = logRepository.findAllByDateBetweenAndSrcIPContainingIgnoreCase(startTime, endTime, ip, pageable);
            Page<LogDto> response = allByDateBetweenAndSrcIPContainingIgnoreCase.map(logMapper::toDto);
            return ApiResponse.<Page<LogDto>>builder()
                    .code(0)
                    .message("Successfully retrieved all logs")
                    .success(true)
                    .log(response)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database exception while getting sorted list ip");
        }

    }


}
