import java.util.List;

public class ListExtension {
    public static <T> String listToString(List<T> list) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            T t = list.get(i);
            string.append(i + 1).append(": ").append(t).append(";");
            if (i != list.size() - 1)
                string.append("\n");
        }
        return string.toString();
    }
}
