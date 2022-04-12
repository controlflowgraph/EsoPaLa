package epl.parser.pattern.segment;

import epl.eval.evaluators.Evaluator;
import epl.parser.MatchingContext;

import java.util.List;
import java.util.Map;

public interface PatternSegment
{
    Map<String, List<Evaluator>> matches(MatchingContext context);
}
