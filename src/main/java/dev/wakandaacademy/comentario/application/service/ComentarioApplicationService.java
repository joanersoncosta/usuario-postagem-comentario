package dev.wakandaacademy.comentario.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioListResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioResponse;
import dev.wakandaacademy.comentario.application.api.EditaComentarioRequest;
import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ComentarioApplicationService implements ComentarioService {
	private final PostagemRepository postagemRepository;
	private final UsuarioRepository usuarioRepository;
	private final ConteudoRepository conteudoRepository;
	private final ComentarioRepository comentarioRepository;

	@Override
	public ComentarioIdResponse adicionaComentario(String usuarioEmail, UUID idConteudo, UUID idPostagem,
			ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioApplicationService - adicionaComentario");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		Comentario comentario = comentarioRepository
				.salvaComentario(new Comentario(usuario, postagem, comentarioRequest));
		postagem.incrementaQuantidadeComentarios();
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] ComentarioApplicationService - adicionaComentario");
		return ComentarioIdResponse.builder().idComentario(comentario.getIdComentario()).build();
	}

	@Override
	public void removeComentario(String usuarioEmail, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - removeComentario");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}, [idComentario] {}", idConteudo, idPostagem, idComentario);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertencePost(postagem);
		comentario.pertenceUsuario(usuario);		
		comentarioRepository.removeComentario(postagem, comentario);
		log.info("[finaliza] ComentarioApplicationService - removeComentario");
	}

	@Override
	public void alteraComentario(String emailUsuario, UUID idConteudo, UUID idPostagem, UUID idComentario,
			EditaComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioApplicationService - alteraComentario");
		log.info("[emailUsuario] {}", emailUsuario);
		log.info("[idConteudo] {}, [idPostagem] {}, [idComentario] {}", idConteudo, idPostagem, idComentario);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(emailUsuario);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertencePost(postagem);
		comentario.pertenceUsuario(usuario);		
		comentario.alteraComentario(comentarioRequest);
		comentarioRepository.salvaComentario(comentario);
		log.info("[finaliza] ComentarioApplicationService - alteraComentario");
	}

	@Override
	public Comentario detalhaComentario(UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - detalhaComentario");
		Comentario comentario = comentarioRepository.buscaComentario(idComentario)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Comentário não encontrado"));
		log.info("[finaliza] ComentarioApplicationService - detalhaComentario");
		return comentario;
	}

	private Postagem detalhaPostagem(UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] ComentarioApplicationService - detalhaPostagem");
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(idConteudo)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Counteúdo não encontrado"));
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado"));
		postagem.pertenceConteudo(conteudo);
		log.info("[finaliza] ComentarioApplicationService - detalhaPostagem");
		return postagem;
	}

	@Override
	public ComentarioResponse buscaComentarioPorId(String usuarioEmail, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - buscaComentarioPorId");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}, [idComentario] {}", idConteudo, idPostagem, idComentario);
		usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		Comentario comentario = comentarioRepository.buscaComentario(idComentario)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Comentário não encontrado"));
		comentario.pertencePost(postagem);
		log.info("[finaliza] ComentarioApplicationService - buscaComentarioPorId");
		return ComentarioResponse.converte(comentario);
	}

	@Override
	public List<ComentarioListResponse> buscaComentarios(String usuarioEmail, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] ComentarioApplicationService - buscaPostagemComComentarios");
		log.info("[emailUsuario] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		detalhaPostagem(idConteudo, idPostagem);
		List<Comentario> comentarios = comentarioRepository.buscaComentarios(idPostagem);
		log.info("[finaliza] ComentarioApplicationService - buscaPostagemComComentarios");
		return ComentarioListResponse.converte(comentarios);
	}

	@Override
	public void usuarioLike(String usuarioEmail, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - usuarioLike");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}, [idComentario] {}", idConteudo, idPostagem, idComentario);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertencePost(postagem);
		comentario.pertenceUsuario(usuario);
		comentario.like(usuario);
		comentarioRepository.salvaComentario(comentario);
		log.info("[finaliza] ComentarioApplicationService - usuarioLike");
	}
	
	@Override
	public void usuarioDeslike(String usuarioEmail, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - usuarioDeslike");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}, [idComentario] {}", idConteudo, idPostagem, idComentario);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertencePost(postagem);
		comentario.pertenceUsuario(usuario);
		comentario.deslike(usuario);
		comentarioRepository.salvaComentario(comentario);
		log.info("[finaliza] ComentarioApplicationService - usuarioDeslike");
	}
}
