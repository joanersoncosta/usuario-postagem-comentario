package dev.wakandaacademy.credencial.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.credencial.domain.Credencial;

public interface CredencialSpringMongoRepository extends MongoRepository<Credencial, String>{
	Credencial findByUsuario(String usuario);

}
