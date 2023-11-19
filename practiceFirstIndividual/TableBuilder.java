import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableBuilder<V, E> {
    private List<E> rowTags;
    private List<E> columnTags;
    private List<List<V>> matrix;

    public TableBuilder() {
        rowTags = new ArrayList<>();
        columnTags = new ArrayList<>();
        matrix = new ArrayList<>();
    }

    public void addRow(List<V> row) {
        matrix.add(row);
    }

    public void set(int rowIndex, int columnIndex, V el) throws IndexOutOfBoundsException {
        matrix.get(rowIndex).set(columnIndex, el);
    }

    public Table<V, E> build() throws IllegalArgumentException {
        return new Table<V, E>(matrix, rowTags, columnTags);
    }

    public List<E> getRowTags() {
        return Collections.unmodifiableList(rowTags);
    }

    public void setRowTags(List<E> rowTags) {
        this.rowTags = rowTags;
    }

    public List<E> getColumnTags() {
        return Collections.unmodifiableList(columnTags);
    }

    public void setColumnTags(List<E> columnTags) {
        this.columnTags = columnTags;
    }

    public List<List<V>> getMatrix() {
        return Collections.unmodifiableList(matrix);
    }

    public void setMatrix(List<List<V>> matrix) {
        this.matrix = matrix;
    }
}
