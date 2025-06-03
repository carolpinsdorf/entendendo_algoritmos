public class Cap9ProgramacaoDinamica {

    // divide o problema em sub problemas e os resolve, de forma escalonada
    // sempre comeca com uma tabela, que vai sendo preenchdia, quando for
    // a solucao será encontrada
    // util quando quer otimizar o resultado em relacao a um limite

    // 1. cria a tabela em que as linhas sao os itens, e as colunas sao o peso (de 0 a capaxidade maxima)
    // 2. percorrer a lista de itens disponiveis, de 1 a n
        // 3. percorrer cada coluna de pesos, de 0 até a capaxidade maxima
        // 4. se o peso do item atual(i) for maior que a capacidade j -> nao cabe
            // entao o valor maximo é o da linha de cima(sem o item)
        // 5. se o peso do item atual(i) for menor que a capacidade j -> cabe
            //calcula qual o maior resultado possivel
    // 6. o resultado estará na ultima celula da tabela

    // Representa um item com valor e peso
    static class Item {
        String nome;
        int valor;
        int peso;

        Item(String nome, int valor, int peso) {
            this.nome = nome;
            this.valor = valor;
            this.peso = peso;
        }
    }

    public static void main(String[] args) {
        // Lista de itens disponíveis
        Item[] itens = {
                new Item("Violao", 1500, 1),
                new Item("Notebook", 3000, 3),
                new Item("Lata de cerveja", 2000, 4)
        };

        int capacidade = 4; // Peso máximo da mochila

        // 1. cria a tabela em que as linhas sao os itens, e as colunas sao o peso (de 0 a capaxidade maxima)
        int[][] tabela = new int[itens.length +1][capacidade +1];

        // 2. percorrer a lista de itens disponiveis, de 1 a n
        for (int i = 1; i <= itens.length; i++){
            Item item = itens[i-1];         // pega o item atual

            // 3. percorrer cada coluna de pesos, de 0 até a capaxidade maxima
            for(int j = 0; j <= capacidade; j++){

                // 4. se o peso do item atual(i) for maior que a capacidade j -> nao cabe
                if(item.peso > j){
                    tabela[i][j] = tabela[i - 1][j];        // entao o valor maximo é o da linha de cima(sem o item)

                // 5. se o peso do item atual(i) for menor que a capacidade j -> cabe
                }else{
                    int semItem = tabela[i-1][j];
                    int comItem = item.valor + tabela[i - 1][j - item.peso];

                    tabela[i][j] = Math.max(semItem, comItem);      //calcula qual o maior resultado possivel
                }
            }
        }
        System.out.println(tabela[itens.length][capacidade]);

// ----------------------------------------------------------------------------------------------------------------------------
        // MAIOR SUBSTRING/SUBSEQUENCIA COMUM
        // recebendo uma palavra digitada por um usuario, qual das palavras da lista de palavras validas mais se assemelha
        // ao input do usuario

        String input = "fosh";
        String[] dicionario = {"fish", "fort"};

        // 1. Criar a String de resultado
        String sugestao = "";

        // 2. Inicializa com a maior subsequencia comum com 0
        int maiorLCS = 0;

        //3. percorre cada palavra do dicionario
        for (String palavra: dicionario){
            int lcs = calculaLCS(input, palavra);           // programacao dinamica

            if(lcs > maiorLCS){
                maiorLCS = lcs;
                sugestao = palavra;
            }

        }

        System.out.println(sugestao);
    }

    public static int calculaLCS(String a, String b){
        // 1. cria a tabela de programacao dinamica
        int[][] dp = new int [a.length() + 1][b.length() +1];

        // 2. percorrer a string A
        for(int i = 1; i <= a.length(); i++ ){

            // 3. percorrer a string B
            for(int j = 1; j <= b.length(); j++){

                // 4. se o char na posicao i na String A for igual a da posicao j de B
                if (a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;           // incrementa o valor vindo da diagonal anterior

               // 5. se não forem iguais
                }else{      //  calcule o melhor LCS possível ignorando um caractere de A ou de B
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}
