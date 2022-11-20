import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class InstitutoTableModel extends AbstractTableModel {
    private List<InstitutoDePesquisa> institutos = new ArrayList<>();
    private String[] colunas = new String[]{"Id Instituto",
            "Nome",
            "Empresa Contratante",
            };

    public InstitutoTableModel(List<InstitutoDePesquisa> institutos) {
        this.institutos = institutos;
    }

    @Override
    public int getRowCount() {
        return institutos.size();
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

        InstitutoDePesquisa instituto = institutos.get(rowIdx);

        switch (colIdx) {
            case 0:
                value = Integer.toString(instituto.getIdInstituto());
                break;
            case 1:
                value = instituto.getNome();
                break;
            case 2:
                value = instituto.getEmpresaContratante();
                break;
            default:
                System.err.printf("[ERRO] Índice de coluna inválido: %d%n",
                        colIdx);
        }

        return value;
    }

    public void carregar(List<InstitutoDePesquisa> institutos) {
        this.institutos = institutos;
        fireTableDataChanged();
    }

    public InstitutoDePesquisa getInstituto(int rowIdx) {
        InstitutoDePesquisa instituto = null;

        instituto = institutos.get(rowIdx);

        return instituto;
    }
} // fim da classe TarefaTableModel
