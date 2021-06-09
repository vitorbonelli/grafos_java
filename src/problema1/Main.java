package problema1;
/*
Vitor Cesar Cota Bonelli
RA: 11009113

O objetivo do programa é identificar se, dada uma lista de convidados, todos se conhecem.

O programa receberá os inteiros: quantidade de amigos do Marvin, quantidades de pares de amizades, lista de relações de amizades, quantidade de testes a realizar, quantidades de convidados de cada teste, relação de convidados de cada teste.

A saída será "SIM" ou "NAO", que representará se todos da lista se conhecem ou não.

*/

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//recebe quantidade de amigos
		int amigos = sc.nextInt();
		
		int[][] matriz = new int[amigos][amigos];
		
		int i;
		int j;
		
		//zera matriz de ligações
		for(i=0;i<amigos;i++){
		    for(j=0;j<amigos;j++){
		        matriz[i][j]=0;
		    }
		}
		
		int ligacoes = sc.nextInt();
		
		int x;
		int y;
		
		//atribui as ligações de forma simétrica em relação à diagonal principal
		for(i=0;i<ligacoes;i++){
		    x = sc.nextInt();
		    y = sc.nextInt();
		    matriz[x][y] = 1;
		    matriz[y][x] = 1;
		}
		
		//recebe quantidade de testes
		int testes = sc.nextInt();
		
		//laço de testes
		for (int cont=0;cont<testes;cont++){
		
		    //a resposta é sim por padrão
		    String resposta = "SIM";
		    
		    //recebe numero de convidados
		    int convidados = sc.nextInt();
		    
		    //recebe o nós convidados e guarda em um vetor
		    int[] vetor = new int[convidados];
		    for(i=0;i<convidados;i++){
		        vetor[i] = sc.nextInt();
		    }
		    
		    //testa todos os pares possíveis
		    //se encontrar um par que não é amigo, ja temos a resposta "não"
		    //não se aplica quando comparamos um convidado com ele mesmo (i!=j)
		    
		    for (i=0;i<convidados;i++){
		        for (j=0;j<convidados;j++){
		            
		            if(matriz[vetor[i]][vetor[j]] == 0 && i!=j){
		                resposta = "NAO";
		            }
		            
		        }
		    }
		    //imprime resposta
		    System.out.println(resposta);
		}

	}
}

