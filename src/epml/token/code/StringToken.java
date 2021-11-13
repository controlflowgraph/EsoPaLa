package epml.token.code;

public record StringToken(String content) implements Token
{
    @Override
    public String toString()
    {
        return this.content;
    }
}
