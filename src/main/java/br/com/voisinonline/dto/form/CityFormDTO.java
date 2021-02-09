package br.com.voisinonline.dto.form;

import lombok.*;

import java.io.Serializable;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityFormDTO implements Serializable {
    private static final long serialVersionUID = -291148004853824432L;

    private String codeIBGE;
    private String name;
}