package epml.token.code;

import java.util.List;

public record GroupToken(List<Token> content) implements Token
{
    @Override
    public String toString()
    {
        return this.content.toString();
    }
}
