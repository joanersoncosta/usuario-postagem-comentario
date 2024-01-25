package dev.wakandaacademy.postagem.application.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EditaPostagemRequest {
	private String titlo;
	private String descricao;
}
