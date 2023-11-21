package dev.wakandaacademy.credencial.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "Credencial")
public class Credencial {
	@MongoId(targetType = FieldType.STRING)
	@Getter
	private String usuario;
	
	@NotNull
	@Size(min = 6)
	@Getter(value = AccessLevel.PRIVATE)
	private String senha;
}
