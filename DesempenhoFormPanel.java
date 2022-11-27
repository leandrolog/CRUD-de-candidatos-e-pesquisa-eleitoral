import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DesempenhoFormPanel extends JPanel {


    private AppFrame frame;

    private Desempenho desempenho;

    private GridBagLayout layoutDesempenho;
    private GridBagConstraints constraintsDesempenho;

    private JTextField txtidDesempenho;
    private JTextField txtNome;
    private JTextField txtData;
    private JTextField txtIntençãoDeVoto;
    private JTextField txtIdcandidato;
    private JButton btnSalvar;
    private JButton btnCancelar;


    public DesempenhoFormPanel(AppFrame frame) {
        this.frame = frame;

        layoutDesempenho = new GridBagLayout();
        constraintsDesempenho = new GridBagConstraints();

        setLayout(layoutDesempenho);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (desempenho == null) {
                    txtNome.setText("");
                    txtData.setText("");
                    txtIntençãoDeVoto.setText("");
                    txtIdcandidato.setText("");

                } else {
                    txtidDesempenho.setText(Integer.toString(desempenho.getIdDesempenho()));
                    txtNome.setText(desempenho.getNome());
                    txtData.setText(desempenho.getData());
                    txtIntençãoDeVoto.setText(desempenho.getIntencaoDeVoto());
                    txtIdcandidato.setText("");
                }
            }
        });

        criarForm();
    }

    public void setInstitutoDePesquisa(Desempenho desempenho) {
        this.desempenho = desempenho;
    }
    private void criarForm() {
        JLabel label;

        label = new JLabel("Id ");
        adicionarComponente(label, 0, 0);
        txtidDesempenho = new JTextField(5);
        txtidDesempenho.setEditable(false);
        adicionarComponente(txtidDesempenho, 0, 2,1,1);

        label = new JLabel("Id Candidato");
        adicionarComponente(label, 1, 0);
        txtIdcandidato = new JTextField( 5);
        adicionarComponente(txtIdcandidato, 1, 2, 1, 1);

        label = new JLabel("Nome");
        adicionarComponente(label, 2, 0);
        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 2, 2,1,1);

        label = new JLabel("Data");
        adicionarComponente(label, 3, 0);
        txtData = new JTextField( 30);
        adicionarComponente(txtData, 3, 2, 1, 1);

        label = new JLabel("Intenção De Voto");
        adicionarComponente(label, 4, 0);
        txtIntençãoDeVoto = new JTextField( 30);
        adicionarComponente(txtIntençãoDeVoto, 4, 2, 1, 1);



        criarBotoes();
    }

    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 2, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DesempenhoFormPanel.this.desempenho == null) {
                    Desempenho novoDesemepenho = new Desempenho();
                    novoDesemepenho.setNome(txtNome.getText());
                    novoDesemepenho.setData(txtData.getText());
                    novoDesemepenho.setIntencaoDeVoto(txtIntençãoDeVoto.getText());
                    novoDesemepenho.setIdCandidato(Integer.parseInt(txtIdcandidato.getText()));

                    DesempenhoStorage.inserir(novoDesemepenho);
                    JOptionPane.showMessageDialog(DesempenhoFormPanel.this,
                            "Desempenho de Pesquisa adicionado com sucesso",
                            "App",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    desempenho.setNome(txtNome.getText());
                    desempenho.setData(txtData.getText());
                    desempenho.setIntencaoDeVoto(txtIntençãoDeVoto.getText());
                    desempenho.setIdCandidato(Integer.parseInt(txtIdcandidato.getText()));
                    DesempenhoStorage.atualizar(DesempenhoFormPanel.this.desempenho);
                    JOptionPane.showMessageDialog(DesempenhoFormPanel.this,
                            "Desempenho de Pesquisa atualizado com sucesso",
                            "App",
                            JOptionPane.INFORMATION_MESSAGE);
                }


                frame.mostrarListPanelDesempenho();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanelDesempenho();
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
        constraintsDesempenho.gridx = coluna;
        constraintsDesempenho.gridy = linha;
        constraintsDesempenho.gridwidth = largura;
        constraintsDesempenho.gridheight = altura;

        constraintsDesempenho.fill = GridBagConstraints.HORIZONTAL;

        layoutDesempenho.setConstraints(componente, constraintsDesempenho);
        add(componente);
    }
}
