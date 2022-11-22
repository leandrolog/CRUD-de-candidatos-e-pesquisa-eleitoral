import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InstitutoFormPanel extends JPanel {


    private AppFrame frame;

    private InstitutoDePesquisa instituto;

    private GridBagLayout layoutInstituto;
    private GridBagConstraints constraintsInstituto;

    private JTextField txtidInstituto;
    private JTextField txtNome;
    private JTextField txtEmpresaContratante;
    private JButton btnSalvar;
    private JButton btnCancelar;


    public InstitutoFormPanel(AppFrame frame) {
        this.frame = frame;

        layoutInstituto = new GridBagLayout();
        constraintsInstituto = new GridBagConstraints();

        setLayout(layoutInstituto);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (instituto == null) {
                    txtidInstituto.setText("");
                    txtNome.setText("");
                    txtEmpresaContratante.setText("");
                } else {
                    txtidInstituto.setText(Integer.toString(instituto.getIdInstituto()));
                    txtNome.setText(instituto.getNome());
                    txtEmpresaContratante.setText(instituto.getEmpresaContratante());
                }
            }
        });

        criarForm();
    }

    public void setInstitutoDePesquisa(InstitutoDePesquisa instituto) {
        this.instituto = instituto;
    }
    private void criarForm() {
        JLabel label;

        label = new JLabel("Id Instituto");
        adicionarComponente(label, 0, 0);
        txtidInstituto = new JTextField(5);
        txtidInstituto.setEditable(false);
        adicionarComponente(txtidInstituto, 0, 2);

        label = new JLabel("Nome");
        adicionarComponente(label, 1, 0);
        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 1, 2);

        label = new JLabel("Empresa Contratante");
        adicionarComponente(label, 2, 0);
        txtEmpresaContratante = new JTextField( 30);
        JScrollPane pane = new JScrollPane(txtEmpresaContratante);
        adicionarComponente(pane, 2, 2, 1, 1);

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
                if (InstitutoFormPanel.this.instituto == null) {
                    InstitutoDePesquisa novoInstituto = new InstitutoDePesquisa();
                    novoInstituto.setNome(txtNome.getText());
                    novoInstituto.setEmpresaContratante(txtEmpresaContratante.getText());

                    InstitutoStorage.inserir(novoInstituto);
                    JOptionPane.showMessageDialog(InstitutoFormPanel.this,
                            "Instituto de Pesquisa adicionado com sucesso",
                            "App",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    instituto.setNome(txtNome.getText());
                    instituto.setEmpresaContratante(txtEmpresaContratante.getText());
                    InstitutoStorage.atualizar(InstitutoFormPanel.this.instituto);
                    JOptionPane.showMessageDialog(InstitutoFormPanel.this,
                            "Instituto de Pesquisa atualizado com sucesso",
                            "App",
                            JOptionPane.INFORMATION_MESSAGE);
                }


                frame.mostrarListPanelInstituto();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarListPanelInstituto();
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
        constraintsInstituto.gridx = coluna;
        constraintsInstituto.gridy = linha;
        constraintsInstituto.gridwidth = largura;
        constraintsInstituto.gridheight = altura;

        constraintsInstituto.fill = GridBagConstraints.HORIZONTAL;

        layoutInstituto.setConstraints(componente, constraintsInstituto);
        add(componente);
    }
}
