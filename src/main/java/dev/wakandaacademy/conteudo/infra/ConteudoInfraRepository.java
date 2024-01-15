package dev.wakandaacademy.conteudo.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ConteudoInfraRepository implements ConteudoRepository {
	private final ConteudoSpringDataMongoRepository conteudoSpringDataMongoRepository;
	private final MongoTemplate mongoTemplate;
	
	@Override
	public Conteudo salvaConteudo(Conteudo conteudo) {
		log.info("[start] ConteudoInfraRepository - salvaConteudo");
		conteudoSpringDataMongoRepository.save(conteudo);
		log.info("[finish] ConteudoInfraRepository - salvaConteudo");
		return conteudo;
	}

	@Override
	public List<Conteudo> buscaConteudos() {
		log.info("[start] ConteudoInfraRepository - buscaConteudos");
		List<Conteudo> conteudos = conteudoSpringDataMongoRepository.findAll();
		log.info("[finish] ConteudoInfraRepository - buscaConteudos");
		return conteudos;
	}

	@Override
	public Optional<Conteudo> buscaConteudoPorId(UUID idConteudo) {
		log.info("[start] ConteudoInfraRepository - buscaConteudoPorId");
		Optional<Conteudo> conteudo = conteudoSpringDataMongoRepository.findById(idConteudo);
		log.info("[finish] ConteudoInfraRepository - buscaConteudoPorId");
		return conteudo;
	}

	@Override
	public List<Conteudo> buscaConteudosDoUsuario(UUID idUsuario) {
		log.info("[start] ConteudoInfraRepository - buscaConteudoPorId");
		Query query = new Query();
		query.addCriteria(Criteria.where("idUsuario").is(idUsuario));
		List<Conteudo> conteudo = mongoTemplate.find(query, Conteudo.class);
		log.info("[finish] ConteudoInfraRepository - buscaConteudoPorId");
		return conteudo;
	}

	@Override
	public void deletaConteudo(Conteudo conteudo) {
		log.info("[start] ConteudoInfraRepository - deletaConteudo");
		conteudoSpringDataMongoRepository.delete(conteudo);
		log.info("[finish] ConteudoInfraRepository - deletaConteudo");
	}

}
