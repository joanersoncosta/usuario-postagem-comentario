package dev.wakandaacademy.conteudo.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ConteudoRequest {
	@NotBlank
	@Size(min = 3, max = 50)
	private String descricao;
	private String categoria;
}
