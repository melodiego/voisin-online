package br.com.voisinonline.dto.form;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateFormDTO implements Serializable {
    private static final long serialVersionUID = -1631234441386775439L;

    private String name;
    private String uf;
    private List<CityFormDTO> cities;
}