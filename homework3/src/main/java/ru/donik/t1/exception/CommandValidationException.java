package ru.donik.t1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ошибка обработки команды")
public class CommandValidationException extends RuntimeException {
    public CommandValidationException(String message) {
        super(message);
    }
}