package epml.eval.values;

public class RangeValue implements Value, ValueIterable
{
    private final double start;
    private final double end;
    private final double increment;

    public RangeValue(double start, double end, double increment)
    {
        this.start = start;
        this.end = end;
        this.increment = increment;
    }

    @Override
    public ValueIterator iterator()
    {
        return new RangeValueIterator(this.start, this.end, this.increment);
    }

    @Override
    public String toString()
    {
        return "[" + this.start + "->" + this.end + " i:" + this.increment + ")";
    }
}
