package problema2;
/*
Vitor Cesar Cota Bonelli
RA: 11009113

O objetivo do programa é identificar se, dada uma lista de conexões entre aeroportos, todos estão conectados.

O programa receberá os inteiros: quantidade de aeroportos, quantidade de conexões, lista de conexões.

A saída será a quantidade de conexões mínima que adicionaremos ao grafo para termos todos os aeroportos conectados.

*/
import java.util.*;

public class Graph {

	private int V;
	private LinkedList<Integer> adj[];

	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	void addLigacao(int v, int w) {
		// adiciona ligação bidirecionalmente
		adj[v].add(w);
		adj[w].add(v);
	}

	int proximoDesconexo(int v) {

		// função retorna o menor vértice desconexo
		// caso esteja tudo conectado, retorna -1

		// Mapeia aeroportos visitados
		boolean visited[] = new boolean[V];

		// Realiza Busca em profundidade recursiva
		buscaProfundidade(v, visited);

		int i = 0;
		int desconexo = -1;
		while (desconexo == -1 && i < V) {

			if (!visited[i]) {
				desconexo = i;
			}

			i++;
		}

		return desconexo;

	}

	void buscaProfundidade(int v, boolean visited[]) {

		visited[v] = true;

		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				buscaProfundidade(n, visited);
		}
	}

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);

		int aeroportos = sc.nextInt();
		Graph pingu = new Graph(aeroportos);

		int ligacoes = sc.nextInt();

		int i;
		int x;
		int y;

		// atribui as ligações
		for (i = 0; i < ligacoes; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			pingu.addLigacao(x, y);
		}

		boolean grafoConectado = false;
		i = 0;
		int cont = 0;

		// checa se grafo está inteiramente conectado
		while (!grafoConectado) {
			i = pingu.proximoDesconexo(0);
			if (i == -1) {
				grafoConectado = true;
			} else {
				// caso não estiver, adiciona uma ligação ao próximo aeroporto sem conexão
				pingu.addLigacao(0, i);
				cont++;
			}
		}

		System.out.println("# de novos voos: " + cont);

	}
}
