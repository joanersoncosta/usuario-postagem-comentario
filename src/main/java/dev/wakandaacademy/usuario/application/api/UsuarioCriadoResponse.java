package dev.wakandaacademy.usuario.application.api;

import java.util.UUID;

import dev.wakandaacademy.usuario.domain.Usuario;
import dev.wakandaacademy.usuario.domain.enuns.Sexo;
import lombok.Value;

@Value
public class UsuarioCriadoResponse {
	private final UUID idUsuario;
	private final String nome;
	private final String email;
	private final String telefone;
	private final Sexo sexo;
	private final String dataNascimento;

	public UsuarioCriadoResponse(Usuario usuario) {
		this.idUsuario = UUID.randomUUID();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.sexo = usuario.getSexo();
		this.dataNascimento = usuario.getDataNascimento();
	}
	
	public static UsuarioCriadoResponse converteResponseParaUsuario(Usuario usuario) {
		return new UsuarioCriadoResponse(usuario);
	}
}
