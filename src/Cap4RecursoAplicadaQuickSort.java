import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cap4RecursoAplicadaQuickSort {

    //--------------------------------------------------------------------------------------------------------
    // Capitulo 4 - RECURSAO
    // - EX4.1 - funcao que soma todos os numeros de uma array usando recursao
    public int sumAll(int[] arr) {
        // 1 - pega o primeiro elemento da lista, index 0
        // 2 - cria uma novo array com os elementos da lista do index 1 até o final
        // 3 - soma recursivamente o restante, ate o arrei estiver vazio

        // caso base: array vazio -> caso que encerra o loop
        if (arr.length == 0) return 0;

        // separa o primeiro elemento
        int first = arr[0];                      //

        // cria um array com o restante, sem o primeiro elemento
        int[] rest = new int[arr.length - 1];      // seta o tamanho do array para 2
        for (int i = 1; i < arr.length; i++){      // joga os elementos do array pra rest
            rest[i - 1] = arr[i];                  // menos o 1o elemento
        }

        // caso recursivo
        return first + sumAll(rest);
    }

    //-------------------------------------------------------------------------------------------------------
    // Capitulo 4 - EX4.2 - funcao recursiva que conta o numero de itens numa lista
    public int lenArray(int[] arr) {
        // 1. cria uma lista com o todos os elementos menos o primeiro
        // 2. chama recursivamente a funcao, até o array estiver vazio

        // caso base: array está vazio
        if(arr.length == 0) return 0;

        int[] rest = new int[arr.length - 1];
        for(int i = 1; i < arr.length; i++){
            rest[i-1] = arr[i];
        }

        //caso recursivo
        return 1 + lenArray(rest);
    }

    //-------------------------------------------------------------------------------------------------------
    // Capitulo 4 - Ex4.3 - encontre o valor mais alto em uma lista
    public int maxValue(int[] arr) {
        // 2. guardar 1o elemento da lista numa variavel
        // 3. cria uma lista com o restante dos elementos

        // caso base -> se restar apenas um element ele é o maior
        if(arr.length == 1) return arr[0];

        int first = arr[0];  // 1

        int[] rest = new int [arr.length - 1]; // 2, 3, 4
        for(int i = 1; i < arr.length; i++) {
            rest[i - 1] = arr[i];
        }

        // chamada recursiva
        int max = maxValue(rest);
        return max;
    }

    //-------------------------------------------------------------------------------------------------------
    // Capitulo 4 - QUICK SORT (divisao e conquista)
    // Função mais rápida para ordenação, utiliza a regressao um exemplo de DC
    // Tempo de execucao: o pior cenário é O(n2), porém o caso médio (maioria dos casos) é O(n log n)
    // nesse caso as constantes sao relevantes e explicam por que o quicksort é mais rapido que o merge
    public List<Integer> quickSort(List<Integer> arr) {

        //1  - escolha o pivot , 2 - particione em sub arrays , 3 - execute a recursao , 4 - concatene

        // caso base -> se o array tiver 0 ou 1 elementos, pode retorna-lo
        if(arr.size() < 2) return arr;

        // caso regressivo
        // 1. escolhe um pivo, se escolher o 1o o tempo de execucao será 0(n2)
        //int pivot = arr.get(0);
        int pivotRandomIndex = new Random().nextInt(arr.size()); // pega um numero aleatorio no intervalo
        int pivot = arr.get(pivotRandomIndex);                   // de 0 ao tamanho do array

        // 2. particiona em 2 sub arrays onde em um fica todos os numeros < que o pivo
        List<Integer> menores = new ArrayList<>();
        // e outro onde fica todos os numeros > que pivot
        List<Integer> maiores = new ArrayList<>();
        // com pivot aleatirio precisa guardar ele/eles(se tiver duplicata
        List<Integer> iguais = new ArrayList<>();

        // i = 1 pois pula o pivot
        for (int num : arr) {
            if (num < pivot){
                menores.add(Integer.valueOf(num));
            }else if(arr.get(num) > pivot){
                maiores.add(Integer.valueOf(num));
            }else{
                iguais.add(Integer.valueOf(num)); // so for igual
            }
        }

        // 3. recursao e (4)concatenacao do resultado
        List<Integer> resultado = new ArrayList<>();
        resultado.addAll(quickSort(menores));
        resultado.addAll(iguais);
        resultado.addAll(quickSort(maiores));

        return resultado;
    }
}
