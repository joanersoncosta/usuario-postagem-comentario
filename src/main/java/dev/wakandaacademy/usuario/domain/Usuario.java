package dev.wakandaacademy.usuario.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.usuario.application.api.PessoaNovoRequest;
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
	@UniqueElements
	private String email;
	@NotBlank
	private String telefone;
	@NotNull
	private String sexo;
	@NotNull
	private String dataNascimento;

	private LocalDateTime momentoDoDacastro;
	private LocalDateTime dataHoraDaultimaAlteracao;

	public Usuario(PessoaNovoRequest pessoaRequest) {
		this.idUsuario = UUID.randomUUID();
		this.nome = pessoaRequest.getNome();
		this.email = pessoaRequest.getEmail();
		this.telefone = pessoaRequest.getTelefone();
		setSexo(pessoaRequest.getSexo());
		this.dataNascimento = pessoaRequest.getDataNascimento();
		this.momentoDoDacastro = LocalDateTime.now();
	}

	private Sexo getSexo() {
		return Sexo.verificaValor(sexo);
	}

	private void setSexo(Sexo sexo) {
		if (sexo != null) {
			this.sexo = sexo.getSexo();
		}
	}


}
