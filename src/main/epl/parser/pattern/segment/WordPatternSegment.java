package epl.parser.pattern.segment;

import epl.eval.evaluators.Evaluator;
import epl.parser.MatchingContext;
import epl.parser.code.segment.WordCodeSegment;

import java.util.List;
import java.util.Map;

public record WordPatternSegment(String word) implements PatternSegment
{
    private static final Map<String, List<Evaluator>> DEFAULT = Map.of();

    @Override
    public Map<String, List<Evaluator>> matches(MatchingContext context)
    {
        if(!context.hasNext()) return null;
        if(!(context.peekNext() instanceof WordCodeSegment w)) return null;
        if(!w.word().equals(this.word)) return null;
        context.getNext();
        return DEFAULT;
    }
}
