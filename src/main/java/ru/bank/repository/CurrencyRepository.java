package ru.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bank.entity.CurrencyEntity;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {

    Optional<CurrencyEntity> findByVchCode(String vchCode);

}
