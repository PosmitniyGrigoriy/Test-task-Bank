package ru.bank.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.bank.service.CurrencyService;

@Slf4j
@AllArgsConstructor
@Service
public class SchedulerService {

    private final CurrencyService currencyService;

    @Scheduled(cron = "${spring.task.scheduler.upload-cron}")
    public void uploadCurrencies() {
        log.info("The currency upload scheduler has started");
        currencyService.upload();
        log.info("The currency upload scheduler has been turned off");
    }

}
