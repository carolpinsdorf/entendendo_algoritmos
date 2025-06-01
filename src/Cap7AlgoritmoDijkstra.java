import java.util.*;

public class Cap7AlgoritmoDijkstra {

    // Algoritmo para grafos ponderados -> Grafos em que as arestas tem valor (e.g.: transito)
    // Encontra o caminho "mais barato", ou seja que tem a menor soma das arestas
    // Ex.: A pesquisa em largura encontra o minimo caminho (com menos arestas) Dijkstra encontra
    // os "mais rápido" ou o "mais barato".
    // So funciona em grafos sem ciclos, e sem pesos negativos

    // 1. faça uma tabela(map) com custo de cada vertice do mapa inteiro (os que nao estiver "a vista"
    // coloca como infinito)
    // 2. criar uma tabela com o pai de cada vertice
    // 3. encontre o vertice mais barato = A1
    // 4. descubra os custos para chegar nos vizinhos desse vertice (A1)
    // 5. registre os pais desses vizinhos, sera o A1 e atualize o preço
    // ~ isso se repete para todos os vertices ~

    // classe implementadada para faciltar
    static class Grafo {
        private Map<String, Map<String, Integer>> adjacencias = new HashMap<>();

        public void adicionarAresta(String origem, String destino, int peso) {
            adjacencias.putIfAbsent(origem, new HashMap<>());
            adjacencias.get(origem).put(destino, peso);
        }

        public Map<String, Integer> getVizinhos(String no) {
            return adjacencias.getOrDefault(no, new HashMap<>());
        }

        public Set<String> getNos() {
            return adjacencias.keySet();
        }
    }

    public Map<String, Integer> algoritmoDijkstra(Grafo grafo, String inicio){
        Map<String, Integer> custos = new HashMap<>();
        Map<String, String> pais = new HashMap<>();
        Set<String> processados = new HashSet<>();                                          // para evitar ciclos

        for (String no: grafo.getNos()){                                                    // 1. tabela de custos
            if (no.equals(inicio)) continue;
            Integer custo = grafo.getVizinhos(inicio).get(no);
            custos.put(no, custo != null? custo : Integer.MAX_VALUE);

            if(custo != null) pais.put(no, inicio);                                         //2. tabela com pai de cada vertice
        }
        custos.put(inicio, 0);                                                              // incia com custo 0

        String no = encontrarMenorCusto(custos, processados);                                //3. encontra o vertice mais barato

        while(no != null){
            int custo = custos.get(no);

            for (Map.Entry<String, Integer> vizinho: grafo.getVizinhos(no).entrySet()){
                int novoCusto = custo + vizinho.getValue();
                if(novoCusto < custos.getOrDefault(vizinho.getKey(), Integer.MAX_VALUE)){
                    custos.put(vizinho.getKey(), novoCusto);                                 // 4. atualiza o custo do caminho pro vizinho
                    pais.put(vizinho.getKey(), no);                                          // 5. registra os pais dos vizinhos
                }
            }
            processados.add(no);
            no = encontrarMenorCusto(custos, processados);                                      // RECURSAO, repete para cada vertice do grafo
        }
        return custos;
    }

    private String encontrarMenorCusto(Map<String, Integer> custos, Set<String> processados) {
        int menorCusto = Integer.MAX_VALUE;
        String menorNo = null;

        for (Map.Entry<String, Integer> entry: custos.entrySet()){
            if (!processados.contains(entry.getKey()) && entry.getValue() < menorCusto){
                menorCusto = entry.getValue();
                menorNo = entry.getKey();
            }
        }
        return menorNo;
    }


}
