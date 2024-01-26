package dev.wakandaacademy.comentario.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import dev.wakandaacademy.DataHelper;
import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioListResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioResponse;
import dev.wakandaacademy.comentario.application.api.EditaComentarioRequest;
import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;

@ExtendWith(MockitoExtension.class)
class ComentarioApplicationServiceTest {

	@InjectMocks
	private ComentarioApplicationService comentarioApplicationService;
	@Mock
	private PostagemRepository postagemRepository;
	@Mock
	private UsuarioRepository usuarioRepository;
	@Mock
	private ConteudoRepository conteudoRepository;
	@Mock
	private ComentarioRepository comentarioRepository;
	private List<ComentarioListResponse> buscaComentarios;

	@Test
	void testAdicionaComentario() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagem = DataHelper.createPostagem();
		Postagem postagemMock = mock(Postagem.class);

		ComentarioRequest request = DataHelper.createRequestComentaio();

		when(usuarioRepository.buscaUsuarioPorEmail(usuario.getEmail())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(conteudo.getIdConteudo())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(postagem.getIdPostagem())).thenReturn(Optional.of(postagemMock));
		when(comentarioRepository.salvaComentario(any())).thenReturn(new Comentario(usuario, postagem, request));

		ComentarioIdResponse response = comentarioApplicationService.adicionaComentario(usuario.getEmail(), conteudo.getIdConteudo(), postagem.getIdPostagem(), request);
		
		verify(postagemMock).pertenceConteudo(conteudo);
		verify(postagemMock).incrementaQuantidadeComentarios();
		verify(postagemRepository, times(1)).salvaPostagem(postagemMock);
		
		assertNotNull(response);
		assertEquals(ComentarioIdResponse.class, response.getClass());
	}

	@Test
	void testRemoveComentario() {
		Usuario usuario = DataHelper.createUsuario();
		Comentario comentario = DataHelper.createComentario();
		Postagem postagem = DataHelper.createPostagem();
		Conteudo conteudo = DataHelper.createConteudo();
		
		Postagem postagemMock = mock(Postagem.class);
		Comentario comentarioMock = mock(Comentario.class);
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagemMock));
		when(comentarioRepository.buscaComentario(any())).thenReturn(Optional.of(comentarioMock));

		comentarioApplicationService.removeComentario(usuario.getEmail(), conteudo.getIdConteudo(), postagem.getIdPostagem(), comentario.getIdComentario());
		
		verify(comentarioMock).pertencePost(postagemMock);
		verify(comentarioMock).pertenceUsuario(usuario);
		verify(comentarioRepository, times(1)).removeComentario(postagemMock, comentarioMock);

		assertEquals(postagemMock.getQuantidadeComentarios(), 0);
	}

	@Test
	void testAlteraComentario() {
		Usuario usuario = DataHelper.createUsuario();
		Comentario comentario = DataHelper.createComentario();
		Postagem postagem = DataHelper.createPostagem();
		Conteudo conteudo = DataHelper.createConteudo();
		
		Postagem postagemMock = mock(Postagem.class);
		Comentario comentarioMock = mock(Comentario.class);
		EditaComentarioRequest request = DataHelper.createEditaComentario();
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagemMock));
		when(comentarioRepository.buscaComentario(any())).thenReturn(Optional.of(comentarioMock));

		comentarioApplicationService.alteraComentario(usuario.getEmail(), conteudo.getIdConteudo(), postagem.getIdPostagem(), comentario.getIdComentario(), request);
		
		verify(postagemMock).pertenceConteudo(conteudo);
		verify(comentarioMock).pertencePost(postagemMock);
		verify(comentarioMock).pertenceUsuario(usuario);
		verify(comentarioMock).alteraComentario(request);
		verify(comentarioRepository, times(1)).salvaComentario(comentarioMock);
	}

	@Test
	void testBuscaComentarioPorId() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagemMock = mock(Postagem.class);
		Comentario comentarioMock = mock(Comentario.class);
		
		String emailUsuario = DataHelper.createUsuario().getEmail();
		UUID idComentario = DataHelper.createComentario().getIdComentario();
		UUID idPostagem = DataHelper.createPostagem().getIdPostagem();
		UUID idConteudo = DataHelper.createConteudo().getIdConteudo();
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagemMock));
		when(comentarioRepository.buscaComentario(any())).thenReturn(Optional.of(comentarioMock));

		ComentarioResponse response = comentarioApplicationService.buscaComentarioPorId(emailUsuario, idConteudo, idPostagem, idComentario);
		
		verify(postagemMock).pertenceConteudo(conteudo);
		verify(comentarioMock).pertencePost(postagemMock);
		
		assertNotNull(response);
		assertEquals(ComentarioResponse.class, response.getClass());
	}

	@Test
	void testBuscaComentarios() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = DataHelper.createConteudo();
		List<Comentario> listComentario = DataHelper.createListComentario();
		Postagem postagemMock = mock(Postagem.class);
		
		String emailUsuario = DataHelper.createUsuario().getEmail();
		UUID idPostagem = DataHelper.createPostagem().getIdPostagem();
		UUID idConteudo = DataHelper.createConteudo().getIdConteudo();
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagemMock));
		when(comentarioRepository.buscaComentarios(any())).thenReturn(listComentario);

		List<ComentarioListResponse> response = comentarioApplicationService.buscaComentarios(emailUsuario, idConteudo, idPostagem);
		
		verify(postagemMock).pertenceConteudo(conteudo);
		verify(comentarioRepository, times(1)).buscaComentarios(idPostagem);

		assertNotNull(response);
		assertEquals(response, 3);
		assertEquals(ComentarioListResponse.class, response.getClass());
	}

	@Test
	void testUsuarioLike() {
		Usuario usuario = DataHelper.createUsuario();
		Conteudo conteudo = DataHelper.createConteudo();
		Postagem postagemMock = mock(Postagem.class);
		Comentario comentarioMock = mock(Comentario.class);

		String emailUsuario = DataHelper.createUsuario().getEmail();
		UUID idPostagem = DataHelper.createPostagem().getIdPostagem();
		UUID idConteudo = DataHelper.createConteudo().getIdConteudo();
		
		when(usuarioRepository.buscaUsuarioPorEmail(any())).thenReturn(usuario);
		when(conteudoRepository.buscaConteudoPorId(any())).thenReturn(Optional.of(conteudo));
		when(postagemRepository.buscaPostagemPorId(any())).thenReturn(Optional.of(postagemMock));
		when(comentarioRepository.buscaComentario(any())).thenReturn(Optional.of(comentarioMock));

		comentarioApplicationService.usuarioLike(emailUsuario, idConteudo, idPostagem, idConteudo);
		
		Comentario retornoComentario = DataHelper.getComentario();
		
		verify(postagemMock).pertenceConteudo(conteudo);
		verify(comentarioMock).pertencePost(postagemMock);
		verify(comentarioMock).pertenceUsuario(usuario);
		verify(comentarioMock).like(usuario);
		verify(comentarioRepository, times(1)).salvaComentario(comentarioMock);
		
		assertEquals(retornoComentario.getLike(), 1);
	}

	@Test
	void testUsuarioDeslike() {
	}

}
