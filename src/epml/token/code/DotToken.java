package epml.token.code;

public record DotToken() implements Token
{
    @Override
    public String toString()
    {
        return ".";
    }
}
