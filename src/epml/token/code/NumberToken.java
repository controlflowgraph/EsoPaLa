package epml.token.code;

public record NumberToken(String number) implements Token
{
    @Override
    public String toString()
    {
        return this.number;
    }
}
