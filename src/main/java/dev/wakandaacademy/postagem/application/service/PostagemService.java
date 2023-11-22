package dev.wakandaacademy.postagem.application.service;

import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;

public interface PostagemService {
	PostagemIdResponse criarPostagem(PostagemRequest postagemRequest);

}
