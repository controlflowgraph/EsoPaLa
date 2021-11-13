package epml.eval.values;

public interface Accessor extends Value
{
    Value get();

    void set(Value value);
}
