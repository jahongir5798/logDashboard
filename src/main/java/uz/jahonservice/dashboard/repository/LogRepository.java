package uz.jahonservice.dashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.jahonservice.dashboard.entity.LogEntity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, UUID> {

    Page<LogEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

//    @Query(
//            value = """
//                    select date, count(date)
//                    from log group by date
//                                        """,
//            nativeQuery = true
//    )
//    List<Object[]> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query(value = """
            SELECT date AS log_date, COUNT(*) AS log_count
            FROM log
            WHERE date BETWEEN :startDate AND :endDate
            GROUP BY date
            ORDER BY log_date
            """, nativeQuery = true)
    List<Object[]> countLogsByDateRange(
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate);


    @Query(
            value = """
                    select service, count(*)
                    from log
                    where date between :startDate and :endDate
                    group by service
                    """, nativeQuery = true
    )
    List<Object[]> servicesCount(
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate
    );

    @Query(
            value = """
                    select log.dst_country, count(*)
                    from log
                    where date between :startDate and :endDate
                    group by dst_country
                    """,
            nativeQuery = true
    )
    List<Object[]> getLogsByCountry(
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate
    );

    Page<LogEntity> findAllByDateBetweenAndSrcIPContainingIgnoreCase(LocalDate start, LocalDate end, String srcIP, Pageable pageable);
}
