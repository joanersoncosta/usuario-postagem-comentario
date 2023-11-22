package dev.wakandaacademy.usuario.infra;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.usuario.domain.Usuario;

public interface UsuarioSpringDataMongoRepository extends MongoRepository<Usuario, UUID>{

}
