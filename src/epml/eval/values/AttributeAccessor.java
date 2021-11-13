package epml.eval.values;

public class AttributeAccessor implements Accessor
{
    private final String attribute;
    private final ObjectValue source;

    public AttributeAccessor(String attribute, ObjectValue source)
    {
        this.attribute = attribute;
        this.source = source;
    }



    @Override
    public Value get()
    {
        return this.source.getAttribute(this.attribute);
    }

    @Override
    public void set(Value value)
    {
        this.source.setAttribute(this.attribute, value);
    }
}
