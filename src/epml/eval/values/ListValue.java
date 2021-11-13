package epml.eval.values;

import java.util.List;

public record ListValue(List<Value> values) implements Value, ValueIterable
{
    @Override
    public String toString()
    {
        return this.values.toString();
    }

    @Override
    public ValueIterator iterator()
    {
        return new ListValueIterator(this);
    }
}
