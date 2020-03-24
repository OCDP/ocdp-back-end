package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.RegiaoBoca;
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
    private NextSequenceService nextSequenceService;

    public LocalAtendimento salvar(LocalAtendimento localAtendimento) {
        localAtendimento.setId(nextSequenceService.getNextSequence("localAtendimento"));
        return repository.save(localAtendimento);
    }

    public LocalAtendimento atualizar(LocalAtendimento localAtendimento) throws Exception {
        LocalAtendimento localAtendimentoDB = repository.findById(localAtendimento.getId()).get();
        if(localAtendimentoDB == null){
            throw new Exception("LocalAtendimento não existe com esse id: "+localAtendimento.getId());
        }
        return repository.save(localAtendimento);
    }

    public List<LocalAtendimento> findAll() {
        return repository.findAll();
    }

    public LocalAtendimento findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public LocalAtendimento findByNome(@NonNull String nome) {
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
