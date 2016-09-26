import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by lukelee on 9/25/16.
 */
public class CompoundComparator<ValType> implements Comparator<ValType>{

    ArrayList<Comparator<ValType>> _comparators;

    public CompoundComparator() { _comparators = new ArrayList<Comparator<ValType>>(); }

    public void addComparator( Comparator<ValType> comparator ) {
        _comparators.add( comparator );
    }

    /**
     * Use a list of comparators to determine whether objects are equal.
     * Equality is defined as all comparators returning zero.
     */
    @Override
    public int compare( ValType v1, ValType v2 ) {
        // Iterate over all comparators
        // If one reports a non-zero value, exit - objects v1 and v2 are not equal
        for( Comparator<ValType> comparator : _comparators ) {
            int comparison = comparator.compare( v1, v2 );
            if( comparison != 0 )
                return comparison;
        }
        // All of the comparators returned zero - The objects are equal
        return 0;
    }
}
