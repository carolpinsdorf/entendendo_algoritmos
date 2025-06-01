import java.util.List;

public class Cap1PesquisaBinaria {

    //--------------------------------------------------------------------------------------------------------
    // Capitulo 1 - PESQUISA BINÁRIA
    // Encontrar um elemento numa lista ordenada, de forma otimizada usando o modelo de dividir para con-
    // quistar
    // Tempo de execucao ->  O(log n)
    public Integer binarySearch(int target, List<Integer> arr) {
        // 1. criar 2 ponteiros 1 no começo e outro no fim do array -> baixo e alto
        // 2. enquanto baixo < = a alto:
        // calcule o meio
        // compare o valor do meio com o valor buscado, se for igual retorna o indice
        // se valor do meio > que o target, repete a busca na 1a metade da lista (do inicio ao meio)
        // se valor do meio < que o target, repete a busca na 2a metade da lista (do meio ao fim)
        // 3. se sair do loop é que nao existe, retorna nulo

        int low = 0;
        int high = arr.size() - 1; // é o numero do index

        while(low <= high) {
            int mid = (low + high) / 2;
            int midVal = arr.get(mid);

            if(midVal == target) return (Integer) mid;
            else if(midVal > target) high = mid - 1;  // +1 e -1 serve para que ignore o elemento do meio que
            else low = mid + 1;                       // ja foi verificado
        }
        return null;
    }

    //--------------------------------------------------------------------------------------------------------
    // PESQUISA BINARIA COM RECURSAO (tem que passar low e high como parametro)
    // Tempo de execucao -> O(log n)
    public Integer binarySearchR(int target, List<Integer> arr, int low, int high) {
        // caso base -> nao encontrado
        if(low > high) return null;

        int mid = (low + high) / 2;
        int midVal = arr.get(mid);

        if(midVal == target) return (Integer) mid;

        if(midVal > target) return binarySearchR(target, arr, low, mid - 1); // high passa a ser mid -1

        else return binarySearchR(target, arr, mid + 1, high); // low passa a ser mid +1
    }
}
