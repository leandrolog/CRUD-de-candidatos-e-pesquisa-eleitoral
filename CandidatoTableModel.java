import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CandidatoTableModel extends AbstractTableModel {
    private List<Candidato> candidatos = new ArrayList<>();
    private String[] colunas = new String[]{"Id",
            "Nome",
            "Partido"};

    public CandidatoTableModel(List<Candidato> tarefas) {
        this.candidatos = candidatos;
    }

    @Override
    public int getRowCount() {
        return candidatos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int colIdx) {
        String colName = null;

        colName = colunas[colIdx];

        return colName;
    }

    @Override
    public Object getValueAt(int rowIdx, int colIdx) {
        String value = null;

        Candidato candidato = candidatos.get(rowIdx);

        switch (colIdx) {
            case 0:
                value = Integer.toString(candidato.getId());
                break;
            case 1:
                value = candidato.getNome();
                break;
            case 2:
                value = candidato.getPartido();
                break;
            default:
                System.err.printf("[ERRO] Índice de coluna inválido: %d%n",
                        colIdx);
        }

        return value;
    }

    public void carregar(List<Candidato> candidatos) {
        this.candidatos = candidatos;
        fireTableDataChanged();
    }

    public Candidato getCandidato(int rowIdx) {
        Candidato candidato = null;

        candidato = candidatos.get(rowIdx);

        return candidato;
    }
} // fim da classe TarefaTableModel
