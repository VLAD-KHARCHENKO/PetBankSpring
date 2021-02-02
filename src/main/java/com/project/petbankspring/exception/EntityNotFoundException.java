package com.project.petbankspring.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7595625433016740607L;

    public EntityNotFoundException(String arg0) {
        super(arg0);
        log.info(" EntityNotFoundException");
    }

}
