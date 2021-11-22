package epl.parser.pattern;

import epl.parser.pattern.segment.IdentifierPatternSegment;
import epl.parser.pattern.segment.SubPatternSegment;
import epl.parser.pattern.segment.WordPatternSegment;
import epl.parser.pattern.token.PatternToken;
import epl.parser.pattern.token.PatternTokenType;
import epl.parser.pattern.token.PatternTokenizer;
import epl.parser.util.IndexedIterator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

public class PatternParser
{
    public static PatternMatcher parse(String text)
    {
        return new PatternParser(PatternTokenizer.tokenize(text)).parse();
    }

    private final IndexedIterator<PatternToken> iterator;
    private final Stack<SubPatternSegment> subSegments = new Stack<>();
    private final LinkedHashMap<String, Integer> occurrences = new LinkedHashMap<>();
    private int repetitionPossibilities = 0;

    private PatternParser(List<PatternToken> tokens)
    {
        this.iterator = IndexedIterator.iterator(tokens);
    }

    private PatternMatcher parse()
    {
        initializeSubSegmentStack();
        parseSegments();
        return getSegments();
    }

    private void initializeSubSegmentStack()
    {
        this.subSegments.push(new SubPatternSegment(SubPatternSegment.Quantifier.EXISTENT));
    }

    private void parseSegments()
    {
        while (this.iterator.hasNext() && this.subSegments.size() > 0)
        {
            parseSegment(this.iterator.next());
        }
    }

    private void parseSegment(PatternToken token)
    {
        switch (token.type())
        {
            case OPENING_PARENTHESES -> {
                if (!this.iterator.hasNext())
                    throw new RuntimeException("Missing quantifier after opening sub pattern segment!");
                PatternToken quantifierToken = this.iterator.next();
                if (quantifierToken.type() != PatternTokenType.QUANTIFIER)
                    throw new RuntimeException("Expected quantifier after opening sub pattern segment!");
                SubPatternSegment.Quantifier quantifier = SubPatternSegment.Quantifier.getFromString(quantifierToken.token());
                SubPatternSegment segment = new SubPatternSegment(quantifier);
                this.subSegments.peek().add(segment);
                this.subSegments.push(segment);
                if (segment.quantifier() != SubPatternSegment.Quantifier.OPTIONAL) this.repetitionPossibilities++;
            }
            case IDENTIFIER -> {
                String name = token.token().substring(1);
                this.subSegments.peek().add(new IdentifierPatternSegment(name));
                increase(name);
            }
            case WORD -> this.subSegments.peek().add(new WordPatternSegment(token.token()));
            case CLOSING_PARENTHESES -> {
                SubPatternSegment segment = this.subSegments.pop();
                if (segment.quantifier() != SubPatternSegment.Quantifier.OPTIONAL) this.repetitionPossibilities--;
            }
            default -> throw new RuntimeException("Unsupported pattern token type '" + token.type() + "'!");
        }
    }

    private PatternMatcher getSegments()
    {
        if (this.subSegments.size() == 0) mismatch("many");
        if (this.subSegments.size() > 1) mismatch("few");
        return new PatternMatcher(this.subSegments.get(0).segments(), this.occurrences);
    }

    private void mismatch(String reason)
    {
        throw new RuntimeException("Mismatching sub pattern segments! Too " + reason + " closing parentheses. (c: " + this.iterator.last().position() + ")");
    }

    private void increase(String identifier)
    {
        this.occurrences.put(identifier, this.occurrences.getOrDefault(identifier, 0) + 1 + this.repetitionPossibilities);
    }
}
