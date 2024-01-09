package dev.wakandaacademy.postagem.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PostagemInfraRepository implements PostagemRepository {
	private final PostagemSpringDataMongoRepository postagemSpringDataMongoRepository;
	private final MongoTemplate mongoTemplate;
	
	@Override
	public Postagem salvaPostagem(Postagem postagem) {
			log.info("[inicia] PostagemInfraRepository - salvaPostagem");
			postagemSpringDataMongoRepository.save(postagem);
			log.info("[finaliza] PostagemInfraRepository - salvaPostagem");
		return postagem;
	}

	@Override
	public List<Postagem> buscaPostagens() {
		log.info("[inicia] PostagemInfraRepository - buscaPostagens");
		List<Postagem> postagens = postagemSpringDataMongoRepository.findAll();
		log.info("[finaliza] PostagemInfraRepository - buscaPostagens");
		return postagens;
	}
	
	@Override
	public Optional<Postagem> buscaPostagemPorId(UUID idPostagem) {
		log.info("[inicia] PostagemInfraRepository - buscaPostagemPorId");
		Optional<Postagem> postagem = postagemSpringDataMongoRepository.findById(idPostagem);
		log.info("[finaliza] PostagemInfraRepository - buscaPostagemPorId");
		return postagem;
	}

	@Override
	public void deletaPost(Postagem postagem) {
		log.info("[inicia] PostagemInfraRepository - deletaPost");
		postagemSpringDataMongoRepository.delete(postagem);
		log.info("[finaliza] PostagemInfraRepository - deletaPost");
	}


}
