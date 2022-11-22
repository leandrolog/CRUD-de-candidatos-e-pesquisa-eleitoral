import java.util.Date;

public class Pesquisa extends Candidato {

    private int idPesquisa;
    private String intencaoDeVoto;
    private String data;
    private String turno;
    private String TipoDePesquisa;

    private int idInstitutoProv;


    public int getIdInstitutoProv() {
        return idInstitutoProv;
    }

    public void setIdInstitutoProv(int idInstitutoProv) {
        this.idInstitutoProv = idInstitutoProv;
    }

    public int getIdPesquisa() {
        return idPesquisa;
    }

    public void setIdPesquisa(int idPesquisa) {
        this.idPesquisa = idPesquisa;
    }

    public String getIntencaoDeVoto() {
        return intencaoDeVoto;
    }

    public void setIntencaoDeVoto(String intencaoDeVoto) {
        this.intencaoDeVoto = intencaoDeVoto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTipoDepesquisa() {
        return TipoDePesquisa;
    }

    public void setTipoDepesquisa(String tipoDepesquisa) {
        TipoDePesquisa = tipoDepesquisa;
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

        Pesquisa outraPesquisa = (Pesquisa) obj;
        return idPesquisa == outraPesquisa.getIdPesquisa();
    }
}
