package epl.parser.pattern.token;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatternTokenizerTest
{
    @Test
    void shouldTokenizeOpeningParentheses()
    {
        List<PatternToken> actual = PatternTokenizer.tokenize("(((");
        List<PatternToken> expected = List.of(
                new PatternToken("(", PatternTokenType.OPENING_PARENTHESES, 0),
                new PatternToken("(", PatternTokenType.OPENING_PARENTHESES, 1),
                new PatternToken("(", PatternTokenType.OPENING_PARENTHESES, 2)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeClosingParentheses()
    {
        List<PatternToken> actual = PatternTokenizer.tokenize(")))");
        List<PatternToken> expected = List.of(
                new PatternToken(")", PatternTokenType.CLOSING_PARENTHESES, 0),
                new PatternToken(")", PatternTokenType.CLOSING_PARENTHESES, 1),
                new PatternToken(")", PatternTokenType.CLOSING_PARENTHESES, 2)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeWords()
    {
        List<PatternToken> actual = PatternTokenizer.tokenize("a, ab, abc");
        List<PatternToken> expected = List.of(
                new PatternToken("a", PatternTokenType.WORD, 0),
                new PatternToken(",", PatternTokenType.WORD, 1),
                new PatternToken("ab", PatternTokenType.WORD, 3),
                new PatternToken(",", PatternTokenType.WORD, 5),
                new PatternToken("abc", PatternTokenType.WORD, 7)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeIdentifiers()
    {
        List<PatternToken> actual = PatternTokenizer.tokenize("$a $1 $a1");
        List<PatternToken> expected = List.of(
                new PatternToken("$a", PatternTokenType.IDENTIFIER, 0),
                new PatternToken("$1", PatternTokenType.IDENTIFIER, 3),
                new PatternToken("$a1", PatternTokenType.IDENTIFIER, 6)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeQuantifiers()
    {
        List<PatternToken> actual = PatternTokenizer.tokenize("+*?");
        List<PatternToken> expected = List.of(
                new PatternToken("+", PatternTokenType.QUANTIFIER, 0),
                new PatternToken("*", PatternTokenType.QUANTIFIER, 1),
                new PatternToken("?", PatternTokenType.QUANTIFIER, 2)
        );
        assertEquals(expected, actual);
    }
}