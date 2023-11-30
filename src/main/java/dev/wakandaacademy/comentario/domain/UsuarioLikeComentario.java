package dev.wakandaacademy.comentario.domain;

import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "idUsuario")
public class UsuarioLikeComentario {
	@Indexed(unique = true)
	private UUID idUsuario;
	@Builder.Default
	@Setter
	private boolean likeUsuario = false;
}
