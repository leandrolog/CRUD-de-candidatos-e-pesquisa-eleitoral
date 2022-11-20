import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PesquisaTableModel extends AbstractTableModel {
    private List<Pesquisa> pesquisas = new ArrayList<>();
    private String[] colunas = new String[]{"Id Pesquisa",
            "Intenção de Voto",
            "Data",
            "Turno",
            "Tipo de Pesquisa"};

    public PesquisaTableModel(List<Pesquisa> pesquisas) {
        this.pesquisas = pesquisas;
    }

    @Override
    public int getRowCount() {
        return pesquisas.size();
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

        Pesquisa pesquisa = pesquisas.get(rowIdx);

        switch (colIdx) {
            case 0:
                value = Integer.toString(pesquisa.getIdPesquisa());
                break;
            case 1:
                value = pesquisa.getIntencaoDeVoto();
                break;
            case 2:
                value = pesquisa.getData();
                break;
            case 3:
                value = pesquisa.getTurno();
                break;
            case 4:
                value = pesquisa.getTipoDepesquisa();
                break;

            default:
                System.err.printf("[ERRO] Índice de coluna inválido: %d%n",
                        colIdx);
        }

        return value;
    }

    public void carregar(List<Pesquisa> pesquisas) {
        this.pesquisas = pesquisas;
        fireTableDataChanged();
    }

    public Pesquisa getPesquisa(int rowIdx) {
        Pesquisa pesquisa = null;

        pesquisa = pesquisas.get(rowIdx);

        return pesquisa;
    }
} // fim da classe TarefaTableModel
