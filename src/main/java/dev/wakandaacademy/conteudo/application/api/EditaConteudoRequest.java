package dev.wakandaacademy.conteudo.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class EditaConteudoRequest {
	@NotBlank(message = "Campo descricao não pode estar vazio.")
	@Size(min = 3, max = 50)
	private String descricao;
}
