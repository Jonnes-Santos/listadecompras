import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListarComprasGUI extends JFrame {
    private ListarCompras listarCompras;
    private DefaultListModel<String> listModel;
    private JList<String> listaItens;
    private JTextField campoItem;
    private JComboBox<String> comboCategoria;
    private JComboBox<String> comboMes;

    public ListarComprasGUI() {
        listarCompras = new ListarCompras();
        listModel = new DefaultListModel<>();
        listaItens = new JList<>(listModel);
        campoItem = new JTextField(20);

        // Inicializa o comboCategoria com categorias
        comboCategoria = new JComboBox<>(new String[]{"Geral", "Frutas", "Limpeza", "Bebidas", "Outros"});

        // Inicializa o comboMes com os meses
        comboMes = new JComboBox<>(new String[]{"Todos", "Janeiro", "Fevereiro", "Março", "Abril", "Maio",
                "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"});

        configurarJanela();
        adicionarComponentes();
        atualizarLista();
    }

    private void configurarJanela() {
        setTitle("Lista de Compras");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));
    }

    private void adicionarComponentes() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelEntrada.setBackground(new Color(240, 240, 240));
        painelEntrada.add(new JLabel("Item:"));
        painelEntrada.add(campoItem);
        painelEntrada.add(new JLabel("Categoria:"));
        painelEntrada.add(comboCategoria);
        painelEntrada.add(new JLabel("Mês:"));
        painelEntrada.add(comboMes); // Adiciona o comboMes ao painel

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(new Color(50, 150, 250));
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });
        painelEntrada.add(botaoAdicionar);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.setBackground(new Color(240, 240, 240));

        JButton botaoMarcar = new JButton("Marcar como Comprado");
        botaoMarcar.setBackground(new Color(50, 200, 100));
        botaoMarcar.setForeground(Color.WHITE);
        botaoMarcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marcarComoComprado();
            }
        });
        painelBotoes.add(botaoMarcar);

        JButton botaoRemover = new JButton("Remover");
        botaoRemover.setBackground(new Color(250, 100, 100));
        botaoRemover.setForeground(Color.WHITE);
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerItem();
            }
        });
        painelBotoes.add(botaoRemover);

        painel.add(painelEntrada, BorderLayout.NORTH);
        painel.add(new JScrollPane(listaItens), BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        comboMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLista();
            }
        });

        add(painel);
    }

    private void adicionarItem() {
        String nomeItem = campoItem.getText().trim();
        String categoria = (String) comboCategoria.getSelectedItem();
        String mes = (String) comboMes.getSelectedItem();
        if (!nomeItem.isEmpty()) {
            listarCompras.adicionarItem(nomeItem, categoria, mes);
            atualizarLista();
            campoItem.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "O nome do item está vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void marcarComoComprado() {
        int indiceSelecionado = listaItens.getSelectedIndex();
        if (indiceSelecionado != -1) {
            listarCompras.marcarComoComprado(indiceSelecionado);
            atualizarLista();
        }
    }

    private void removerItem() {
        int indiceSelecionado = listaItens.getSelectedIndex();
        if (indiceSelecionado != -1) {
            listarCompras.removerItem(indiceSelecionado);
            atualizarLista();
        }
    }

    private void atualizarLista() {
        listModel.clear();
        String mesSelecionado = (String) comboMes.getSelectedItem();
        for (Item item : listarCompras.getItens()) {
            if (mesSelecionado.equals("Todos") || item.getMes().equals(mesSelecionado)) {
                listModel.addElement(item.toString());
            }
        }
    }
}