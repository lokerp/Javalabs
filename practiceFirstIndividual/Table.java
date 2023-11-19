import java.util.*;

public class Table <V, E> {
    private final HashMap<E, Integer> rowTags;
    private final HashMap<E, Integer> columnTags;
    private List<E> orderedRowTags = null;
    private List<E> orderedColumnTags = null;
    private final List<List<V>> matrix;
    public Table(List<List<V>> matrix, List<E> rowTags, List<E> columnTags) throws IllegalArgumentException {
        if (matrix.isEmpty() || matrix.size() != rowTags.size() || matrix.size() != columnTags.size())
            throw new IllegalArgumentException("Wrong sizes");
        for (List<V> r : matrix)
            if (r.size() != matrix.size())
                throw new IllegalArgumentException("Wrong sizes");

        this.rowTags = new HashMap<>();
        this.columnTags = new HashMap<>();
        this.matrix = matrix;

        for (int i = 0; i < rowTags.size(); i++) {
            this.rowTags.put(rowTags.get(i), i);
            this.columnTags.put(columnTags.get(i), i);
        }
        if (this.rowTags.size() != rowTags.size())
            throw new IllegalArgumentException("Row tags are repeating");
        if (this.rowTags.size() != columnTags.size())
            throw new IllegalArgumentException("Column tags are repeating");
    }

    public V get(E rowTag, E columnTag) throws IllegalArgumentException {
        int rowIndex = rowTags.getOrDefault(rowTag, -1);
        int columnIndex = rowTags.getOrDefault(columnTag, -1);
        if (rowIndex == -1 || columnIndex == -1)
            throw new IllegalArgumentException("Incorrect row tag or column tag");

        return matrix.get(rowIndex).get(columnIndex);
    }

    public List<V> getRow(E rowTag) throws IllegalArgumentException {
        int rowIndex = rowTags.getOrDefault(rowTag, -1);
        if (rowIndex == -1)
            throw new IllegalArgumentException("Incorrect row tag or column tag");
        return matrix.get(rowIndex);
    }

    public List<V> getColumn(E columnTag) throws IllegalArgumentException {
        int columnIndex = columnTags.getOrDefault(columnTag, -1);
        if (columnIndex == -1)
            throw new IllegalArgumentException("Incorrect row tag or column tag");
        List<V> column = new ArrayList<>(matrix.size());
        for (int i = 0; i < matrix.size(); i++)
            column.add(matrix.get(i).get(columnIndex));
        return column;
    }

    public List<E> getRowTags() {
        if (orderedRowTags != null)
            return orderedRowTags;
        List<E> rowTagsList = new ArrayList<>(matrix.size());
        for (int i = 0; i < matrix.size(); i++)
            rowTagsList.add(i, null);
        var rowsAndIndexes = rowTags.entrySet().stream().toList();
        for (int i = 0; i < matrix.size(); i++)
            rowTagsList.set(rowsAndIndexes.get(i).getValue(), rowsAndIndexes.get(i).getKey());
        orderedRowTags = rowTagsList;
        return orderedRowTags;
    }

    public List<E> getColumnTags() {
        if (orderedColumnTags != null)
            return orderedColumnTags;
        List<E> columnTagsList = new ArrayList<>(matrix.size());
        for (int i = 0; i < matrix.size(); i++)
            columnTagsList.add(i, null);
        var columnsAndIndexes = columnTags.entrySet().stream().toList();
        for (int i = 0; i < matrix.size(); i++)
            columnTagsList.set(columnsAndIndexes.get(i).getValue(), columnsAndIndexes.get(i).getKey());
        orderedColumnTags = columnTagsList;
        return orderedColumnTags;
    }

    public List<List<V>> getMatrix() {
        return matrix;
    }

    public int getSize() {
        return matrix.size();
    }
}
