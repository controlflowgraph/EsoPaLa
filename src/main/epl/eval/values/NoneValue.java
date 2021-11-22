package epl.eval.values;

public record NoneValue() implements Value
{
    @Override
    public String toString()
    {
        return "none";
    }
}
