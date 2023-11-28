package dev.wakandaacademy.comentario.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Document(collation = "Comentario")
public class Comentario {
	
	@Id
	@MongoId(targetType = FieldType.STRING)
	private UUID idComentario;
	@Indexed
	private UUID idPostagem;
	@Indexed
	private UUID idUsuario;
	private Date data;
	@NotBlank
	@Size(message = "Campo descrição comentario não pode estar vazio!", min = 3, max = 250)
	private String descricao;
}
