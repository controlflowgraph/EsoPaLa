package epl.parser.code.token;

import epl.parser.util.TokenCategory;
import epl.parser.util.TokenizerFactory;

import java.util.List;
import java.util.regex.Pattern;

public class CodeTokenizer
{
    private static final TokenizerFactory<CodeToken, CodeTokenType> FACTORY = new TokenizerFactory<>(
        List.of(
            new TokenCategory<>(Pattern.compile("(\\()"), CodeTokenType.OPENING_PARENTHESES),
            new TokenCategory<>(Pattern.compile("(\\))"), CodeTokenType.CLOSING_PARENTHESES),
            new TokenCategory<>(Pattern.compile("(\\.)"), CodeTokenType.DOT),
            new TokenCategory<>(Pattern.compile("([0-9]+(\\.[0-9]+)?)"), CodeTokenType.NUMBER),
            new TokenCategory<>(Pattern.compile("([a-zA-Z][a-zA-Z0-9]*)"), CodeTokenType.WORD),
            new TokenCategory<>(Pattern.compile("([^ \t\r\n])"), CodeTokenType.WORD)
        ),
        new CodeTokenFactory()
    );

    public static List<CodeToken> tokenize(String text)
    {
        return FACTORY.create(text).tokenize();
    }
}
