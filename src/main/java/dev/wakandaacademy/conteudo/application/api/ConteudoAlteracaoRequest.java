package dev.wakandaacademy.conteudo.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConteudoAlteracaoRequest {
	@NotBlank
	@Size(min = 3, max = 50)
	private String descricao;
}
