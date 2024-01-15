package dev.wakandaacademy.conteudo.domian.enuns;

import org.springframework.http.HttpStatus;

import dev.wakandaacademy.handler.APIException;

public enum ConteudoCategoria {
	TECNOLOGIA("TECNOLOGIA"), 
	VIAJEM("VIAJEM"), 
	PET("PET");
	
	private String nicho;
	
	ConteudoCategoria(String  nicho){
		this.nicho = nicho;
	}
	
	public String getNicho() {
		return this.nicho;
	}
	
	public static ConteudoCategoria verificaValor(String nicho) {
	    for (ConteudoCategoria valorCorrespondente : ConteudoCategoria.values()) {
	        if (valorCorrespondente.name().equals(nicho)) {
	            return valorCorrespondente;
	        }
	    }
	    throw APIException.build(HttpStatus.BAD_REQUEST, "Nicho não encontrado");
	}
}
