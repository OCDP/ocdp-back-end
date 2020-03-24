package br.ufg.api.ocd.service;

import br.ufg.api.ocd.enums.StatusUsuario;
import br.ufg.api.ocd.model.Usuario;
import br.ufg.api.ocd.repository.UsuarioRepository;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  NextSequenceService nextSequenceService;

    public Usuario findByCpf(@NonNull String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Usuario> findByStatus(@NonNull String status) {
        return repository.findAllByStatus(status.toUpperCase());
    }

    public List<Usuario> findByTipoStatus(@NonNull String tipo, @NonNull String status) {
        return repository.findAllByTipoUsuarioAndStatus(tipo.toUpperCase(), status.toUpperCase());
    }

    public List<Usuario> findByTipo(@NonNull String tipo) {
        return repository.findAllByTipoUsuario(tipo.toUpperCase());
    }


    public Usuario salvar(Usuario usuario) {
        usuario.setId(nextSequenceService.getNextSequence("usuario"));
        return repository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) throws Exception {
        Usuario usuarioDB = repository.findById(usuario.getId()).get();
        if(usuarioDB == null){
            throw new Exception("Usuario não existe com esse id: "+usuario.getId());
        }
        return repository.save(usuario);
    }

    public Usuario findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public Usuario findByNome(@NonNull String nome) {
        return repository.findByNome(nome);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
