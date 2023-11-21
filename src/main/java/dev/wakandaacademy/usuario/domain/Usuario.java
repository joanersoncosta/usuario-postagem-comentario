package dev.wakandaacademy.usuario.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.usuario.domain.enuns.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Usuario")
public class Usuario {

	@Id
	private UUID idUsuario;
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

	private LocalDateTime momentoDoDacastro;
	private LocalDateTime dataHoraDaultimaAlteracao;

	public Usuario(UUID idUsuario, String email, String nome, String telefone, String dataNascimento, Sexo sexo,
			LocalDateTime momentoDoDacastro) {
		this.idUsuario = UUID.randomUUID();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.momentoDoDacastro = LocalDateTime.now();
	}

}
