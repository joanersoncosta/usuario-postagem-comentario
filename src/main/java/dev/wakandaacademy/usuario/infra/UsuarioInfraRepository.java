package dev.wakandaacademy.usuario.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class UsuarioInfraRepository implements UsuarioRepository {
	private final UsuarioSpringDataMongoRepository UsuarioSpringDataMongoRepository;

	@Override
	public Usuario salvaUsuario(Usuario usuario) {
		try {
			log.info("[inicia] UsuarioInfraRepository - salvaUsuario");
			UsuarioSpringDataMongoRepository.save(usuario);
			log.info("[finaliza] UsuarioInfraRepository - salvaUsuario");
		}catch (DataIntegrityViolationException ex) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Usuario já cadastrada");
		}
		return usuario;
	}

	@Override
	public Optional<Usuario> buscaUsuarioPorId(UUID idUsuario) {
		log.info("[inicia] UsuarioInfraRepository - buscaUsuarioPorId");
		Optional<Usuario> usuario = UsuarioSpringDataMongoRepository.findById(idUsuario);
		log.info("[finaliza] UsuarioInfraRepository - buscaUsuarioPorId");
		return usuario;
	}

	@Override
	public Usuario buscaUsuarioPorEmail(String email) {
		log.info("[inicia] UsuarioInfraRepository - buscaUsuarioPorEmail");
		Usuario usuario = UsuarioSpringDataMongoRepository.findByEmail(email).orElseThrow(() -> 
			APIException.build(HttpStatus.NOT_FOUND, "Usuario não encontrado para esse email!"));
		log.info("[finaliza] UsuarioInfraRepository - buscaUsuarioPorEmail");
		return usuario;
	}
}
