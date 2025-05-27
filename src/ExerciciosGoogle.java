import java.util.*;

public class ExerciciosGoogle {

    private boolean isAnagram(String a, String b) {
        // 1. criar mapa da palavra a com os caracteres e quantidades
        // 2. percorre a palavra b, verificando se tem o caracter e a quantidade necessaria
            // se nao tiver retorna falso

        //edge case: se a e b nao tiverem o mesmo tamanho
        if (a.length() != b.length()) return false;                         // false

        Map<Character, Integer> map = new HashMap<>();
        for(char c: a.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);                       // { a = 1, m = 1, o = 1, r = 1}
        }

        for(char c: b.toCharArray()){                                   // [r, o, m, a]
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) map.remove(c);  // se zerar a quantidade remove do dicionario
            } else{
                return false;
            }
        }
        return true;                                                        //true
    }

    public List<HashSet<String>> anagramsList(List<String> list){           // test: [amor, roma, carro, corar, arco]

        // 1. criar funcao helper isAnagram
        // 2. percorrer a lista comparanado uma palavra i, com todas as palavras da lista, verificando se é um anagrama
            // se for adiciona ao set (para nao ter duplicatas)
        // retorna a lista de sets

        List<HashSet<String>> result = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){                            // word 1 = amor
            String word1 = list.get(i);
            HashSet<String> anagrams = new HashSet<>();
                                                                                // Time complexity: O(n2) -> brute force solution
            for(int j = 1; i < list.size(); j++) {
                String word2 = list.get(j);                             // word2 = roma

                if (isAnagram(word1, word2)) {
                    anagrams.add(word1);                                // anagrams = { amor, roma}
                    anagrams.add(word2);
                }else{
                    anagrams.add(word1);
                }
            }
            result.add(anagrams);                                       //result [{amor, roma}] e assim vai
        }
        return result;
    }


}
