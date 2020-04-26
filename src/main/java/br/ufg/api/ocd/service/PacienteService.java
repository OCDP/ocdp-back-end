package br.ufg.api.ocd.service;

import br.ufg.api.ocd.dto.PacienteDTO;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.repository.PacienteRepository;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private GenericService genericService;

    @Autowired
    private ModelMapper modelMapper;

    public Paciente salvarPacienteDTO(PacienteDTO pacienteDTO){
        Paciente paciente = repository.findByCpf(pacienteDTO.getCpf());
        if(paciente == null){
            paciente = salvar(modelMapper.map(pacienteDTO, Paciente.class));
        }
        return paciente;
    }

    public List<Paciente> geByNome(@NonNull String nome) {
        return repository.findByQuery(nome);
    }

    public List<Paciente> findAll() {
        return (List<Paciente>) repository.findAll();
    }

    public Paciente salvar(Paciente paciente) {
        genericService.init(Paciente.class);
        return repository.save(paciente);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
