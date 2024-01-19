package dev.wakandaacademy.usuario.application.api;

import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(of = "idUsuario")
public class UsuarioIdResponse {
	private UUID idUsuario;

}
