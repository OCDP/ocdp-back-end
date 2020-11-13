package br.ufg.api.ocd.service;

import br.ufg.api.ocd.exception.LogradouroNaoEncontradoException;
import br.ufg.api.ocd.model.Bairro;
import br.ufg.api.ocd.model.Logradouro;
import br.ufg.api.ocd.repository.BairroRepository;
import br.ufg.api.ocd.repository.LogradouroRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LogradouroService {

    @Autowired
    private LogradouroRepository repository;




    public Logradouro salvar(Logradouro logradouro) {

        return repository.save(logradouro);
    }

    public Logradouro atualizar(Logradouro logradouro) throws Exception {
        return Optional.of(logradouro)
                .flatMap(l -> repository.findById(l.getId()))
                .map(l -> repository.save(l))
                .orElseThrow(() -> new Exception("Logradouro não existe com esse id: " + logradouro.getId()));
    }

    public Logradouro findByCep(String cep) throws Exception {
        return Optional.of(cep)
                .map(c -> repository.findFirstByCep(c))
                .orElseThrow(() -> new LogradouroNaoEncontradoException("Logradouro não encontrado!"));
    }
}
