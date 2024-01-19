package dev.wakandaacademy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.conteudo.domian.enuns.ConteudoCategoria;
import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.domain.Usuario;
import dev.wakandaacademy.usuario.domain.enuns.Sexo;

public class DataHelper {

	public static final UUID ID_USUARIO_VALIDO = UUID.fromString("8d58875d-2455-4075-8b5d-57c73fcf1241");
	public static final UUID ID_USUARIO_INVALIDO = UUID.fromString("b92ee6fa-9ae9-45ac-afe0-fb8e4460d839");
	public static final UUID ID_CONTEUDO_VALIDO = UUID.fromString("aa774eb6-55d5-4867-ac34-78f3f017435f");
	public static final Usuario USUARIO = createUsuario();
	public static final Conteudo CONTEUDO1 = createConteudo();

	public static Usuario createUsuario() {
		return Usuario.builder().idUsuario(ID_USUARIO_VALIDO).nome("nome").email("exemplo@gmail.com")
				.telefone("73999999999").sexo(Sexo.FEMININO).dataNascimento("1999-07-25")
				.momentoDoDacastro(LocalDateTime.now()).build();
	}

	public static Usuario createUsuarioRequest(UsuarioNovoRequest usuarioRequest) {
		return Usuario.builder().idUsuario(ID_USUARIO_VALIDO).nome(usuarioRequest.getNome())
				.email(usuarioRequest.getEmail()).telefone(usuarioRequest.getTelefone()).sexo(usuarioRequest.getSexo())
				.dataNascimento(usuarioRequest.getDataNascimento()).momentoDoDacastro(LocalDateTime.now()).build();
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

	public static List<Conteudo> createListConteudo() {
		return List.of(
				CONTEUDO1,
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
}