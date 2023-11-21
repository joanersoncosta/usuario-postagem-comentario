package dev.wakandaacademy.usuario.application.api;

import dev.wakandaacademy.usuario.domain.enuns.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PessoaNovoRequest {
	@NotBlank
	private String nome;
	@Email
	@NotNull
	private String email;
	@NotBlank
	private String telefone;
	@NotBlank
	private Sexo sexo;
	@NotNull
	private String dataNascimento;
}
