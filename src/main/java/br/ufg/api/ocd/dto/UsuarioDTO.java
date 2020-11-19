package br.ufg.api.ocd.dto;

import br.ufg.api.ocd.enums.NivelAtencao;
import br.ufg.api.ocd.enums.StatusUsuario;
import br.ufg.api.ocd.enums.TipoUsuario;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioDTO {
    private Long id;
    @NotEmpty(message = "Forneça o cpf do usuário")
    private String cpf;
    @NotEmpty(message = "Forneça o nome do usuário")
    private String nome;
    @NotNull(message = "Forneça o status do usuário")
    private StatusUsuario status;
    @NotEmpty(message = "Forneça o email do usuário")
    private String email;
    @NotEmpty(message = "Forneça o telefone do usuário")
    private String telefone;
    @NotNull(message = "Forneça o nivel de atenção do usuário")
    private NivelAtencao nivelAtencao;
    @NotNull(message = "Forneça do tipo de usuário")
    private TipoUsuario tipoUsuario;
}
