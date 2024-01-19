package dev.wakandaacademy.conteudo.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.wakandaacademy.DataHelper;
import dev.wakandaacademy.conteudo.application.api.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;

@ExtendWith(MockitoExtension.class)
class ConteudoApplicationServiceTest {

	@InjectMocks
	private ConteudoApplicationService conteudoApplicationService;
	@Mock
	private UsuarioRepository usuarioRepository;
	@Mock
	private ConteudoRepository conteudoRepository;

	@Test
	void salvaConteudo_ComDadosValidos() {
		Usuario usuario = DataHelper.createUsuario();
		ConteudoRequest request = DataHelper.conteudoRequest();
		Conteudo conteudo = DataHelper.createConteudo();
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.salvaConteudo(any())).thenReturn(conteudo);

		ConteudoIdResponse response = conteudoApplicationService.criaConteudo(usuario.getEmail(), request);

		assertThat(response).isNotNull();
		assertThat(ConteudoIdResponse.class).isEqualTo(response.getClass());

	}

}
