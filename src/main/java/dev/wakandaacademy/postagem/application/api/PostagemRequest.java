package dev.wakandaacademy.postagem.application.api;

import java.util.UUID;

import lombok.Getter;

@Getter
public class PostagemRequest {
	
	private UUID idUsuario;
	private String titlo;
	private String descricao;

}
