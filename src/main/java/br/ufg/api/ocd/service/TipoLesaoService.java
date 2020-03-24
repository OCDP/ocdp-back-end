package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.TipoLesao;
import br.ufg.api.ocd.repository.TipoLesaoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TipoLesaoService {

    @Autowired
    private TipoLesaoRepository repository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public TipoLesao salvar(TipoLesao tipoLesao) {
        tipoLesao.setId(nextSequenceService.getNextSequence("tipoLesao"));
        return repository.save(tipoLesao);
    }

    public TipoLesao atualizar(TipoLesao tipoLesao) throws Exception {
        TipoLesao tipoLesaoDB = repository.findById(tipoLesao.getId()).get();
        if(tipoLesaoDB == null){
            throw new Exception("TipoLesao não existe com esse id: "+tipoLesao.getId());
        }
        return repository.save(tipoLesao);
    }

    public TipoLesao findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public TipoLesao findByNome(@NonNull String nome) {
        return repository.findByNome(nome);
    }

    public List<TipoLesao> getAll() {
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
