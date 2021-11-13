package epml.token.pattern;

import epml.token.code.GroupToken;
import epml.token.code.Token;

public record ExpressionElement() implements Element
{
    @Override
    public boolean matches(Token token)
    {
        return token instanceof GroupToken;
    }
}
