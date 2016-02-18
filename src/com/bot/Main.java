package com.bot;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Bot LEIA = new Bot();
		while(true){
			LEIA.getPergunta("banco.txt");
		}
	}
}
