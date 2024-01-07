package dev.wakandaacademy.comentario.infra;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.comentario.domain.Comentario;

public interface ComentarioSpringDataMongoRepository extends MongoRepository<Comentario, UUID>{

}
