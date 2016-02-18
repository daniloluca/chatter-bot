package com.bot;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Bot {
	private String ask;
	private List<String> answer;
	private boolean answerSend;
	
	public void getPergunta(String base) throws IOException{		
		answer = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		answerSend = false;
		
		ask = sc.nextLine().toLowerCase();
		
		classificar(ask, base);
	}

	@SuppressWarnings("resource")
	public void classificar(String ask, String base) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(base));
		List<String> askBase = new ArrayList<String>();
		String line;
		boolean quit = false;
		
		while((line = reader.readLine()) != null && !quit){
			line = line.trim();
			if(line.contains("<category>")){
				while(!line.equals("</category>") && !quit){
					line = line.trim();
					if(line.contains("<pattern>")){
						String l;
						askBase = new ArrayList<String>();
						l = line.substring(line.indexOf("<pattern>")+9, line.indexOf("</pattern>"));
						while(l != null){
							if(l.contains(" ")){
								askBase.add(l.substring(0, l.indexOf(" ")));
								l = l.substring(l.indexOf(" ")+1, l.length());
							}else{
								askBase.add(l.substring(0, l.length()));
								l=null;
							}
						}
						if(verificar(ask, askBase)){
							while(!(line = reader.readLine()).equals("</category>")){
								line = line.trim();
								if(line.contains("<template>")){
									answer.add(line.substring(line.indexOf("<template>")+10, line.indexOf("</template>")));
								}
							}
							getResposta();
							quit = true;
						}else{
							line = reader.readLine();
							getResposta();
						}
					}
					else{
						line = reader.readLine();
					}
				}
			}
		}
		if(!answerSend){
			classificar("erro", base);
		}
	}
	
	public boolean verificar(String ask, List<String> askBase){
		List<String> askDiv = new ArrayList<String>();
		int contains = 0;
		
		while(ask != null){
			ask = ask.replace("?", "");
			if(ask.contains(" ")){
				askDiv.add(ask.substring(0, ask.indexOf(" ")));
				ask = ask.substring(ask.indexOf(" ")+1, ask.length());
			}else{
				askDiv.add(ask.substring(0, ask.length()));
				ask=null;
			}
		}
		for (String s : askBase) {
			if(askDiv.contains(s)){
				contains++;
			}
		}
		if(contains >= (askBase.size())){
			answerSend = true;
			return true;
		}else{
			return false;
		}
		
	}
	
	public void getResposta(){
		if(!answer.isEmpty()){
			int rand = (int) (Math.random() * answer.size());
			if(answer.get(rand).equals("fibonacci")){
				String l = ask;
				List<String> fibonacciNum = new ArrayList<String>();
				while(l != null){
					if(l.contains(" ")){
						fibonacciNum.add(l.substring(0, l.indexOf(" ")));
						l = l.substring(l.indexOf(" ")+1, l.length());
					}else{
						fibonacciNum.add(l.substring(0, l.length()));
						l=null;
					}
				}
				for (String s : fibonacciNum) {
					if(isInteger(s) >= 0){
						System.out.println("O numero na posição "+ s + " da sequencia Fibonacci é o " + fibonacci(Integer.parseInt(s)));
					}
				}
			}else{
				System.out.println(answer.get(rand));
			}
		}
	}
	
	public int fibonacci(int f){
		int b=0, c=1, d;
		if(f==1){
			return 0;
		}else if(f==2){
			return 1;
		}
	    for(int x=3; x<=f; x++){
	        d = b + c;
	        b = c;
	        c = d;
	    }
		return c;
	}
	
	public int isInteger(String s){
		try{
			Integer.parseInt(s);
		}catch(NumberFormatException e){
			return -1;
		}
		return Integer.parseInt(s);
	}
}
