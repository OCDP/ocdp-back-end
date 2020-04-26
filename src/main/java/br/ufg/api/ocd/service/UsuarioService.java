package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Usuario;
import br.ufg.api.ocd.repository.UsuarioRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private GenericService genericService;

    public Usuario findByCpf(@NonNull String cpf) {
        return repository.findByCpf(cpf);
    }

    public Usuario salvar(Usuario usuario) {
        genericService.init(Usuario.class);
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

    public void deleteAll(){
        repository.deleteAll();
    }

}
