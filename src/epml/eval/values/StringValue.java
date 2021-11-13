package epml.eval.values;

public record StringValue(String content) implements Value
{
    @Override
    public String toString()
    {
        return this.content;
    }
}
