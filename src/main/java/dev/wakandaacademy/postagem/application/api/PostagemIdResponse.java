package dev.wakandaacademy.postagem.application.api;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostagemIdResponse {
	private UUID idPostagem;
}
