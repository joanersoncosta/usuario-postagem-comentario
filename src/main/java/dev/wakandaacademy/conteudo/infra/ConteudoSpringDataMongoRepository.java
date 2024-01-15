package dev.wakandaacademy.conteudo.infra;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.wakandaacademy.conteudo.domian.Conteudo;

public interface ConteudoSpringDataMongoRepository extends MongoRepository<Conteudo, UUID>{

}
