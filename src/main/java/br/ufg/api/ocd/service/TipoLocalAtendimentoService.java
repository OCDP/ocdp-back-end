package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.TipoLocalAtendimento;
import br.ufg.api.ocd.repository.TipoLocalAtendimentoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoLocalAtendimentoService {

    @Autowired
    private TipoLocalAtendimentoRepository repository;

    public List<TipoLocalAtendimento> getAll() {
        return repository.findAll();
    }

    public TipoLocalAtendimento salvar(TipoLocalAtendimento tipo) {

        return repository.save(tipo);
    }

    public TipoLocalAtendimento atualizar(TipoLocalAtendimento tipo) throws Exception {
        TipoLocalAtendimento tipoDB = repository.findById(tipo.getId()).get();
        if(tipoDB == null){
            throw new Exception("TipoLocalAtendimento não existe com esse id: "+tipo.getId());
        }
        return repository.save(tipo);
    }

    public TipoLocalAtendimento findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
