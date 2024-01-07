package dev.wakandaacademy.comentario.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ComentarioRequest {
	@NotBlank
	@Size(message = "Campo descrição comentario não pode estar vazio!", min = 3, max = 250)
	private String descricao;
}
