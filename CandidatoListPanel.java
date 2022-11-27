import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CandidatoListPanel extends JPanel {
    private AppFrame frame;

    private JButton btnCandidato;
    private JButton btnPesquisa;
    private JButton btnDesempenho;

    private JButton btnCriar;
    private JButton btnEditar;
    private JButton btnRemover;
    private JTable tabela;

    private CandidatoTableModel tableModel;

    public CandidatoListPanel(AppFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        criarComandosPanel();
        criarTabelaPanel();
        criarComandosMenuPanel();
    }

    private void criarComandosPanel() {
        JPanel panel = new JPanel();
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setAlignment(FlowLayout.CENTER);

        criarBtnCandidato();
        panel.add(btnCandidato);

        criarBtnPesquisa();
        panel.add(btnPesquisa);

        criarBtnDesempenho();
        panel.add(btnDesempenho);

        add(panel, BorderLayout.NORTH);

    }


    private void criarComandosMenuPanel() {
        JPanel panelMenu = new JPanel();
        FlowLayout layout = (FlowLayout) panelMenu.getLayout();
        layout.setAlignment(FlowLayout.CENTER);

        criarBtnCriar();
        panelMenu.add(btnCriar);

        criarBtnEditar();
        panelMenu.add(btnEditar);

        criarBtnRemover();
        panelMenu.add(btnRemover);

        add(panelMenu, BorderLayout.SOUTH);

        desabilitarBtns();
    }

    private void criarBtnCandidato() {//// leva a tabela de consulta candidato
        btnCandidato = new JButton("Candidato");
        btnCandidato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanel();
            }
        });
    }
    private void criarBtnPesquisa() {//// leva a tabela de consulta pesquisa
        btnPesquisa = new JButton("Pesquisa");
        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanelPesquisa();
            }
        });
    }

    private void criarBtnDesempenho() {
        btnDesempenho = new JButton("Desempenho");
        btnDesempenho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanelDesempenho();
            }
        });
    }


    private void criarBtnCriar() {
        btnCriar = new JButton("Criar");
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFormPanel(null);
            }
        });
    }

    private void criarBtnEditar() {
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFormPanel(tableModel.getCandidato(tabela.getSelectedRow()));
            }
        });
    }

    private void criarBtnRemover() {
        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidato candidato = tableModel.getCandidato(tabela.getSelectedRow());
                int resposta = JOptionPane.showConfirmDialog(CandidatoListPanel.this, "Deseja realmente remover?", "Todo App", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (resposta == JOptionPane.YES_OPTION) {
                    CandidatoStorage.remover(candidato);
                   recarregar();
                }
            }
        });
    }

    private void criarTabelaPanel() {///// Cria a tabela onde seram alocado os dados
        JPanel panel = new JPanel();

        tableModel = new CandidatoTableModel(CandidatoStorage.listar());
        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (tabela.getSelectedRow() >= 0) {
                        habilitarBtns();
                    } else {
                        desabilitarBtns();
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tabela);
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }

    private void habilitarBtns() {
        btnEditar.setEnabled(true);
        btnRemover.setEnabled(true);
    }

    private void desabilitarBtns() {
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
    }

    public void recarregar() {
        tableModel.carregar(CandidatoStorage.listar());
    }
} // fim da classe TarefaListPanel
