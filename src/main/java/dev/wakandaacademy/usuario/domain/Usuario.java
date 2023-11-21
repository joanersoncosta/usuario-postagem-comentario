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
	@Email
	@NotNull
	private String email;
	@NotBlank
	private String nome;
	@NotNull
	private String dataNascimento;
	@NotBlank
	private Sexo sexo;
	private LocalDateTime momentoDoDacastro;
	private LocalDateTime dataHoraDaultimaAlteracao;

	public Usuario(UUID idUsuario, String email, String nome, String dataNascimento, Sexo sexo,
			LocalDateTime momentoDoDacastro) {
		this.idUsuario = UUID.randomUUID();
		this.email = email;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.momentoDoDacastro = LocalDateTime.now();
	}

}
