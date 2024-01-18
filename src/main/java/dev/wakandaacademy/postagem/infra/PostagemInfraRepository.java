package dev.wakandaacademy.postagem.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
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
	public List<Postagem> buscaPostagens(UUID idConteudo) {
		log.info("[inicia] PostagemInfraRepository - buscaPostagens");
		Query query = new Query();
		query.addCriteria(Criteria.where("idConteudo").is(idConteudo));
		List<Postagem> postagens = mongoTemplate.find(query, Postagem.class);
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
	public void deletaPost(Conteudo conteudo, Postagem postagem) {
		log.info("[inicia] PostagemInfraRepository - deletaPost");
		removeComentariosAssociados(postagem);
		
		Query queryConteudo = new Query();
		queryConteudo.addCriteria(Criteria.where("idConteudo").is(conteudo.getIdConteudo()));
		Update update = new Update();
		update.set("quantidadePostagem", conteudo.getQuantidadePostagem() - 1);
		mongoTemplate.updateFirst(queryConteudo, update, Conteudo.class);

		Query queryPost = new Query();
		queryPost.addCriteria(Criteria.where("idPostagem").is(postagem.getIdPostagem()));
		mongoTemplate.remove(queryPost, Postagem.class);
		log.info("[finaliza] PostagemInfraRepository - deletaPost");
	}

	@Override
	public void desativaPostagem(UUID idConteudo) {
		log.info("[inicia] PostagemInfraRepository - desativaPostagem");
		Query query = new Query();
		query.addCriteria(Criteria.where("idConteudo").is(idConteudo));

		Update update = new Update();
		update.set("statusAtivacao", StatusAtivacaoPostagem.INATIVA);

		mongoTemplate.updateMulti(query, update, Postagem.class);
		log.info("[finaliza] PostagemInfraRepository - desativaPostagem");
	}

	@Override
	public List<Comentario> buscaComentarios(UUID idPostagem) {
		log.info("[inicia] PostagemInfraRepository - buscaComentarios");
		Query query = new Query();
		query.addCriteria(Criteria.where("idPostagem").is(idPostagem));
		List<Comentario> comentarios = mongoTemplate.find(query, Comentario.class);
		log.info("[finaliza] PostagemInfraRepository - buscaComentarios");
		return comentarios;
	}
	
	private void removeComentariosAssociados(Postagem postagem) {
		Query queryComentario = new Query();
		queryComentario.addCriteria(Criteria.where("idPostagem").is(postagem.getIdPostagem()));
		mongoTemplate.remove(queryComentario, Comentario.class);
	}

}