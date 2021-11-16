package epl.parser.code;

import epl.parser.code.segment.*;
import epl.parser.code.token.CodeToken;
import epl.parser.code.token.CodeTokenizer;
import epl.parser.util.IndexedIterator;

import java.util.List;
import java.util.Stack;

public class CodeParser
{
    public static List<CodeSegment> parse(String text)
    {
        return new CodeParser(CodeTokenizer.tokenize(text)).parse();
    }

    private final IndexedIterator<CodeToken> iterator;
    private final Stack<SubCodeSegment> subSegments = new Stack<>();

    private CodeParser(List<CodeToken> tokens)
    {
        this.iterator = IndexedIterator.iterator(tokens);
    }

    private List<CodeSegment> parse()
    {
        initializeSubSegmentStack();
        parseSegments();
        return getSegments();
    }

    private void initializeSubSegmentStack()
    {
        this.subSegments.push(new SubCodeSegment());
    }

    private void parseSegments()
    {
        while(this.iterator.hasNext() && this.subSegments.size() > 0)
        {
            parseToken(this.iterator.next());
        }
    }

    private void parseToken(CodeToken token)
    {
        switch (token.type())
        {
            case OPENING_PARENTHESES ->
                    {
                        SubCodeSegment segment = new SubCodeSegment();
                        this.subSegments.peek().add(segment);
                        this.subSegments.push(segment);
                    }
            case CLOSING_PARENTHESES -> this.subSegments.pop();
            case WORD -> this.subSegments.peek().add(new WordCodeSegment(token.token(), token.position()));
            case NUMBER -> this.subSegments.peek().add(new NumberCodeSegment(token.token(), token.position()));
            case DOT -> this.subSegments.peek().add(new TerminationCodeSegment(token.position()));
            default -> throw new RuntimeException("Unsupported code token type '" + token.type() + "'!");
        }
    }

    private List<CodeSegment> getSegments()
    {
        if(this.subSegments.size() == 0) mismatch("many");
        if(this.subSegments.size() > 1) mismatch("few");
        return this.subSegments.get(0).segments();
    }

    private void mismatch(String reason)
    {
        throw new RuntimeException("Mismatching sub code segments! Too " + reason + " closing parentheses. (c: " + this.iterator.last().position() + ")");
    }
}
