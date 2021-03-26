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
public class PicketFormDTO implements Serializable {
    private static final long serialVersionUID = 5854418911597744473L;

    @NotBlank(message = "The name could not be null or empty.")
    private String name;
    @NotBlank(message = "The propertySectorId could not be null or empty.")
    private String propertySectorId;
    @NotNull(message = "The picketArea could not be null.")
    private Double picketArea;
    private String description;
}