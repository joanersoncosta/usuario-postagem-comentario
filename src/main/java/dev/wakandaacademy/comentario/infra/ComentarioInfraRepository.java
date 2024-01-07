package dev.wakandaacademy.comentario.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.domain.Comentario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Repository
public class ComentarioInfraRepository implements ComentarioRepository {
	private final ComentarioSpringDataMongoRepository comentarioSpringDataMongoRepository;
	
	@Override
	public Comentario adicionaComentario(Comentario comentario) {
		log.info("[inicia] ComentarioInfraRepository - adicionaComentario");
		comentarioSpringDataMongoRepository.save(comentario);
		log.info("[finaliza] ComentarioInfraRepository - adicionaComentario");
		return comentario;
	}

	@Override
	public Optional<Comentario> buscaComentario(UUID idComentario) {
		log.info("[inicia] ComentarioInfraRepository - buscaComentario");
		Optional<Comentario> comentario = comentarioSpringDataMongoRepository.findById(idComentario);
		log.info("[finaliza] ComentarioInfraRepository - buscaComentario");
		return comentario;
	}

}
