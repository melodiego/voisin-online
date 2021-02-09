package br.com.voisinonline.dto;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDTO implements Serializable {
    private static final long serialVersionUID = 490457834076654747L;

    private String codeIBGE;
    private String name;
}