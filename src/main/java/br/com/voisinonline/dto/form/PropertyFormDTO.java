package br.com.voisinonline.dto.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyFormDTO implements Serializable {
    private static final long serialVersionUID = 4699268913491499609L;

    @NotBlank(message = "The name could not be null or empty.")
    private String name;
    @NotNull(message = "The totalPastureArea could not be null.")
    private Long totalPastureArea;
    private String description;
}