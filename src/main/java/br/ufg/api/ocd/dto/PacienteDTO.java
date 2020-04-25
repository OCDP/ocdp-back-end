package br.ufg.api.ocd.dto;

import br.ufg.api.ocd.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PacienteDTO {
    private String id;

    @NotEmpty(message = "Forneça o nome do paciente")
    private String nome;

    @NotEmpty(message = "Forneça o cpf do paciente")
    private String cpf;

    @NotNull(message = "Forneça a data de nascimento do paciente")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataNascimento;

    @NotNull(message = "Forneça o sexo do paciente")
    private Sexo sexo;

    @NotEmpty(message = "Forneça o email do paciente")
    private String email;

    @NotEmpty(message = "Forneça o telefone celular do paciente")
    private String telefoneCelular;

    @NotEmpty(message = "Forneça o nome da mãe do paciente")
    private String nomeDaMae;

    @NotEmpty(message = "Forneça o telefone do responsavel do paciente")
    private String telefoneResponsavel;

    @NotNull(message = "Forneça o bairro do paciente")
    private BairroDTO bairro;

    @NotNull(message = "Forneça o endereço completo do paciente")
    private String enderecoCompleto;
}
