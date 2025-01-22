import java.util.ArrayList;
import java.util.List;

public class ListarCompras {
    private List<Item> itens;

    public ListarCompras() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(String nome, String categoria, String mes) {
        Item novoItem = new Item(nome, categoria, mes);
        itens.add(novoItem);
    }

    public void marcarComoComprado(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.get(indice).marcarComoComprado();
        }
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        }
    }

    public List<Item> getItens() {
        return itens;
    }
}
