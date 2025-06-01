import java.util.ArrayList;
import java.util.List;

public class Cap2OrdenacaoSelecao {

    //--------------------------------------------------------------------------------------------------------
    // Capitulo 2 - ORDENACAO POR SELECAO
    // Ordenar uma lista, percorrendo ela e pegando os elementos ordenados e colocando numa outra lista
    // Tempo de execucao -> O(n2) porém faz menos trocas (uma por iteracao), em comparacao com bubble e
    // insertion sort, porem estas ao mais estaveis (mesmo tempo de execucao)

    // Precisa de um méthodo auxiliar para buscar o menor (ou maior a ordem for decrescente)
    public int findMin(List<Integer> arr) {
        int min = arr.get(0);
        for(Integer num : arr) {
            if(num < min) min = num;
        }
        return min;
    }
    public List<Integer> selectionSort(List<Integer> arr) {
        // 1. cria uma nova lista vazia
        // 2. percorre a lista toda
        // acha o menor valor da lista (ou o maior se for descendente)
        // remove da lista original e insere a nova lista
        // 3. retorna a nova lista

        List<Integer> sorted = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            int min = findMin(arr);
            arr.remove(Integer.valueOf(min)); // remove o valor
            sorted.add(Integer.valueOf(min));
        }

        return sorted;
    }
}
