package dev.wakandaacademy.postagem.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.EditaPostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemResponse;
import dev.wakandaacademy.postagem.application.api.PostagemUsuarioListResponse;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;

@ExtendWith(MockitoExtension.class)
class PostagemApplicationServiceTest {

	@InjectMocks
	private PostagemApplicationService postagemApplicationService;
	@Mock
	private PostagemRepository postagemRepository;
	@Mock
	private UsuarioRepository usuarioRepository;
	@Mock
	private ConteudoRepository conteudoRepository;

	@Test
	void salvaPostagem_ComDadosValidos_RetornaIdPostagemResponse() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = DataHelper.createConteudo();
		PostagemRequest request = DataHelper.postagemRequest();
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.salvaPostagem(any())).thenReturn(new Postagem(usuario, conteudo.getIdConteudo(), request));

		PostagemIdResponse response = postagemApplicationService.criaPostagem(usuario.getEmail(), conteudo.getIdConteudo(), request);

		assertThat(response).isNotNull();
		assertThat(PostagemIdResponse.class).isEqualTo(response.getClass());
	}

	@Test
	void buscaPostagem_ComIdValido_RetornaPostagem() {
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagem = mock(Postagem.class);
		UUID idConteudo = conteudo.getIdConteudo();
		UUID idPostagem = postagem.getIdPostagem();

		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagem));

		PostagemResponse response = postagemApplicationService.buscaPostagemPorId(idConteudo, idPostagem);

		assertThat(response).isNotNull();
		assertThat(PostagemResponse.class).isEqualTo(response.getClass());
	}
	
	@Test
	void buscaPostagem_ComIdInexistente_RetornaErro() {
		Conteudo conteudo = DataHelper.createConteudo();
		UUID idConteudo = conteudo.getIdConteudo();
		UUID idPostagem = UUID.fromString("5ade1e1d-dce1-4611-9226-c9480048b78e");
		
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.empty());

		APIException ex = assertThrows(APIException.class,
				() -> postagemApplicationService.buscaPostagemPorId(idConteudo, idPostagem));
		
		verify(conteudoRepository, times(1)).buscaConteudoPorId(any());
		verify(postagemRepository, times(1)).buscaPostagemPorId(any());
		
		assertEquals("Post não encontrado!", ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatusException());
	}

	@Test
	void buscaPostagem_comIdConteudoValido_RetornaListPostagem() {
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagem = DataHelper.createPostagem();
		List<Postagem> listPostagem = DataHelper.createListPostagem();
		
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagens(any())).thenReturn(listPostagem);

		List<PostagemUsuarioListResponse> response = postagemApplicationService.buscaPostagens(conteudo.getIdConteudo());
		
		assertThat(response).isNotEmpty();
		assertThat(response).hasSize(3);
		assertThat(PostagemUsuarioListResponse.class).isEqualTo(response.getClass());
	}
	
	@Test
	void editaPostagem_comDadosValidos() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagem = mock(Postagem.class);
		EditaPostagemRequest request = DataHelper.editaPostagemRequest();
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagem));

		postagemApplicationService.alteraPostPorId(usuario.getEmail(), conteudo.getIdConteudo(), postagem.getIdPostagem(), request);
		
		verify(postagem).pertenceUsuario(usuario);
		verify(postagem).alteraPostagem(request);
		verify(postagemRepository, times(1)).salvaPostagem(postagem);
	}
	
	@Test
	void deletaPostagem_comIdValido() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagem = mock(Postagem.class);
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagem));

		postagemApplicationService.deletaPostPorId(usuario.getEmail(), conteudo.getIdConteudo(), postagem.getIdPostagem());
		
		verify(postagem).pertenceUsuario(usuario);
		verify(postagemRepository, times(1)).deletaPost(conteudo, postagem);
		
		assertEquals(conteudo.getQuantidadePostagem(), 0);
	}
	
	@Test
	void ativaPostagem_comIdValido() {
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagemMock = mock(Postagem.class);
		
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagemMock));

		postagemApplicationService.usuarioAtivaPostagem(conteudo.getIdConteudo(), postagemMock.getIdPostagem());
		
		verify(postagemMock).pertenceConteudo(conteudo);
		verify(postagemRepository, times(1)).desativaPostagem(conteudo.getIdConteudo());
		verify(postagemMock).ativaPostagem();
		verify(postagemRepository, times(1)).salvaPostagem(postagemMock);

//		assertEquals(postagemMock.getStatusAtivacao(), StatusAtivacaoPostagem.ATIVO);
	}

	@Test
	void teste(){
		System.out.println(UUID.randomUUID());
	}
}
