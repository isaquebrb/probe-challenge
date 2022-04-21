package br.com.elo7.sonda.candidato.domain.exception;

public class CommandException extends RuntimeException {

    public CommandException(String msg) {
        super(msg);
    }
}
