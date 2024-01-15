package dev.wakandaacademy.conteudo.infra;

import org.springframework.stereotype.Repository;

import dev.wakandaacademy.conteudo.application.service.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ConteudoInfraRepository implements ConteudoRepository {
	private final ConteudoSpringDataMongoRepository conteudoSpringDataMongoRepository;
	
	@Override
	public Conteudo salvaConteudo(Conteudo conteudo) {
		log.info("[start] ConteudoInfraRepository - salvaConteudo");
		conteudoSpringDataMongoRepository.save(conteudo);
		log.info("[finish] ConteudoInfraRepository - salvaConteudo");
		return conteudo;
	}

}
