package epml.eval.values;

public interface Value
{
    default <T> T as(Class<T> c)
    {
        return c.cast(this);
    }

    default Value val()
    {
        return this instanceof Accessor a ? a.get() : this;
    }

    default <T> T val(Class<T> c)
    {
        return c.cast(val());
    }
}
