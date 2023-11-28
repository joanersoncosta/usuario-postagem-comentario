package dev.wakandaacademy.comentario.application.api;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ComentarioIdResponse {
	private UUID idPostagem;
}
