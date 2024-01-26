package dev.wakandaacademy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.EditaConteudoRequest;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.conteudo.domian.enuns.ConteudoCategoria;
import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.postagem.application.api.EditaPostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.domain.Usuario;
import dev.wakandaacademy.usuario.domain.enuns.Sexo;

public class DataHelper {

	public static final Usuario USUARIO = createUsuario();
	public static final UUID ID_USUARIO_VALIDO = UUID.fromString("8d58875d-2455-4075-8b5d-57c73fcf1241");
	public static final UUID ID_USUARIO_INVALIDO = UUID.fromString("b92ee6fa-9ae9-45ac-afe0-fb8e4460d839");
	public static final Conteudo CONTEUDO = createConteudo();
	public static final UUID ID_CONTEUDO_VALIDO = UUID.fromString("aa272bf8-02d3-4918-a2a4-21c9f68649b4");
	public static final Postagem POSTAGEM = createPostagem();
	public static final UUID ID_POSTAGEM_VALIDO = UUID.fromString("aa774eb6-55d5-4867-ac34-78f3f017435f");
	private static String publicador = getUserName(USUARIO.getEmail());;
	private static Comentario COMENTARIO = createComentario();
	public static final UUID ID_COMENTARIO_VALIDO = UUID.fromString("8d477cd4-fa55-47d0-a216-098f1cbc8faa");
	private static String comentarista = getUserName(USUARIO.getEmail());;

	public static Usuario createUsuario() {
		return Usuario.builder().idUsuario(ID_USUARIO_VALIDO).nome("nome").email("exemplo@gmail.com")
				.telefone("73999999999").sexo(Sexo.FEMININO).dataNascimento("1999-07-25")
				.momentoDoDacastro(LocalDateTime.now()).build();
	}

	public static UsuarioNovoRequest createUsuarioRequest() {
		return new UsuarioNovoRequest("nome", "73999999999", Sexo.FEMININO, "1999-07-25", "exemplo@gmail.com",
				"123456");
	}

	public static Credencial createCredencial() {
		return new Credencial("exemplo@gmail.com", "123456");
	}

	public static Conteudo createConteudo() {
		return Conteudo.builder().idConteudo(ID_CONTEUDO_VALIDO).idUsuario(USUARIO.getIdUsuario())
				.autor(USUARIO.getNome()).descricao("exemplo 1").categoria(ConteudoCategoria.TECNOLOGIA)
				.quantidadePostagem(0).momentoAlteracaoCategoria(LocalDateTime.parse("2024-01-19T16:25:11.820082200"))
				.build();
	}

	public static ConteudoRequest conteudoRequest() {
		return new ConteudoRequest("exemplo 1", "TECNOLOGIA");
	}

	public static EditaConteudoRequest editaConteudoRequest() {
		return new EditaConteudoRequest("exemplo 1");
	}

	public static List<Conteudo> createListConteudo() {
		return List.of(CONTEUDO,
				Conteudo.builder().idConteudo(UUID.randomUUID()).idUsuario(USUARIO.getIdUsuario())
						.autor(USUARIO.getNome()).descricao("exemplo 2").categoria(ConteudoCategoria.TECNOLOGIA)
						.quantidadePostagem(0)
						.momentoAlteracaoCategoria(LocalDateTime.parse("2024-01-19T16:25:11.820082200")).build(),
				Conteudo.builder().idConteudo(UUID.randomUUID()).idUsuario(USUARIO.getIdUsuario())
						.autor(USUARIO.getNome()).descricao("exemplo 3").categoria(ConteudoCategoria.TECNOLOGIA)
						.quantidadePostagem(0)
						.momentoAlteracaoCategoria(LocalDateTime.parse("2024-01-19T16:25:11.820082200")).build(),
				Conteudo.builder().idConteudo(UUID.randomUUID()).idUsuario(USUARIO.getIdUsuario())
						.autor(USUARIO.getNome()).descricao("exemplo 3").categoria(ConteudoCategoria.TECNOLOGIA)
						.quantidadePostagem(0)
						.momentoAlteracaoCategoria(LocalDateTime.parse("2024-01-19T16:25:11.820082200")).build());
	}

	public static Postagem createPostagem() {
		return Postagem.builder().idPostagem(ID_POSTAGEM_VALIDO).idConteudo(CONTEUDO.getIdConteudo())
				.idUsuario(USUARIO.getIdUsuario()).publicador(publicador).dataPostagem(LocalDateTime.now())
				.titlo("Exemplo 1").descricao("Teste salva Postagem 1").statusAtivacao(StatusAtivacaoPostagem.INATIVA)
				.quantidadeComentarios(0).like(0).deslike(0).build();
	}

	public static List<Postagem> createListPostagem() {
		return List.of(createPostagem(), 
				Postagem.builder()
				.idPostagem(UUID.randomUUID())
				.idConteudo(CONTEUDO.getIdConteudo())
				.idUsuario(USUARIO.getIdUsuario())
				.publicador(publicador)
				.dataPostagem(LocalDateTime.now())
				.titlo("Exemplo 2")
				.descricao("Teste salva Postagem 2")
				.statusAtivacao(StatusAtivacaoPostagem.INATIVA)
				.quantidadeComentarios(0)
				.like(0)
				.deslike(0).build(),
				Postagem.builder().idPostagem(UUID.randomUUID()).idConteudo(CONTEUDO.getIdConteudo())
						.idUsuario(USUARIO.getIdUsuario()).publicador(publicador).dataPostagem(LocalDateTime.now())
						.titlo("Exemplo 3").descricao("Teste salva Postagem 3")
						.statusAtivacao(StatusAtivacaoPostagem.INATIVA).quantidadeComentarios(0).like(0).deslike(0)
						.build());
	}

	public static PostagemRequest postagemRequest() {
		return new PostagemRequest("Exemplo 1", "Teste salva Postagem 1");
	}

	public static EditaPostagemRequest editaPostagemRequest() {
		return new EditaPostagemRequest("Exemplo 4", "Teste salva Postagem 4");
	}
	
	private static String getUserName(String email) {
		String[] nome = email.split("@");
		return publicador = nome[0];
	}

	public static Comentario createComentario() {
		return Comentario.builder().idComentario(ID_COMENTARIO_VALIDO).idConteudo(ID_CONTEUDO_VALIDO)
				.idPostagem(ID_POSTAGEM_VALIDO).publicador(publicador).comentarista(comentarista)
				.dataCriacaoComentario(LocalDateTime.now()).descricao("Exemplo 2").like(0).deslike(0).build();
	}

	public static ComentarioRequest createRequestComentaio() {
		return new ComentarioRequest("Exemplo Comentario 1");
	}

	public static List<Comentario> createListComentario() {
		return List.of(COMENTARIO,
				Comentario.builder().idComentario(UUID.randomUUID()).idConteudo(ID_CONTEUDO_VALIDO)
						.idPostagem(ID_POSTAGEM_VALIDO).publicador(publicador).comentarista(comentarista)
						.dataCriacaoComentario(LocalDateTime.now()).descricao("Exemplo 2").like(0).deslike(0).build(),
				Comentario.builder().idComentario(UUID.randomUUID()).idConteudo(ID_CONTEUDO_VALIDO)
						.idPostagem(ID_POSTAGEM_VALIDO).idUsuario(ID_USUARIO_VALIDO).publicador(publicador)
						.comentarista(comentarista).dataCriacaoComentario(LocalDateTime.now()).descricao("Exemplo 3")
						.like(0).deslike(0).build());
	}
}