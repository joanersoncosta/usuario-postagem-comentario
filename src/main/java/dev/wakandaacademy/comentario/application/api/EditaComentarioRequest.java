package dev.wakandaacademy.comentario.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EditaComentarioRequest {
	@NotBlank
	@Size(message = "Campo descrição comentario não pode estar vazio!", min = 3, max = 250)
	private String descricao;
}
