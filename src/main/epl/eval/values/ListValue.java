package epl.eval.values;

import epl.eval.operations.util.Concatenation;

import java.util.ArrayList;
import java.util.List;

public record ListValue(List<Value> values) implements Value, Concatenation
{
    public ListValue()
    {
        this(new ArrayList<>());
    }

    @Override
    public Value concatenate(Value value)
    {
        if(!(value instanceof ListValue l)) throw new RuntimeException("Expected list for concatenation!");
        List<Value> concat = new ArrayList<>();
        concat.addAll(this.values);
        concat.addAll(l.values);
        return new ListValue(concat);
    }
}
