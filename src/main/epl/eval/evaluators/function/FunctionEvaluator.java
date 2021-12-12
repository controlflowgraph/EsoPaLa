package epl.eval.evaluators.function;

import epl.eval.LanguagePattern;
import epl.eval.evaluators.Evaluator;
import epl.eval.evaluators.util.VariableLocationEvaluator;
import epl.eval.values.FunctionValue;
import epl.eval.values.Value;

import java.util.ArrayList;
import java.util.List;

@LanguagePattern(patterns = { "a function that does the following $b", "a function that receives $a (* , $a) and does the following $b" })
public class FunctionEvaluator implements Evaluator
{
    private final List<Evaluator> arguments;
    private final Evaluator body;

    public FunctionEvaluator(Evaluator body)
    {
        this.arguments = List.of();
        this.body = body;
    }

    public FunctionEvaluator(List<Evaluator> arguments, Evaluator body)
    {
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public Value evaluate()
    {
        List<String> arguments = new ArrayList<>();
        for(Evaluator evaluator : this.arguments)
        {
            if(!(evaluator instanceof VariableLocationEvaluator v)) throw new RuntimeException("Expected variables as parameters! (" + evaluator + ")");
            arguments.add(v.getVariable());
        }
        return new FunctionValue(arguments, this.body);
    }
}
