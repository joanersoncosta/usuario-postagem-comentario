package dev.wakandaacademy.usuario.application.api;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PessoaIdResponse {
	private UUID idUsuario;

}
