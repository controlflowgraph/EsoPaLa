package epl.parser.util;

import java.util.List;

public class TokenizerFactory<TokenClass extends Token<TokenType>, TokenType>
{
    private final List<TokenCategory<TokenType>> categories;
    private final TokenFactory<TokenClass, TokenType> factory;

    public TokenizerFactory(List<TokenCategory<TokenType>> categories, TokenFactory<TokenClass, TokenType> factory)
    {
        this.categories = categories;
        this.factory = factory;
    }

    public Tokenizer<TokenClass, TokenType> create(String text)
    {
        return new Tokenizer<>(this.categories, this.factory, text);
    }
}
