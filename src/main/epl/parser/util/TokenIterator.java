package epl.parser.util;

import java.util.List;

public class TokenIterator <TokenClass extends Token<TokenType>, TokenType>
{
    public static <TokenClass extends Token<TokenType>, TokenType> TokenIterator<TokenClass, TokenType> iterator(List<TokenClass> tokens)
    {
        return new TokenIterator<>(tokens);
    }

    private int index;
    private final int length;
    private final List<TokenClass> tokens;

    private TokenIterator(List<TokenClass> tokens)
    {
        this.length = tokens.size();
        this.tokens = tokens;
    }

    public boolean hasNext()
    {
        return this.index < this.length;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return this.index;
    }

    public TokenClass next()
    {
        return this.tokens.get(this.index++);
    }

    public TokenClass peek()
    {
        return this.tokens.get(this.index);
    }

    public TokenClass last()
    {
        return this.tokens.get(this.index - 1);
    }
}
