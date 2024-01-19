package dev.wakandaacademy;

import java.time.LocalDateTime;
import java.util.UUID;

import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.usuario.application.api.UsuarioIdResponse;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.domain.Usuario;
import dev.wakandaacademy.usuario.domain.enuns.Sexo;

public class DataHelper {

	public static final UUID ID_USUARIO_VALIDO = UUID.fromString("8d58875d-2455-4075-8b5d-57c73fcf1241");
	public static final UUID ID_TAREFA_INVALIDO = UUID.fromString("b92ee6fa-9ae9-45ac-afe0-fb8e4460d839");
	
	public static Usuario createUsuario(){
		return Usuario.builder().idUsuario(ID_USUARIO_VALIDO)
		.nome("nome")
		.email("exemplo@gmail.com")
		.telefone("73999999999")
		.sexo(Sexo.FEMININO)
		.dataNascimento("1999-07-25")
		.momentoDoDacastro(LocalDateTime.now())
		.build();
	}
	
	public static Usuario createUsuarioRequest(UsuarioNovoRequest usuarioRequest){
		return Usuario.builder().idUsuario(ID_USUARIO_VALIDO)
		.nome(usuarioRequest.getNome())
		.email(usuarioRequest.getEmail())
		.telefone(usuarioRequest.getTelefone())
		.sexo(usuarioRequest.getSexo())
		.dataNascimento(usuarioRequest.getDataNascimento())
		.momentoDoDacastro(LocalDateTime.now())
		.build();
	}
	
	public static final UsuarioIdResponse createUsuarioIdResponse() {
		return UsuarioIdResponse.builder().idUsuario(ID_USUARIO_VALIDO).build();
	}
	
	public static UsuarioNovoRequest createUsuarioRequest() {
		return new UsuarioNovoRequest("nome", "73999999999", Sexo.FEMININO, "1999-07-25", "exemplo@gmail.com", "123456");
	}

	public static Credencial createCredencial() {
		return new Credencial("exemplo@gmail.com", "123456");
	}
}