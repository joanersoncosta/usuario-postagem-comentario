package dev.wakandaacademy.postagem.infra;

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
	
	@Override
	public Postagem salvaPostagem(Postagem postagem) {
			log.info("[inicia] PostagemInfraRepository - salvaPostagem");
			postagemSpringDataMongoRepository.save(postagem);
			log.info("[finaliza] PostagemInfraRepository - salvaPostagem");
		return postagem;
	}
}
