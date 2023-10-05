import java.util.Comparator;

public class PerformanceLearnerComparator<T extends Learner> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return Double.compare(o1.getPerformance(), o2.getPerformance());
    }
}
