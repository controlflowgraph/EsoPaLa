package epl.parser;

import epl.eval.*;
import epl.eval.evaluators.Evaluator;
import epl.eval.evaluators.util.MultiEvaluator;
import epl.eval.evaluators.atom.NumberEvaluator;
import epl.eval.evaluators.util.VariableLocationEvaluator;
import epl.parser.code.segment.*;
import epl.parser.util.IndexedIterator;

import java.util.ArrayList;
import java.util.List;

public class CodePatternMatcher
{
    public static List<Evaluator> getEvaluators(List<CodeSegment> segments, List<MatcherUnit> units, Environment env)
    {
        return getEvaluators(new MatchingContext(IndexedIterator.iterator(segments), units, env));
    }

    public static List<Evaluator> getEvaluators(MatchingContext context)
    {
        return new CodePatternMatcher(context).match();
    }

    private final MatchingContext context;


    private CodePatternMatcher(MatchingContext context)
    {
        this.context = context;
    }

    private List<Evaluator> match()
    {
        List<Evaluator> evaluators = new ArrayList<>();
        while(this.context.hasNext())
        {
            Evaluator match = getMatch();
            evaluators.add(match);
        }
        return evaluators;
    }

    private Evaluator getMatch()
    {
        for(MatcherUnit unit : this.context.getUnits())
        {
            this.context.save();
            Evaluator evaluator = unit.matches(this.context);
            if(evaluator != null)
            {
                this.context.discard();
                if(this.context.hasNext() && this.context.peekNext() instanceof TerminationCodeSegment) this.context.getNext();
                else if(!(evaluator instanceof MultiEvaluator) && this.context.hasNext()) throw new RuntimeException("ERROR: Expected '.' after expression! (" + evaluator.getClass().getName() + ")");
                return evaluator;
            }
            this.context.restore();
        }

        if(this.context.getLength() == 1)
        {
            return getSimpleEvaluator(this.context.getNext());
        }
        else
        {
            List<Evaluator> evaluators = new ArrayList<>();
            while(this.context.hasNext())
            {
                Evaluator evaluator = getSimpleEvaluator(this.context.getNext());
                evaluators.add(evaluator);
            }
            return new MultiEvaluator(evaluators);
        }
    }

    private Evaluator getSimpleEvaluator(CodeSegment segment)
    {
        if(segment instanceof WordCodeSegment s)
        {
            return new VariableLocationEvaluator(this.context.getEnv(), s.word());
        }
        if(segment instanceof NumberCodeSegment n)
        {
            return new NumberEvaluator(n.number());
        }
        if(segment instanceof SubCodeSegment s)
        {
            return getMultiEvaluator(s);
        }
        throw new RuntimeException("Unknown pattern! (" + segment + ")");
    }

    private MultiEvaluator getMultiEvaluator(SubCodeSegment segment)
    {
        return getMultiEvaluator(segment.segments());
    }

    private MultiEvaluator getMultiEvaluator(List<CodeSegment> segments)
    {
        List<Evaluator> evaluators = new ArrayList<>();
        for(CodeSegment sub : segments)
        {
            if(sub instanceof WordCodeSegment a)
            {
                evaluators.add(new VariableLocationEvaluator(this.context.getEnv(), a.word()));
            }
            else if(sub instanceof NumberCodeSegment a)
            {
                evaluators.add(new NumberEvaluator(a.number()));
            }
            else if(sub instanceof SubCodeSegment a)
            {
                evaluators.add(new MultiEvaluator(CodePatternMatcher.getEvaluators(a.segments(), this.context.getUnits(), this.context.getEnv())));
            }
        }
        return new MultiEvaluator(evaluators);
    }
}
