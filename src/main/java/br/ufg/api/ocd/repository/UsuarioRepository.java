package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    public Usuario findByCpfAndSenha(String cpf, String senha);
    public Usuario findByCpf(String cpf);
}
