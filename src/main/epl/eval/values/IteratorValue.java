package epl.eval.values;

public interface IteratorValue
{
    boolean hasNext();

    Value next();
}
