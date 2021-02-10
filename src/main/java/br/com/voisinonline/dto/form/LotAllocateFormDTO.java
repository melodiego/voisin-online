package br.com.voisinonline.dto.form;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotAllocateFormDTO implements Serializable {
    private static final long serialVersionUID = 3490423867618972512L;

    @NotBlank(message = "The lot id could not be null or empty.")
    private String lotId;
    @NotBlank(message = "The sector id could not be null or empty.")
    private String sectorId;
    @NotBlank(message = "The picket id could not be null or empty.")
    private String picketId;
}