import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PesquisaFormPanel extends JPanel {


    private AppFrame frame;

    private Pesquisa pesquisa;

    private GridBagLayout layoutPesquisa;
    private GridBagConstraints constraintsPesquisa;

    private JTextField txtIdPesquisa;
    private JTextField txtIntencaoDeVoto;
    private JTextField txtData;
    private JTextField txtTurno;
    private JTextField txtTipoDePesquisa;
    private JTextField txtidInstituto;
    private JButton btnSalvar;
    private JButton btnCancelar;


    public PesquisaFormPanel(AppFrame frame) {
        this.frame = frame;

        layoutPesquisa = new GridBagLayout();
        constraintsPesquisa = new GridBagConstraints();

        setLayout(layoutPesquisa);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (pesquisa == null) {
                    txtIdPesquisa.setText("");
                    txtIntencaoDeVoto.setText("");
                    txtData.setText("");
                    txtTurno.setText("");
                    txtTipoDePesquisa.setText("");
                    txtidInstituto.setText("");
                } else {
                    txtIdPesquisa.setText(Integer.toString(pesquisa.getId()));
                    txtIntencaoDeVoto.setText(pesquisa.getNome());
                    txtData.setText(pesquisa.getPartido());
                    txtTurno.setText("");
                    txtTipoDePesquisa.setText("");
                    txtidInstituto.setText("");
                }
            }
        });

        criarForm();
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }
    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);
        txtIdPesquisa = new JTextField(5);
        txtIdPesquisa.setEditable(false);
        adicionarComponente(txtIdPesquisa, 0, 2);


        label = new JLabel("Id Instituto");
        adicionarComponente(label, 1, 0);
        txtidInstituto = new JTextField(5);
      ///  txtidInstituto.setEditable(false);
        adicionarComponente(txtidInstituto, 1, 2);



        label = new JLabel("Inteção de Voto");
        adicionarComponente(label, 2, 0);
        txtIntencaoDeVoto = new JTextField(30);
        adicionarComponente(txtIntencaoDeVoto, 2, 2);

        label = new JLabel("Data");
        adicionarComponente(label, 3, 0);
        txtData = new JTextField( 30);
        JScrollPane pane = new JScrollPane(txtData);
        adicionarComponente(pane, 3, 2, 1, 1);

        label = new JLabel("Turno");
        adicionarComponente(label, 4, 0);
        txtTurno = new JTextField( 30);

        adicionarComponente(txtTurno, 4, 2, 1, 1);

        label = new JLabel("Tipo de Pesquisa");
        adicionarComponente(label, 5, 0);
        txtTipoDePesquisa = new JTextField( 30);
        adicionarComponente(txtTipoDePesquisa, 5, 2, 1, 1);

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
                if (PesquisaFormPanel.this.pesquisa == null) {
                    Pesquisa novaPesquisa = new Pesquisa();
                    novaPesquisa.setIntencaoDeVoto(txtIntencaoDeVoto.getText());
                    novaPesquisa.setData(txtData.getText());
                    novaPesquisa.setTurno(txtTurno.getText());
                    novaPesquisa.setTipoDepesquisa(txtTipoDePesquisa.getText());
                    novaPesquisa.setIdInstitutoProv(Integer.parseInt(txtidInstituto.getText()));

                    PesquisaStorage.inserir(novaPesquisa);
                    JOptionPane.showMessageDialog(PesquisaFormPanel.this,
                            "Pesquisa adicionada com sucesso",
                            "App",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    pesquisa.setIntencaoDeVoto(txtIntencaoDeVoto.getText());
                    pesquisa.setData(txtData.getText());
                    pesquisa.setTurno(txtTurno.getText());
                    pesquisa.setTipoDepesquisa(txtTipoDePesquisa.getText());
                    PesquisaStorage.atualizar(PesquisaFormPanel.this.pesquisa);
                    JOptionPane.showMessageDialog(PesquisaFormPanel.this,
                            "Pesquisa atualizada com sucesso",
                            "App",
                            JOptionPane.INFORMATION_MESSAGE);
                }


                frame.mostrarListPanelPesquisa();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanelPesquisa();
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
        constraintsPesquisa.gridx = coluna;
        constraintsPesquisa.gridy = linha;
        constraintsPesquisa.gridwidth = largura;
        constraintsPesquisa.gridheight = altura;

        constraintsPesquisa.fill = GridBagConstraints.HORIZONTAL;

        layoutPesquisa.setConstraints(componente, constraintsPesquisa);
        add(componente);
    }
}
