package epml.token.pattern;

import epml.token.code.Token;
import epml.token.code.WordToken;

public record WordElement(String content) implements Element
{
    @Override
    public boolean matches(Token token)
    {
        return token instanceof WordToken w && w.content().equals(this.content);
    }

    @Override
    public String toString()
    {
        return this.content;
    }
}
