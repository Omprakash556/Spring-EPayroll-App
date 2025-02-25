package com.bridgelab.EmployeePayrollApp.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggerService {
    public void info(String message, Object... args) {
        log.info(message, args);
    }

    public void error(String message, Object... args) {
        log.error(message, args);
    }

    public void debug(String message, Object... args) {
        log.debug(message, args);
    }
}
