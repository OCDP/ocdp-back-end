package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nome;
    private String cpf;
    private LocalDateTime dataNascimento;
    private Sexo sexo;
    private String email;
    private String telefoneCelular;
    private String nomeDaMae;
    private String telefoneResponsavel;
    @OneToOne
    private Endereco endereco;
    @OneToOne
    private LogAtendimentos log;

}



