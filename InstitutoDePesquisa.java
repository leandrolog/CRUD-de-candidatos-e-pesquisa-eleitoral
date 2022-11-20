
public class InstitutoDePesquisa extends Candidato {

    private int idInstituto;
    private String Nome;

    private String EmpresaContratante;

    public int getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(int idInstituto) {
        this.idInstituto = idInstituto;
    }



    @Override
    public String getNome() {
        return Nome;
    }

    @Override
    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmpresaContratante() {
        return EmpresaContratante;
    }

    public void setEmpresaContratante(String empresaContratante) {
        EmpresaContratante = empresaContratante;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        InstitutoDePesquisa outroInstituto = (InstitutoDePesquisa) obj;
        return idInstituto == outroInstituto.getIdInstituto();
    }
}
