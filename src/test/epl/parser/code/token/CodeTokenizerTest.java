package epl.parser.code.token;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeTokenizerTest
{
    @Test
    void shouldTokenizeOpeningParentheses()
    {
        List<CodeToken> actual = CodeTokenizer.tokenize("(((");
        List<CodeToken> expected = List.of(
                new CodeToken("(", CodeTokenType.OPENING_PARENTHESES, 0),
                new CodeToken("(", CodeTokenType.OPENING_PARENTHESES, 1),
                new CodeToken("(", CodeTokenType.OPENING_PARENTHESES, 2)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeClosingParentheses()
    {
        List<CodeToken> actual = CodeTokenizer.tokenize(")))");
        List<CodeToken> expected = List.of(
                new CodeToken(")", CodeTokenType.CLOSING_PARENTHESES, 0),
                new CodeToken(")", CodeTokenType.CLOSING_PARENTHESES, 1),
                new CodeToken(")", CodeTokenType.CLOSING_PARENTHESES, 2)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeDots()
    {
        List<CodeToken> actual = CodeTokenizer.tokenize("...");
        List<CodeToken> expected = List.of(
                new CodeToken(".", CodeTokenType.DOT, 0),
                new CodeToken(".", CodeTokenType.DOT, 1),
                new CodeToken(".", CodeTokenType.DOT, 2)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeWords()
    {
        List<CodeToken> actual = CodeTokenizer.tokenize("a, ab, abc");
        List<CodeToken> expected = List.of(
                new CodeToken("a", CodeTokenType.WORD, 0),
                new CodeToken(",", CodeTokenType.WORD, 1),
                new CodeToken("ab", CodeTokenType.WORD, 3),
                new CodeToken(",", CodeTokenType.WORD, 5),
                new CodeToken("abc", CodeTokenType.WORD, 7)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldTokenizeNumbers()
    {
        List<CodeToken> actual = CodeTokenizer.tokenize("1 12 123");
        List<CodeToken> expected = List.of(
                new CodeToken("1", CodeTokenType.NUMBER, 0),
                new CodeToken("12", CodeTokenType.NUMBER, 2),
                new CodeToken("123", CodeTokenType.NUMBER, 5)
        );
        assertEquals(expected, actual);
    }
}