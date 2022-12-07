import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CandidatoFormPanel extends JPanel {


    private AppFrame frame;

    private Candidato candidato;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextArea txtPartido;
    private JButton btnSalvar;
    private JButton btnCancelar;






    public CandidatoFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (candidato == null) {
                    txtId.setText("");
                    txtNome.setText("");
                    txtPartido.setText("");
                } else {
                    txtId.setText(Integer.toString(candidato.getId()));
                    txtNome.setText(candidato.getNome());
                    txtPartido.setText(candidato.getPartido());
                }
            }
        });

        criarForm();
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Nome");
        adicionarComponente(label, 1, 0);
        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 1, 1);

        label = new JLabel("Partido");
        adicionarComponente(label, 2, 0);
        txtPartido = new JTextArea(5, 30);
        JScrollPane pane = new JScrollPane(txtPartido);
        adicionarComponente(pane, 2, 1, 1, 5);

        criarBotoes();
    }

    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 1, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CandidatoFormPanel.this.candidato == null) {
                    Candidato novoCandidato = new Candidato();
                    novoCandidato.setNome(txtNome.getText());
                    novoCandidato.setPartido(txtPartido.getText());

                    CandidatoStorage.inserir(novoCandidato);
                    JOptionPane.showMessageDialog(CandidatoFormPanel.this,
                            "Candidato Inserido com sucesso",
                            "Confirma",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    candidato.setNome(txtNome.getText());
                    candidato.setPartido(txtPartido.getText());
                    CandidatoStorage.atualizar(CandidatoFormPanel.this.candidato);
                    JOptionPane.showMessageDialog(CandidatoFormPanel.this,
                            "Candidato atualizado com sucesso",
                            "Confirma",
                            JOptionPane.INFORMATION_MESSAGE);
                }


                frame.mostrarListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanel();
            }
        });
        btnPanel.add(btnCancelar);
    }

    private void adicionarComponente(JComponent componente,
                                     int linha, int coluna) {
        adicionarComponente(componente, linha, coluna, 1, 1);
    }

    private void adicionarComponente(JComponent componente,
                                     int linha, int coluna,
                                     int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(componente, constraints);
        add(componente);
    }
}
