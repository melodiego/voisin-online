package br.com.voisinonline.build;

import br.com.voisinonline.dto.StateDTO;
import br.com.voisinonline.model.State;

import java.time.LocalDateTime;

public interface BuildState {

    static State buildState() {
        return State.builder()
                .id(1L)
                .name("Alagoas")
                .uf("AL")
                .createdAt(LocalDateTime.now())
                .build();
    }

    static StateDTO buildStateDTO() {
        return StateDTO.builder()
                .id(1L)
                .name("Alagoas")
                .uf("AL")
                .build();
    }
}