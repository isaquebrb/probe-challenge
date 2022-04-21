package br.com.elo7.sonda.candidato.api.error;

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
        this.message = message;
        this.errors = errors;
        this.date = LocalDateTime.now();
    }

    private Integer status;
    private String message;
    private List<String> errors;

    @Setter(value = AccessLevel.NONE)
    private LocalDateTime date;
}
