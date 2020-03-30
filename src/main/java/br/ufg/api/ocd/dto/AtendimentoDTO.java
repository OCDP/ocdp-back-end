package br.ufg.api.ocd.dto;

import br.ufg.api.ocd.enums.TipoAtendimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AtendimentoDTO {
    private String id;

    @NotNull(message = "Forneça a data do antendimento")
    @JsonFormat(pattern="dd-MM-yyyyy HH:mm:ss")
    private Date dataAtendimento;

    @NotNull(message = "Forneça o usuário do antendimento")
    private UsuarioDTO usuario;

    @NotNull(message = "Forneça o paciente do antendimento")
    private PacienteDTO paciente;

    @NotNull(message = "Forneça o tipo de antendimento")
    private TipoAtendimento tipoAtendimento;

    @NotNull(message = "Forneça o local de antendimento do atendiemnto")
    private LocalAtendimentoDTO localAtendimento;

    private LocalAtendimentoDTO localEncaminhado;
}
