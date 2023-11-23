package ru.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bank.entity.CurrencyEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {

    @Query(value = "SELECT * " +
                   "FROM CURRENCIES t " +
                   "WHERE t.date_at BETWEEN :dateFrom AND :dateTo AND t.vch_code = :vchCode", nativeQuery = true)
    Optional<List<CurrencyEntity>> findByDateRangeAndVchCode(@Param("dateFrom") LocalDate dateFrom,
                                                             @Param("dateTo") LocalDate dateTo,
                                                             @Param("vchCode") String vchCode);

}
