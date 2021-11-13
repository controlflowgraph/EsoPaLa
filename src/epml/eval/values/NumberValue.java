package epml.eval.values;

public record NumberValue(double value) implements Value
{
    @Override
    public String toString()
    {
        return Double.toString(this.value);
    }
}
