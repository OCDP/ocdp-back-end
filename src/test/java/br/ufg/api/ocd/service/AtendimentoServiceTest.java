package br.ufg.api.ocd.service;

import br.ufg.api.ocd.DefaultApplicationTest;
import br.ufg.api.ocd.exception.AtendimentoNaoEncontradoException;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.repository.AtendimentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class AtendimentoServiceTest extends DefaultApplicationTest {

    @InjectMocks
    private AtendimentoService service;

    @Mock
    private AtendimentoRepository repository;

    @Test
    public void valid_success_requist() {
        byte[] foto = null;
        Long atendimentoId = 1L;
        Atendimento atendimento = Atendimento.builder().id(atendimentoId).build();
        Optional<Atendimento> opt = Optional.of(atendimento);

        Mockito.when(repository.findById(atendimentoId)).thenReturn(opt);

        service.uploadFoto(foto, atendimentoId);

        Mockito.verify(repository).save(atendimento);
    }

    @Test()
    public void validar_atendimento_nao_encontrado() {
        byte[] foto = null;
        Long atendimentoId = 1L;

        Mockito.when(repository.findById(atendimentoId)).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(AtendimentoNaoEncontradoException.class, () -> service.uploadFoto(foto, atendimentoId));
    }

}