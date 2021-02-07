package io.trajano.reactivespring.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Recurso não encontrado")
public class ResourceNotFoundException extends RuntimeException {
    
}
