package dev.wakandaacademy.comentario.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.postagem.domain.Postagem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Repository
public class ComentarioInfraRepository implements ComentarioRepository {
	private final ComentarioSpringDataMongoRepository comentarioSpringDataMongoRepository;
	private final MongoTemplate mongoTemplate;
	
	@Override
	public Comentario salvaComentario(Comentario comentario) {
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

	@Override
	public void removeComentario(Postagem postagem, Comentario comentario) {
		log.info("[inicia] ComentarioInfraRepository - removeComentario");
		Query queryPost = new Query();
		queryPost.addCriteria(Criteria.where("idPostagem").is(postagem.getIdPostagem()));
		Update update = new Update();
		update.set("quantidadeComentarios", postagem.getQuantidadeComentarios() - 1);
		mongoTemplate.updateFirst(queryPost, update, Postagem.class);

		Query queryComentario = new Query();
		queryComentario.addCriteria(Criteria.where("idComentario").is(comentario.getIdComentario()));
		mongoTemplate.remove(queryComentario, Comentario.class);
		log.info("[finaliza] ComentarioInfraRepository - removeComentario");
	}

	@Override
	public List<Comentario> buscaComentarios(UUID idPostagem) {
		log.info("[inicia] ComentarioInfraRepository - buscaComentarios");
		Query query = new Query();
		query.addCriteria(Criteria
				.where("idPostagem").is(idPostagem));
		List<Comentario> comentarios = mongoTemplate.find(query, Comentario.class);
		log.info("[finaliza] ComentarioInfraRepository - buscaComentarios");
		return comentarios;
	}

}
