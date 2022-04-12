package epl.parser.pattern.segment;

import epl.eval.evaluators.Evaluator;
import epl.eval.evaluators.atom.BooleanEvaluator;
import epl.eval.evaluators.atom.NumberEvaluator;
import epl.eval.evaluators.util.MultiEvaluator;
import epl.eval.evaluators.util.VariableLocationEvaluator;
import epl.parser.CodePatternMatcher;
import epl.parser.MatchingContext;
import epl.parser.code.segment.CodeSegment;
import epl.parser.code.segment.NumberCodeSegment;
import epl.parser.code.segment.SubCodeSegment;
import epl.parser.code.segment.WordCodeSegment;

import java.util.List;
import java.util.Map;

public record IdentifierPatternSegment(String identifier) implements PatternSegment
{
    @Override
    public Map<String, List<Evaluator>> matches(MatchingContext context)
    {
        context.save();
        if(!context.hasNext()) return null;
        CodeSegment next = context.getNext();
        if(next instanceof WordCodeSegment w)
        {
            List<Evaluator> evaluators;
            if(w.word().equals("true") || w.word().equals("false"))
            {
                evaluators = List.of(new BooleanEvaluator(w.word()));
            }
            else
            {
                evaluators = List.of(new VariableLocationEvaluator(context.getEnv(), w.word()));
            }
            return Map.of(this.identifier, evaluators);
        }
        if(next instanceof NumberCodeSegment n)
        {
            List<Evaluator> evaluators = List.of(new NumberEvaluator(n.number()));
            return Map.of(this.identifier, evaluators);
        }
        if(next instanceof SubCodeSegment s)
        {
            List<Evaluator> evaluators = CodePatternMatcher.getEvaluators(s.segments(), context.getUnits(), context.getEnv());
            if(evaluators.size() == 1)
            {
                return Map.of(this.identifier, evaluators);
            }
            else
            {
                return Map.of(this.identifier, List.of(new MultiEvaluator(evaluators)));
            }
        }
        context.restore();
        return null;
    }
}
