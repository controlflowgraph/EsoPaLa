package epl.parser.code;

import epl.parser.code.segment.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeParserTest
{
    @Test
    void shouldParseSimpleExpression()
    {
        String text = "hello (world) a simple expression.";
        List<CodeSegment> actual = CodeParser.parse(text);
        List<CodeSegment> expected = List.of(
                new WordCodeSegment("hello", 0),
                new SubCodeSegment(List.of(
                        new WordCodeSegment("world", 7)
                )),
                new WordCodeSegment("a", 14),
                new WordCodeSegment("simple", 16),
                new WordCodeSegment("expression", 23),
                new TerminationCodeSegment(33)
        );
        assertEquals(expected, actual);
    }

    @Test
    void shouldParseComplexExpression()
    {
        String text = "hello (world) a ((10)) simple (((10, 20))) expression.";
        List<CodeSegment> actual = CodeParser.parse(text);
        List<CodeSegment> expected = List.of(
                new WordCodeSegment("hello", 0),
                new SubCodeSegment(List.of(
                        new WordCodeSegment("world", 7)
                )),
                new WordCodeSegment("a", 14),
                new SubCodeSegment(List.of(
                        new SubCodeSegment(List.of(
                                new NumberCodeSegment("10", 18)
                        ))
                )),
                new WordCodeSegment("simple", 23),
                new SubCodeSegment(List.of(
                        new SubCodeSegment(List.of(
                                new SubCodeSegment(List.of(
                                        new NumberCodeSegment("10", 33),
                                        new WordCodeSegment(",", 35),
                                        new NumberCodeSegment("20", 37)
                                ))
                        ))
                )),
                new WordCodeSegment("expression", 43),
                new TerminationCodeSegment(53)
        );
        assertEquals(expected, actual);
    }
}