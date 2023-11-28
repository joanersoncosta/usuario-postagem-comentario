package dev.wakandaacademy.comentario.application.api;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ComentarioRequest {
	private UUID idPostagem;
	private UUID idUsuario;
}
