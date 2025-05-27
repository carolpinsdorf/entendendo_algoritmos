import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
//--------------------------------------------------------------------------------------------------------
    // Capitulo 1 - PESQUISA BINÁRIA
    // Encontrar um elemento numa lista ordenada, de forma otimizada usando o modelo de dividir para con-
    // quistar
    // Tempo de execucao ->  O(log n)
    public static Integer binarySearch(int target, List<Integer> arr) {
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
    public static Integer binarySearchR(int target, List<Integer> arr, int low, int high) {
        // caso base -> nao encontrado
        if(low > high) return null;

        int mid = (low + high) / 2;
        int midVal = arr.get(mid);

        if(midVal == target) return (Integer) mid;

        if(midVal > target) return binarySearchR(target, arr, low, mid - 1); // high passa a ser mid -1

        else return binarySearchR(target, arr, mid + 1, high); // low passa a ser mid +1
    }


//--------------------------------------------------------------------------------------------------------
    // Capitulo 2 - ORDENACAO POR SELECAO
    // Ordenar uma lista, percorrendo ela e pegando os elementos ordenados e colocando numa outra lista
    // Tempo de execucao -> O(n2) porém faz menos trocas (uma por iteracao), em comparacao com bubble e
    // insertion sort, porem estas ao mais estaveis (mesmo tempo de execucao)

    // Precisa de um méthodo auxiliar para buscar o menor (ou maior a ordem for decrescente)
    public static int findMin(List<Integer> arr) {
        int min = arr.get(0);
        for(Integer num : arr) {
            if(num < min) min = num;
        }
        return min;
    }
    public static List<Integer> selectionSort(List<Integer> arr) {
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

//--------------------------------------------------------------------------------------------------------
    // Capitulo 3 - Recursao (conceito) -> dividir um problema em partes menores e resolve-lo até chegar
    // no caso base (qualquer condicao que faça o metodo parar de chamar-se repetitivamente)
    // ~ analogia do "oposto" do while ~
    // Call stack (pilha de chamada) - armazena as chamadas da funcoes de forma que a ultima a entrar é a
    // primeira a sair (LIFO - Last In First Out)


//--------------------------------------------------------------------------------------------------------
    // Capitulo 4 - RECURSAO
    // - EX4.1 - funcao que soma todos os numeros de uma array usando recursao
    public static int sumAll(int[] arr) {
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
    public static int lenArray(int[] arr) {
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
    public static int maxValue(int[] arr) {
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
    public static List<Integer> quickSort(List<Integer> arr) {

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

//-------------------------------------------------------------------------------------------------------
    // Capitulo 5 - Tabelas Hash (hashtables, hashmaps, dicionarios)
    // mapeiam uma string para um numero, se forma que esta string sempre retornará o mesmo numero
    // ex.: lista de mercado -> insere a palavra abacate, a funcao hash retona um indice onde vai
    // guardar o preco de abacate, e para esse palavra sempre retornará o mesmo indice

    // criando um hashmap
    public static void createHashTable() {
        HashMap<String, Integer> listaMercado = new HashMap<>();
        listaMercado.put("Melao", 2423);
    }

//-------------------------------------------------------------------------------------------------------
    // Capitulo 6 - Grafos e BFS (pesquisa em largura)
    // Servem para achar o minimo caminho possivel, ou se há um caminho (relacao)
    // Ex.: min. movimento para ganhar num jogo, corretor ortográfico (min alteracoes para chegar numa
    // palavras real), encontrar algo mais próximo de voce  ->  isso tudo é BFS (pesquisa em largura)
    // Grafos - > estrutura de dados, baseadas num conjunto de conexoes. Composto por vertices e arestas
    // (vertex and edges), podem ter n conexoes





    public static void main(String[] args) {

        //-----------------------------------------------------------------------------------------------

        //System.out.println(isPalindrome("0P"));




    }
}