package edu.br.sockets.sd.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ProgramaCliente {
	
	@SuppressWarnings ("resource")
	public static void main (String [] args) throws IOException {
		Scanner sc = new Scanner (System.in);
		String strCpf;
		
		Socket sckCliente = new Socket ("127.0.0.1", 4000);
		PrintWriter saida = new PrintWriter(sckCliente.getOutputStream(), true);
		
		System.out.println ("Digite o CPF a ser verificado: ");
		
		strCpf = sc.next();
		saida.println(strCpf);
		
		InputStreamReader isr = new InputStreamReader( sckCliente.getInputStream());
		BufferedReader entrada = new BufferedReader(isr);
		
		for (;;) {
			String s = entrada.readLine();
			if (s == null)
				break;
			System.out.println("O CPF verificado é: " + s);
			
			
		}
		sckCliente.close();
	}

}
