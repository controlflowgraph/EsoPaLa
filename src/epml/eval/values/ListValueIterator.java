package epml.eval.values;

import java.util.Iterator;

public class ListValueIterator implements ValueIterator
{
    private final Iterator<Value> iterator;

    public ListValueIterator(Value value)
    {
        if(value instanceof ListValue list)
        {
            this.iterator = list.values().iterator();
        }
        else
        {
            throw new RuntimeException("Unable to create 'iterator' over " + value.getClass() + "!");
        }
    }

    public Value next()
    {
        return this.iterator.next();
    }

    public boolean hasNext()
    {
        return this.iterator.hasNext();
    }
}
