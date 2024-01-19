package dev.wakandaacademy.credencial.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Credencial")
@EqualsAndHashCode(of = "usuario")
public class Credencial{
	@MongoId
	@Getter
	private String usuario;
	
	@NotNull
	@Size(min = 6)
	@Getter(value = AccessLevel.PRIVATE)
	private String senha;

	public Credencial(String email, String senha) {
		this.usuario = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
}
