package epl.eval.values.range;

import epl.eval.values.IteratorValue;
import epl.eval.values.NumberValue;
import epl.eval.values.Value;

public class RangeValue implements Value, IteratorValue
{
    private final double start;
    private final double end;
    private final double increment;

    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    private double value;

    public RangeValue(double start, double end, double value, double increment, boolean inclusiveStart, boolean inclusiveEnd)
    {
        this.start = start;
        this.end = end;
        this.value = value;
        this.increment = increment;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }


    @Override
    public boolean hasNext()
    {
        boolean lower = this.start < this.value || this.inclusiveStart && this.value == this.start;
        boolean upper = this.end > this.value || this.inclusiveEnd && this.value == this.end;
        return lower && upper;
    }

    @Override
    public Value next()
    {
        NumberValue num = new NumberValue(this.value);
        this.value += this.increment;
        return num;
    }
}
