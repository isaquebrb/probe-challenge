package br.com.elo7.sonda.candidato.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class InputDTO {

    private int width;
    private int height;
    private List<ProbeDTO> probes;
}
