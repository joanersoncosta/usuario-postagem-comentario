package dev.wakandaacademy.postagem.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostagemRequest {

	@NotBlank
	@Size(message = "Campo titlo postagem não pode estar vazio!", min = 3, max = 50)
	private String titlo;
	@NotBlank
	@Size(message = "Campo descrição postagem não pode estar vazio!", min = 3, max = 250)
	private String descricao;	

}