package dev.wakandaacademy.usuario.application.api;

import dev.wakandaacademy.usuario.domain.enuns.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class UsuarioNovoRequest {
	@NotBlank
	private String nome;

	@NotBlank
	private String telefone;
	@NotNull
	private Sexo sexo;
	@NotNull
	private String dataNascimento;
	@Email
	@NotNull
	private String email;
	@NotNull
	@Size(min = 6)
	private String senha;
}
