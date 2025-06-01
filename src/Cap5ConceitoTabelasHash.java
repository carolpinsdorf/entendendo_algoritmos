import java.util.HashMap;

public class Cap5ConceitoTabelasHash {

    //-------------------------------------------------------------------------------------------------------
    // Capitulo 5 - Tabelas Hash (hashtables, hashmaps, dicionarios)
    // mapeiam uma string para um numero, se forma que esta string sempre retornará o mesmo numero
    // ex.: lista de mercado -> insere a palavra abacate, a funcao hash retona um indice onde vai
    // guardar o preco de abacate, e para esse palavra sempre retornará o mesmo indice

    // criando um hashmap
    public void createHashTable() {
        HashMap<String, Integer> listaMercado = new HashMap<>();
        listaMercado.put("Melao", 2423);
    }
}
