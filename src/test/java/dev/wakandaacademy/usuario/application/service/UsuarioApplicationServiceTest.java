package dev.wakandaacademy.usuario.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.wakandaacademy.DataHelper;
import dev.wakandaacademy.credencial.application.service.CreadencialService;
import dev.wakandaacademy.usuario.application.api.UsuarioCriadoResponse;
import dev.wakandaacademy.usuario.application.api.UsuarioIdResponse;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;

@ExtendWith(MockitoExtension.class)
class UsuarioApplicationServiceTest {

	@InjectMocks
	private UsuarioApplicationService UsuarioApplicationService;
	@Mock
	private CreadencialService creadencialService;
	@Mock
	private UsuarioRepository usuarioRepository;

	Usuario usuario = DataHelper.createUsuario();
	UUID idUsuario = usuario.getIdUsuario();
	String emailUsuario = usuario.getEmail();

	@Test
	void salvaUsuario() {
		UsuarioNovoRequest request = DataHelper.createUsuarioRequest();
		Usuario usuarioNovo = DataHelper.createUsuario();

		when(usuarioRepository.salvaUsuario(usuarioNovo)).thenReturn(usuario);
		UsuarioIdResponse response = UsuarioApplicationService.criaNovoUsuario(request);
		
		verify(creadencialService, times(1)).salvaCredencial(request);

		assertThat(response).isNotNull();
		assertThat(UsuarioIdResponse.class).isEqualTo(response);
	}
	
	@Test
	void buscaUsuario_IdValido_RetornaUsuario() {
		Usuario usuario = DataHelper.createUsuario();
		UUID idUsuario = usuario.getIdUsuario();
		when(usuarioRepository.buscaUsuarioPorId(any())).thenReturn(Optional.of(usuario));
		UsuarioCriadoResponse response = UsuarioApplicationService.buscaUsuarioPorId(idUsuario);
		
		assertThat(response).isNotNull();
		assertThat(UsuarioCriadoResponse.class).isEqualTo(response.getClass());
	}

}
