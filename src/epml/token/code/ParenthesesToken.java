package epml.token.code;

public record ParenthesesToken(boolean opening) implements Token
{
    @Override
    public String toString()
    {
        return this.opening ? "(" : ")";
    }
}
