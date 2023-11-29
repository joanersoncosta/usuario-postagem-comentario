package dev.wakandaacademy.postagem.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostagemRequest {
	@NotBlank
	public String email;
	@NotBlank
	@Size(message = "Campo titlo postagem não pode estar vazio!", min = 3, max = 50)
	private String titlo;
	@NotBlank
	@Size(message = "Campo descrição postagem não pode estar vazio!", min = 3, max = 250)
	private String descricao;
}
