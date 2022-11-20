import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {


    private CardLayout cardLayout;
    private JPanel cardPanel;

    private CandidatoListPanel listPanel;
    private CandidatoFormPanel formPanel;

    private PesquisaListPanel listPanelPesquisa;
    private PesquisaFormPanel formPanelPesquisa;

    private InstitutoListPanel listPanelInstituto;
    private InstitutoFormPanel formPanelInstituto;


    public AppFrame() {
        super("Grupo 07");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel);

        criarCards();
    }

    public void mostrar() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(700, 530);

    }

    private void criarCards() {//////layout que aparece na tela principal ao iniciar o programa
        listPanel = new CandidatoListPanel(this);
        cardPanel.add(listPanel, CandidatoListPanel.class.getName());

        formPanel = new CandidatoFormPanel(this);
        cardPanel.add(formPanel, CandidatoFormPanel.class.getName());


        listPanelPesquisa = new PesquisaListPanel(this);
        cardPanel.add(listPanelPesquisa, PesquisaListPanel.class.getName());

        formPanelPesquisa = new PesquisaFormPanel(this);
        cardPanel.add(formPanelPesquisa, PesquisaFormPanel.class.getName());


        formPanelInstituto = new InstitutoFormPanel(this);
        cardPanel.add(formPanelInstituto, InstitutoFormPanel.class.getName());

        listPanelInstituto = new InstitutoListPanel(this);
        cardPanel.add(listPanelInstituto, InstitutoListPanel.class.getName());
    }





    public void mostrarFormPanel(Candidato candidato) {
        formPanel.setCandidato(candidato);
        cardLayout.show(cardPanel, CandidatoFormPanel.class.getName());
    }

    public void mostrarListPanel() {
        listPanel.recarregar();
        cardLayout.show(cardPanel, CandidatoListPanel.class.getName());
    }

    public void mostrarFormPanelPesquisa(Pesquisa pesquisa) {
        formPanelPesquisa.setPesquisa(pesquisa);
        cardLayout.show(cardPanel, PesquisaFormPanel.class.getName());
    }
    public void mostrarListPanelPesquisa() {
        listPanelPesquisa.recarregar();
        cardLayout.show(cardPanel, PesquisaListPanel.class.getName());
    }

    public void mostrarFormPanelInstituto(InstitutoDePesquisa instituto) {
        formPanelInstituto.setInstitutoDePesquisa(instituto);
        cardLayout.show(cardPanel, InstitutoFormPanel.class.getName());
    }

    public void mostrarListPanelInstituto() {
        listPanelInstituto.recarregar();
        cardLayout.show(cardPanel, InstitutoListPanel.class.getName());
    }
}
