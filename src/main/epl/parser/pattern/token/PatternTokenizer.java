package epl.parser.pattern.token;

import epl.parser.util.TokenCategory;
import epl.parser.util.TokenizerFactory;

import java.util.List;
import java.util.regex.Pattern;

public class PatternTokenizer
{
    private static final TokenizerFactory<PatternToken, PatternTokenType> FACTORY = new TokenizerFactory<>(
            List.of(
                    new TokenCategory<>(Pattern.compile("(\\()"), PatternTokenType.OPENING_PARENTHESES),
                    new TokenCategory<>(Pattern.compile("(\\))"), PatternTokenType.CLOSING_PARENTHESES),
                    new TokenCategory<>(Pattern.compile("(\\$[a-zA-Z0-9]+)"), PatternTokenType.IDENTIFIER),
                    new TokenCategory<>(Pattern.compile("([a-zA-Z][a-zA-Z0-9]*)"), PatternTokenType.WORD),
                    new TokenCategory<>(Pattern.compile("([+*?])"), PatternTokenType.QUANTIFIER),
                    new TokenCategory<>(Pattern.compile("([^ \t\r\n])"), PatternTokenType.WORD)
            ),
            new PatternTokenFactory()
    );

    public static List<PatternToken> tokenize(String text)
    {
        return FACTORY.create(text).tokenize();
    }
}
