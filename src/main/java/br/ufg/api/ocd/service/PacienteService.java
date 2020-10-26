package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.repository.PacienteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private  NextSequenceService nextSequenceService;

    public Paciente findByCpf(@NonNull String cpf) {
        return repository.findByCpf(cpf);
    }

    public Paciente salvar(Paciente paciente) {
        paciente.setId(nextSequenceService.getNextSequence("paciente"));
        return repository.save(paciente);
    }

    public Paciente atualizar(Paciente paciente) throws Exception {
        Paciente pacienteDB = repository.findById(paciente.getId()).get();
        if(pacienteDB == null){
            throw new Exception("Paciente não existe com esse id: "+paciente.getId());
        }
        return repository.save(paciente);
    }

    public Paciente findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public List<Paciente> geByNome(@NonNull String nome) {
        return repository.findByQuery(nome);
    }

    public List<Paciente> findAll() {
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
