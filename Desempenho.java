
public class Desempenho extends Candidato {

    private int idDesempenho;
    private String Nome;

    private String Data;

    private String IntencaoDeVoto;


    private int IdCandidato;





    @Override
    public String getNome() {
        return Nome;
    }

    @Override
    public void setNome(String nome) {
        Nome = nome;
    }

    public int getIdCandidato() {
        return IdCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        IdCandidato = idCandidato;
    }

    public int getIdDesempenho() {
        return idDesempenho;
    }

    public void setIdDesempenho(int idDesempenho) {
        this.idDesempenho = idDesempenho;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getIntencaoDeVoto() {
        return IntencaoDeVoto;
    }

    public void setIntencaoDeVoto(String intencaoDeVoto) {
        IntencaoDeVoto = intencaoDeVoto;
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

        Desempenho desempenho = (Desempenho) obj;
        return idDesempenho == desempenho.getIdDesempenho();
    }
}
