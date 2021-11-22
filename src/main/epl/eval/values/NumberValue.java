package epl.eval.values;

public record NumberValue(double number) implements Value
{
    @Override
    public String toString()
    {
        return Double.toString(this.number);
    }
}
