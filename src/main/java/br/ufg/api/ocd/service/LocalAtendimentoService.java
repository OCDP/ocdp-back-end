package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Lesao;
import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.repository.LocalAtendimentoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalAtendimentoService {

    @Autowired
    private LocalAtendimentoRepository repository;

    @Autowired
    private GenericService genericService;

    public LocalAtendimento salvar(LocalAtendimento localAtendimento) {
        genericService.init(LocalAtendimento.class);
        return repository.save(localAtendimento);
    }

    public LocalAtendimento atualizar(LocalAtendimento localAtendimento) throws Exception {
        LocalAtendimento localAtendimentoDB = repository.findById(localAtendimento.getId()).get();
        if(localAtendimentoDB == null){
            throw new Exception("LocalAtendimento não existe com esse id: "+localAtendimento.getId());
        }
        return repository.save(localAtendimento);
    }

    public LocalAtendimento findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public List<LocalAtendimento> getAll() {

        return (List<LocalAtendimento>) repository.findAll();
    }

    public List<LocalAtendimento> getByNome(@NonNull String nome) {

        return repository.findByNome(nome);
    }

    public List<LocalAtendimento> getByTipoLocalAtendimento(@NonNull String nome) {

        return repository.findByAndTipoLocalAtendimento_Nome(nome);
    }

    public List<LocalAtendimento> getByDistrito(@NonNull String nome) {

        return repository.findByDistrito_Nome(nome);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
