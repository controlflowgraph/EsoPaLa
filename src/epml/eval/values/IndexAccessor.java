package epml.eval.values;

public class IndexAccessor implements Accessor
{
    private final ListValue list;
    private final NumberValue value;

    public IndexAccessor(ListValue list, NumberValue value)
    {
        this.list = list;
        this.value = value;
    }

    @Override
    public Value get()
    {
        return this.list.values().get((int) this.value.value());
    }

    @Override
    public void set(Value value)
    {
        this.list.values().set((int) this.value.value(), value);
    }
}
