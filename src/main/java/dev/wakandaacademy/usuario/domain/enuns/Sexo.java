package dev.wakandaacademy.usuario.domain.enuns;

public enum Sexo {
	FEMININO("FEMININO"), MASCULINO("MASCULINO");
	
	private String sexo;
	
	Sexo(String  sexo){
		this.sexo = sexo;
	}
	
	public String getSexo() {
		return this.sexo;
	}
	
	public static Sexo verificaValor(String sexo){
		for (Sexo valorCorrespondente: Sexo.values()) {
			if(valorCorrespondente.getSexo() == sexo) {
				return valorCorrespondente;
			}
		}
		throw new IllegalArgumentException("Valor invalido");
	}
}
