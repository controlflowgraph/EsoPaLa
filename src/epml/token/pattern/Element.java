package epml.token.pattern;

import epml.token.code.Token;

public interface Element
{
    boolean matches(Token token);
}
