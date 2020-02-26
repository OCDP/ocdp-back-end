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

    public List<Paciente> geByNome(@NonNull String nome) {
        return repository.findByNome(nome);
    }

    public List<Paciente> findAll() {
        return repository.findAll();
    }

    public Paciente salvar(Paciente paciente) {
        paciente.setId(nextSequenceService.getNextSequence("paciente"));
        return repository.save(paciente);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
