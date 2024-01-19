package dev.wakandaacademy.usuario.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.domain.enuns.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "idUsuario")
@Document(collection = "Usuario")
public class Usuario {

	@Id
	private UUID idUsuario;
	@NotBlank
	private String nome;
	@Email
	@NotNull
	@Indexed(unique=true) 
	private String email;
	@NotBlank
	private String telefone;
	@NotNull
	private Sexo sexo;
	@NotNull
	private String dataNascimento;
	
	private LocalDateTime momentoDoDacastro;
	private LocalDateTime dataHoraDaultimaAlteracao;

	public Usuario(UsuarioNovoRequest pessoaRequest) {
		this.idUsuario = UUID.randomUUID();
		this.nome = pessoaRequest.getNome();
		this.email = pessoaRequest.getEmail();
		this.telefone = pessoaRequest.getTelefone();
		this.sexo = retornaSexo(pessoaRequest.getSexo());
		this.dataNascimento = pessoaRequest.getDataNascimento();
		this.momentoDoDacastro = LocalDateTime.now();
	}

	private Sexo retornaSexo(Sexo sexo) {
		return Sexo.validaSexo(sexo)
	            .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Valor inválido."));
	}

}
