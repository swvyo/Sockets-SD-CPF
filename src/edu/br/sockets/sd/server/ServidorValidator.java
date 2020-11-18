package edu.br.sockets.sd.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorValidator {
	
	private ServerSocket sckServidor;
	
	public ServidorValidator() throws IOException {
		this.sckServidor = new ServerSocket(4000);
		
		for (;;) {
			Socket validator;
			InputStreamReader isr;
			BufferedReader entrada;
			PrintWriter saida;
			
			validator = this.sckServidor.accept();
			isr = new InputStreamReader(validator.getInputStream());
			entrada = new BufferedReader(isr);
			saida = new PrintWriter(validator.getOutputStream(), true);
			
			String strCpf = "10101010101";
			int d1, d2;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;
			
			strCpf = entrada.readLine();
			
			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;
			
			if (strCpf.equals ("")) {
				
				saida.println(false);
			}
			
			for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
				
				digitoCPF = Integer.valueOf (
						strCpf.substring(nCount - 1, nCount)).intValue();
				
				//multiplica a ultima casa por 2, por 3 e a seguinto por 4 e segue adiante.
				
				d1 = d1 + (11 - nCount) * digitoCPF;
				
				//repetição do procedimento incluindo o digito verificado na etapa anterior
				
				d2 = d2 + (12 - nCount) * digitoCPF;
			}
			
			//primeiro resto da divisão por 11
			resto = (d1%11);
			
			//verificar o digito principal
			
			if (resto <2) {
				digito1 = 0;
			} else {
				digito1 = 11 - resto;
			}
			
			d2=+ 2 * digito1;

			resto = (d2%11);
			

			if (resto < 2) {
				digito2 = 0;
			} else {
				digito2 = 11 - resto;
			}
			
			//digito verificador do cpf recebido
			
			String nDigVerific = strCpf.substring(strCpf.length() -2,
					strCpf.length());
			
			//concatenação dos restos
			
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
			
			
			saida.println(nDigVerific.equals(nDigResult));
			
			
		}
		
	}
	
	public boolean CPF (String string) {
		return false;
	}

}
