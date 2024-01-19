package dev.wakandaacademy.usuario.domain.enuns;

import java.util.Arrays;
import java.util.Optional;

public enum Sexo {
	FEMININO("FEMININO"), MASCULINO("MASCULINO");
	
	private String sexo;
	
	Sexo(String  sexo){
		this.sexo = sexo;
	}
	
	public String getSexo() {
		return this.sexo;
	}
	
	public static Optional<Sexo> validaSexo(Sexo sexo) {
		return Arrays.stream(values()).filter(valorCorrespondente -> valorCorrespondente.getSexo().equals(sexo))
				.findFirst();
	}
}
