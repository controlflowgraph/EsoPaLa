package epml.token.code;

public record WordToken(String content) implements Token
{
    @Override
    public String toString()
    {
        return this.content;
    }
}
