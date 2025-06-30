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
import uz.jahonservice.dashboard.service.validation.LogValidator;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final LogValidator logValidator;
    private final LogMapper logMapper;

    @Override
    public ApiResponse<Void> saveLogFromFile(MultipartFile file) {
        return null;
    }

    @Override
    public ApiResponse<LogDto> uploadFromText(String text) {
        LogEntity logEntity = logValidator.textToLogEntity(new LogEntity(), text);
        try {
//            logRepository.save(logEntity);
            return ApiResponse.<LogDto>builder()
                    .code(0)
                    .message("Successfully saved log")
                    .success(true)
                    .log(
                            logMapper.toDto(
                                    logRepository.
                                            save(
                                                    logEntity
                                            )
                            )
                    )
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Database exception while saving long from text");
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
        }catch (Exception e){
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
        }catch (Exception e){
            throw new DatabaseException("Database exception while getting sorted list ip");
        }

    }


}
