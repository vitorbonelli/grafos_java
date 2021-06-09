package problema3;

/*
Vitor Cesar Cota Bonelli
RA: 11009113

O objetivo do programa é identificar se, dada uma lista de terminais e ferrovias, existem terminais que possuem característica de vértices de corte, ou seja, caso sejam removidos aumenta o número de componentes conexos da malha ferroviária.

O programa receberá os inteiros: quantidade de terminais, quantidade de ferrovias, lista de conexões.

A saída será a quantidade de terminais de corte e quais são eles.
*/

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

class Graph {
	private int V;

	private LinkedList<Integer> adj[];
	int cont = 0;

	Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i) {
			adj[i] = new LinkedList();
		}
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	// A partir de um DFS, buscaremos os vértices de corte
	// u --> Vértice a ser visitado
	// visited[] --> registro de vértices visitados
	// ord[] --> Ordem de descoberta do vértice
	// parent[] --> vértices pais
	// vertCorte[] --> relação de vértices de corte do grafo
	void buscaVerticeCorte(int u, boolean visited[], int ord[], int low[], int parent[], boolean vertCorte[]) {

		int children = 0;

		// marca nó como visitado
		visited[u] = true;

		// Registra ordem e low
		cont++;
		ord[u] = cont;
		low[u] = cont;

		Iterator<Integer> i = adj[u].iterator();
		while (i.hasNext()) {
			int v = i.next();

			// se nó v não foi visitado, marca u como nó pai de v
			if (!visited[v]) {
				children++;
				parent[v] = u;
				buscaVerticeCorte(v, visited, ord, low, parent, vertCorte);

				// verifica se v tem conexão a um nó ancestral de u
				low[u] = Math.min(low[u], low[v]);

				// nó u é raiz da árvore gerada por DFS?, possui mais de um filho?
				if (parent[u] == -1 && children > 1) {
					vertCorte[u] = true;
				}

				// nó u não é raiz? low dos filhos são maiores que a ordem de decoberta de u?
				if (parent[u] != -1 && low[v] >= ord[u]) {
					vertCorte[u] = true;
				}
			} else if (v != parent[u]) {
				low[u] = Math.min(low[u], ord[v]);
			}
		}
	}

	void verticeCorte() {
		boolean visited[] = new boolean[V];
		int ord[] = new int[V];
		int low[] = new int[V];
		int parent[] = new int[V];
		boolean vertCorte[] = new boolean[V];

		// Atribui valores padrão para parent, visited e vertCorte
		for (int i = 0; i < V; i++) {
			parent[i] = -1;
			visited[i] = false;
			vertCorte[i] = false;
		}

		// Chama função de busca de vértice corte para cada vértice no grafo
		// Garante que mesmo sendo desconexo o grafo todo seja considerado
		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				buscaVerticeCorte(i, visited, ord, low, parent, vertCorte);
			}
		}

		// imprime resultados
		int resultado = 0;

		for (int i = 0; i < V; i++) {
			if (vertCorte[i] == true) {
				resultado++;
			}
		}
		
		System.out.println("# de alvos possiveis: " + resultado);
		
		for (int i = 0; i < V; i++) {
			if (vertCorte[i] == true) {
				System.out.println(i);
			}
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int vertice = sc.nextInt();
		Graph g = new Graph(vertice);

		int ligacoes = sc.nextInt();

		int i;
		int x;
		int y;

		// atribui as ligações
		for (i = 0; i < ligacoes; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			g.addEdge(x, y);
		}

		g.verticeCorte();

	}
}
