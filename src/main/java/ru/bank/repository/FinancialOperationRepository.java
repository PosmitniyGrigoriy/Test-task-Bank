package ru.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bank.entity.FinancialOperationEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FinancialOperationRepository extends JpaRepository<FinancialOperationEntity, Integer> {

    @Query(value = "SELECT * " +
                   "FROM RUB_FINANCIAL_OPERATIONS t " +
                   "WHERE t.date_at BETWEEN :dateFrom AND :dateTo", nativeQuery = true)
    Optional<List<FinancialOperationEntity>> getFinancialOperationsForPeriod(@Param("dateFrom") LocalDate dateFrom,
                                                                             @Param("dateTo") LocalDate dateTo);

}
