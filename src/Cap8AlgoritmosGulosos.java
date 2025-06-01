import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Cap8AlgoritmosGulosos {

    // sao muito simples, resolvem problemas em bulk (grande quantidade)
    // muitas vezes nao oferece a melhor solucao, mas a melhor solucao teria
    // uma complexidade de tempo mt cara, e valeria mais a pena um resultado
    // proximo antes de um resultado exato -> dependendo da situacao
    // Isso se chama ALGORITMO DE APROXIMACAO: sao rápidos e podem chegar
    // a solucao ideal, pode existir mais de uma resposta correta

    // Exemplo página 173
    public HashSet<String> exProblemaCoberturaConjuntos(List<String> estadosAbrangerList, HashMap<String, HashSet<String>> estacoes){
        // 1. lista dos estados que iremos abranger tranforma-se num set
        HashSet<String> estadosAbrangerSet = new HashSet<>();
        estadosAbrangerSet.addAll(estadosAbrangerList);

        // 2. Conjunto para armazenar estacoes finais
        HashSet<String> estacoesFinal = new HashSet<>();

        // 3. observar cada estacao e escolher uma que cubra o maior numero de estados
        while(!estadosAbrangerSet.isEmpty()){
            String melhorEstacao = null;
            HashSet<String> estadosCobertos = new HashSet<>();

            for (Map.Entry<String, HashSet<String>> estacao: estacoes.entrySet()){
                HashSet<String> cobertos = new HashSet<>();
                cobertos.retainAll(estadosAbrangerSet);

                // 4. Verifica se a estação assistida, abrange mais estados que a atual melhorEstacao
                if(cobertos.size() > estadosCobertos.size()){
                    melhorEstacao = estacao.getKey();
                    estadosCobertos = cobertos;
                }
            }
            // 5. Add a melhorEstacao ao set de estacoes finais e remove os estadosCobertos dos estadosAbranger
            if(melhorEstacao != null){
                estacoesFinal.add(melhorEstacao);
                estadosAbrangerSet.removeAll(estadosCobertos);
            }
        }
        return estacoesFinal;
    }
}
