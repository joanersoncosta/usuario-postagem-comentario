package dev.wakandaacademy.conteudo.domian.enuns;

import java.util.Arrays;
import java.util.Optional;

public enum ConteudoCategoria {
	TECNOLOGIA("TECNOLOGIA"), VIAJEM("VIAJEM"), PET("PET");

	private String nicho;

	ConteudoCategoria(String nicho) {
		this.nicho = nicho;
	}

	public String getNicho() {
		return this.nicho;
	}

	public static Optional<ConteudoCategoria> validaCategoria(String nicho) {
		return Arrays.stream(values()).filter(valorCorrespondente -> valorCorrespondente.getNicho().equals(nicho))
				.findFirst();
	}
}
