package epl.parser.pattern;

import epl.eval.evaluators.Evaluator;
import epl.parser.MatchingContext;
import epl.parser.pattern.segment.PatternSegment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record PatternMatcher(List<PatternSegment> segments, Map<String, Integer> occurrences)
{
    public Map<String, List<Evaluator>> matches(MatchingContext context)
    {
        context.save();
        Map<String, List<Evaluator>> evaluators = new HashMap<>();
        for(PatternSegment segment : this.segments)
        {
            Map<String, List<Evaluator>> subs = segment.matches(context);
            if(subs == null)
            {
                context.restore();
                return null;
            }
            mergeMaps(evaluators, subs);
        }
        return evaluators;
    }

    private void mergeMaps(Map<String, List<Evaluator>> base, Map<String, List<Evaluator>> delta)
    {
        for(Map.Entry<String, List<Evaluator>> entry : delta.entrySet())
        {
            List<Evaluator> evaluators = base.getOrDefault(entry.getKey(), new ArrayList<>());
            evaluators.addAll(entry.getValue());
            base.put(entry.getKey(), evaluators);
        }
    }
}
