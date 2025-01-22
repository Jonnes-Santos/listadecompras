public class Item {
    private String nome;
    private String categoria;
    private String mes;
    private boolean comprado;

    public Item(String nome, String categoria, String mes) {
        this.nome = nome;
        this.categoria = categoria;
        this.mes = mes;
        this.comprado = false;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getMes() {
        return mes;
    }

    public boolean isComprado() {
        return comprado;
    }

    public void marcarComoComprado() {
        this.comprado = true;
    }

    @Override
    public String toString() {
        return (comprado ? "[ x ]" : "[ ]") + nome + " (" + categoria + ") - " + mes;
    }
}
