package dev.wakandaacademy.conteudo.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.DataHelper;
import dev.wakandaacademy.conteudo.application.api.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoListResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.ConteudoResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoUsuarioListResponse;
import dev.wakandaacademy.conteudo.application.api.EditaConteudoRequest;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.handler.APIException;
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
	void salvaConteudo_ComDadosValidos_RetornaIdConteudo() {
		Usuario usuario = DataHelper.createUsuario();
		ConteudoRequest request = DataHelper.conteudoRequest();
		Conteudo conteudo = DataHelper.createConteudo();
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.salvaConteudo(any())).thenReturn(conteudo);

		ConteudoIdResponse response = conteudoApplicationService.criaConteudo(usuario.getEmail(), request);

		assertThat(response).isNotNull();
		assertThat(ConteudoIdResponse.class).isEqualTo(response.getClass());
	}

	@Test
	void buscaConteudo_ComIdValido_RetornaConteudo() {
		Conteudo conteudo = DataHelper.createConteudo();
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));

		ConteudoResponse response = conteudoApplicationService.buscaConteudoPorId(conteudo.getIdConteudo());

		assertThat(response).isNotNull();
		assertThat(ConteudoResponse.class).isEqualTo(response.getClass());
	}

	@Test
	void buscaConteudo_ComIdInexistente_RetornaErro() {
		Conteudo conteudo = DataHelper.createConteudo();
//		when(conteudoRepository.buscaConteudoPorId(any())).thenThrow(APIException.class);
//
//		assertThatThrownBy(() -> conteudoApplicationService.buscaConteudoPorId(UUID.randomUUID())).isInstanceOf(APIException.class);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));

		APIException ex = assertThrows(APIException.class,
				() -> conteudoApplicationService.buscaConteudoPorId(UUID.randomUUID()));
		assertEquals("Conteudo não encontrado.", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatusException());
	}
	
	@Test
	void buscaConteudo_RetornaListConteudo() {
		List<Conteudo> conteudosList = DataHelper.createListConteudo();
		
		when(conteudoRepository.buscaConteudos()).thenReturn(conteudosList);
		List<ConteudoListResponse> response = conteudoApplicationService.buscaConteudos();
		
		assertThat(response).isNotEmpty();
		assertThat(response).hasSize(4);
	}
	
	@Test
	void buscaConteudo_ComIdUsuarioValido_RetornaListConteudo() {
		Usuario usuario = DataHelper.createUsuario();
		List<Conteudo> conteudosList = DataHelper.createListConteudo();
		
		when(conteudoRepository.buscaConteudosDoUsuario(any())).thenReturn(conteudosList);
		List<ConteudoUsuarioListResponse> response = conteudoApplicationService.buscaConteudosDoUsuario(usuario.getIdUsuario());
		
		assertThat(response).isNotEmpty();
		assertThat(response).hasSize(4);
		assertThat(response.get(0).getIdUsuario()).isEqualTo(usuario.getIdUsuario());
	}
	
	@Test
	void buscaConteudo_ComIdUsuarioValido_RetornaListVazia() {
		Usuario usuario = DataHelper.createUsuario();
		
		when(conteudoRepository.buscaConteudosDoUsuario(any())).thenReturn(Collections.emptyList());
		List<ConteudoUsuarioListResponse> response = conteudoApplicationService.buscaConteudosDoUsuario(usuario.getIdUsuario());
		
		assertThat(response).isEmpty();
	}
	
	@Test
	void deletaConteudo_ComIdValido() {
		Usuario usuario = mock(Usuario.class);
		Conteudo conteudo = mock(Conteudo.class);
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		
		conteudoApplicationService.deletaConteudoPorId(usuario.getEmail(), conteudo.getIdConteudo());
		verify(conteudo).pertenceUsuario(usuario);
		verify(conteudoRepository, times(1)).deletaConteudo(conteudo);
	}
	
	@Test
	void editaConteudo_ComIdValido() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = mock(Conteudo.class);
		EditaConteudoRequest request = DataHelper.editaConteudoRequest();
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		
		conteudoApplicationService.editaConteudoPorId(usuario.getEmail(), conteudo.getIdConteudo(), request);
		verify(conteudo).pertenceUsuario(usuario);
		verify(conteudoRepository, times(1)).editaConteudoPorId(conteudo, request);
	}
}
