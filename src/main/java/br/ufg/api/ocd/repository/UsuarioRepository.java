package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Usuario;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    public Usuario findByCpfAndSenha(String cpf, String senha);
    public Usuario findByCpf(String cpf);
}
