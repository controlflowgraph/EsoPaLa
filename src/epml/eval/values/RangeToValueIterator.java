package epml.eval.values;

public class RangeToValueIterator implements ValueIterator
{
    private double index;
    private final double start;
    private final double end;
    private final double increment;

    public RangeToValueIterator(double start, double end, double increment)
    {
        this.start = start;
        this.end = end;
        this.increment = increment;
        this.index = start;
    }

    @Override
    public Value next()
    {
        double value = this.index;
        this.index += this.increment;
        return new NumberValue(value);
    }

    @Override
    public boolean hasNext()
    {
        return this.start <= this.index && this.index < this.end;
    }
}
