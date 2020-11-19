package br.ufg.api.ocd.dto;

import br.ufg.api.ocd.enums.TipoAtendimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoDTO {
    private Long id;

    @NotNull(message = "Forneça a data do antendimento")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAtendimento;

    @NotNull(message = "Forneça o usuário do antendimento")
    private Long usuarioId;

    @NotNull(message = "Forneça o paciente do antendimento")
    private Long pacienteId;

    @NotNull(message = "Forneça o tipo de antendimento")
    private TipoAtendimento tipoAtendimento;

    @NotNull(message = "Forneça o local de antendimento do atendiemnto")
    private Long localAtendimentoId;

    private Long localEncaminhadoId;
}
