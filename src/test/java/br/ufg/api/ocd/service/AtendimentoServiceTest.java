package br.ufg.api.ocd.service;

import br.ufg.api.ocd.exception.AtendimentoNaoEncontradoException;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.repository.AtendimentoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AtendimentoServiceTest {

    @InjectMocks
    private AtendimentoService service;

    @Mock
    private AtendimentoRepository repository;

    @Test
    public void valid_success_requist() {
        byte[] foto = null;
        String atendimentoId = "1";
        Atendimento atendimento = Atendimento.builder().id(atendimentoId).build();
        Optional<Atendimento> opt = Optional.of(atendimento);

        Mockito.when(repository.findById(atendimentoId)).thenReturn(opt);

        service.uploadFoto(foto, atendimentoId);

        Mockito.verify(repository).save(atendimento);
    }

    @Test(expected = AtendimentoNaoEncontradoException.class)
    public void validar_atendimento_nao_encontrado() {
        byte[] foto = null;
        String atendimentoId = "1";

        Mockito.when(repository.findById(atendimentoId)).thenReturn(Optional.ofNullable(null));

        service.uploadFoto(foto, atendimentoId);
    }

}