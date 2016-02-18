package com.bot;

public class Word {
	private String nome;
	private String tipo;
	private String definicaoTipo;
	
	public Word(String nome, String tipo, String definicaoTipo){
		this.nome = nome;
		this.tipo = tipo;
		this.definicaoTipo = definicaoTipo;
	}

	public String getDefinicaoTipo() {
		return this.definicaoTipo;
	}

	public String getNome() {
		return this.nome;
	}

	public String getTipo() {
		return this.tipo;
	}
	
}
