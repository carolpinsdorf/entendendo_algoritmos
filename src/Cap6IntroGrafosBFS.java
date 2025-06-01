import java.util.*;

public class Cap6IntroGrafosBFS {

    //-------------------------------------------------------------------------------------------------------
    // Capitulo 6 - Grafos e BFS (pesquisa em largura)
    // Servem para achar o minimo caminho possivel, ou se há um caminho (relacao)
    // Ex.: min. movimento para ganhar num jogo, corretor ortográfico (min alteracoes para chegar numa
    // palavras real), encontrar algo mais próximo de voce  ->  isso tudo é BFS (pesquisa em largura)
    // Grafos - > estrutura de dados, baseadas num conjunto de conexoes. Composto por vertices e arestas
    // (vertex and edges), podem ter n conexoes. Representados por dicionarios

    // BFS - Pesquisa em largura -> verifica se há um caminho, se sim: qual é o mínimo caminho possivel
    // 1. criar uma fila que ira conter os vertices do grafo, para controlar a ordem
    // 2. crie um conjunto de visitados para evitar ciclos
    // 3. desenfilere o vertice e faça a verificação que for necessária
    // 4. percorra a lista de vizinhos, e se nao foram visitados, coloque na lista e coloque em "visitados"
    // 5. faça isso até que a fila estejas vazia

    // classe implementada para facilitar o codigo
    class Pessoa {
        public String nome;
        public Boolean vendeManga;
        public List<Pessoa> vizinhos;

        Pessoa(String name, Boolean vendeManga) {
            this.nome = nome;
            this.vendeManga  = vendeManga;
            this.vizinhos = new ArrayList<>();
        }
    }
    public Pessoa bfs(Pessoa voce){
        Queue<Pessoa> fila = new LinkedList<>();
        Set<Pessoa> visitados = new HashSet<>();

        fila.add(voce);
        visitados.add(voce);

        while(!fila.isEmpty()){
            Pessoa atual = fila.poll();
            System.out.println("Visitando: " + atual.nome);

            if (Boolean.TRUE.equals(atual.vendeManga)) {
                System.out.println(atual.nome + " vende manga!");
                return atual;
            }

            for(Pessoa vizinho: atual.vizinhos){
                if(!visitados.contains(vizinho)){
                    fila.add(vizinho);
                    visitados.add(vizinho);
                }
            }
        }
        System.out.println("Ninguém na rede vende manga.");
        return null;
    }
}
