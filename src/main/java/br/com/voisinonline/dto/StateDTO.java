package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateDTO implements Serializable {
    private static final long serialVersionUID = -8411272312410594144L;

    private Long id;
    private String name;
    private String uf;
}