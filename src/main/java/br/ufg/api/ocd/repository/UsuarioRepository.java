package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    public Usuario findByCpf(String cpf);
    public Usuario findByNome(String nome);
    public List<Usuario> findAllByStatus(String status);
    public List<Usuario> findAllByTipoUsuario(String tipo);
    public List<Usuario> findAllByTipoUsuarioAndStatus(String tipo, String status);
    Page<Usuario> findBy(TextCriteria criteria, Pageable page);
}
