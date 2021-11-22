package epl.parser.pattern.segment;

import epl.eval.evaluators.Evaluator;
import epl.parser.MatchingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record SubPatternSegment(List<PatternSegment> segments, Quantifier quantifier) implements PatternSegment
{
    @Override
    public Map<String, List<Evaluator>> matches(MatchingContext context)
    {
        if(!context.hasNext()) return null;
        int occurrences = 0;
        Map<String, List<Evaluator>> evaluators = new HashMap<>();
        loop:
        while(context.hasNext())
        {
            Map<String, List<Evaluator>> iteration = new HashMap<>();
            context.save();
            for(PatternSegment segment : this.segments)
            {
                Map<String, List<Evaluator>> sub = segment.matches(context);
                if(sub == null)
                {
                    context.restore();
                    break loop;
                }
                mergeMaps(iteration, sub);
            }
            mergeMaps(evaluators, iteration);
            occurrences++;
        }

        if(this.quantifier == Quantifier.OPTIONAL && occurrences > 1) return null;
        if(this.quantifier == Quantifier.EXISTENT && occurrences < 1) return null;
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

    public enum Quantifier
    {
        EXISTENT,
        ARBITRARY,
        OPTIONAL,
        ;

        public static Quantifier getFromString(String token)
        {
            return switch (token)
                    {
                        case "+" -> EXISTENT;
                        case "*" -> ARBITRARY;
                        case "?" -> OPTIONAL;
                        default -> throw new RuntimeException("Unknown token as quantifier! (" + token + ")");
                    };
        }
    }

    public SubPatternSegment(Quantifier quantifier)
    {
        this(new ArrayList<>(), quantifier);
    }

    public void add(PatternSegment segment)
    {
        this.segments.add(segment);
    }
}
