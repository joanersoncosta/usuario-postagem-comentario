package dev.wakandaacademy.postagem.application.service;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostagemApplicationService implements PostagemService {
	private final PostagemRepository postagemRepository;
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public PostagemIdResponse criarPostagem(String email, PostagemRequest postagemRequest) {
		log.info("[inicia] UsuarioRestController - criarPostagem");
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(email);
		Postagem postagem = postagemRepository.salvaPostagem(new Postagem(postagemRequest, usuario));
		log.info("[finaliza] UsuarioRestController - criarPostagem");
		return PostagemIdResponse.builder().idPostagem(postagem.getIdPostagem()).build();
	}
}
