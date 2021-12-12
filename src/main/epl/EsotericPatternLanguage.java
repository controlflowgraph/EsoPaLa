package epl;

import epl.eval.Environment;
import epl.eval.evaluators.Evaluator;
import epl.eval.evaluators.arithmetic.*;
import epl.eval.evaluators.comparator.*;
import epl.eval.evaluators.conjunction.AndEvaluator;
import epl.eval.evaluators.conjunction.NotEvaluator;
import epl.eval.evaluators.conjunction.OrEvaluator;
import epl.eval.evaluators.function.FunctionCallEvaluator;
import epl.eval.evaluators.function.FunctionEvaluator;
import epl.eval.evaluators.function.ReturnEvaluator;
import epl.eval.evaluators.list.ConcatenationEvaluator;
import epl.eval.evaluators.list.ListEvaluator;
import epl.eval.evaluators.range.RangeEvaluator;
import epl.eval.evaluators.util.AssignmentEvaluator;
import epl.eval.evaluators.util.ForEachEvaluator;
import epl.eval.evaluators.util.IfElseIfElseEvaluator;
import epl.eval.evaluators.util.PrintEvaluator;
import epl.parser.CodePatternMatcher;
import epl.parser.MatcherUnit;
import epl.parser.code.CodeParser;
import epl.parser.code.segment.CodeSegment;

import java.util.List;

public class EsotericPatternLanguage
{
    public static void main(String[] args)
    {
//        String text = Files.readString(Path.of("data/code.txt"));
        String text = """
                let a be equal to (a list containing 10, 20 and 30).
                let b be equal to (a list containing 40, 50, 60 and 70).
                let c be equal to (a concatenated with b).
                print(c).
                """;
        List<CodeSegment> segments = CodeParser.parse(text);

        List<Class<? extends Evaluator>> classes = List.of(
                AdditionEvaluator.class,
                DivisionEvaluator.class,
                ModuloEvaluator.class,
                MultiplicationEvaluator.class,
                SubtractionEvaluator.class,
                EqualEvaluator.class,
                GreaterEqualEvaluator.class,
                GreaterEvaluator.class,
                LessEqualEvaluator.class,
                LessEvaluator.class,
                EqualEvaluator.class,
                UnequalEvaluator.class,
                AndEvaluator.class,
                OrEvaluator.class,
                NotEvaluator.class,
                ConcatenationEvaluator.class,
                ListEvaluator.class,
                RangeEvaluator.class,
                AssignmentEvaluator.class,
                ForEachEvaluator.class,
                IfElseIfElseEvaluator.class,
                PrintEvaluator.class,
                FunctionEvaluator.class,
                FunctionCallEvaluator.class,
                ReturnEvaluator.class
        );

        List<MatcherUnit> units = MatcherUnit.getUnits(classes);
        Environment env = new Environment();
        List<Evaluator> evaluators = CodePatternMatcher.getEvaluators(segments, units, env);
        System.out.println(evaluators);
        evaluators.forEach(Evaluator::evaluate);
    }
}


