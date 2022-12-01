import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DesempenhoTableModel extends AbstractTableModel {
    private List<Desempenho> desempenhos = new ArrayList<>();
    private String[] colunas = new String[]{"Id Desempenho",
            "Candidato",
            "Intencao De Voto",
            "Data",

    };

    public DesempenhoTableModel(List<Desempenho> desempenhos) {
        this.desempenhos = desempenhos;
    }

    @Override
    public int getRowCount() {
        return desempenhos.size();
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

        Desempenho desempenho = desempenhos.get(rowIdx);

        switch (colIdx) {
            case 0:
                value = Integer.toString(desempenho.getIdDesempenho());
                break;
            case 1:
                value = desempenho.getNome();
                break;
            case 2:
                value = desempenho.getIntencaoDeVoto();
                break;
            case 3:
                value = desempenho.getData();
                break;
            default:
                System.err.printf("[ERRO] Índice de coluna inválido: %d%n",
                        colIdx);
        }

        return value;
    }

    public void carregar(List<Desempenho> desempenhos) {
        this.desempenhos = desempenhos;
        fireTableDataChanged();
    }

    public Desempenho getDesempenho(int rowIdx) {
        Desempenho desempenho = null;

        desempenho = desempenhos.get(rowIdx);

        return desempenho;
    }
}
