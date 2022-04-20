package br.com.elo7.sonda.candidato.api.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StandardError {

    public StandardError(Integer status, List<String> errors, String message) {
        this.status = status;
        this.errors = errors;
        this.message = message;
        this.date = LocalDateTime.now();
    }

    private Integer status;
    private List<String> errors;
    private String message;

    @Setter(value = AccessLevel.NONE)
    private LocalDateTime date;
}
