package epml;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.MultiLanguagePattern;
import epml.eval.SingleLanguagePattern;
import epml.eval.evaluators.arithmetic.*;
import epml.eval.evaluators.bool.AndEvaluator;
import epml.eval.evaluators.bool.BooleanEvaluator;
import epml.eval.evaluators.comparator.*;
import epml.eval.evaluators.functions.*;
import epml.eval.evaluators.list.*;
import epml.eval.evaluators.string.RepeatStringEvaluator;
import epml.eval.evaluators.string.RepeatedStringEvaluator;
import epml.eval.evaluators.string.StringEvaluator;
import epml.eval.evaluators.util.*;
import epml.eval.evaluators.util.random.RandomNumberEvaluator;
import epml.eval.evaluators.util.random.RandomNumberInRangeEvaluator;
import epml.eval.evaluators.util.range.RangeToEvaluator;
import epml.eval.evaluators.util.range.RangeToWithIncrementEvaluator;
import epml.eval.evaluators.util.range.RangeUntilEvaluator;
import epml.eval.evaluators.util.range.RangeUntilWithIncrementEvaluator;
import epml.eval.evaluators.util.structs.*;
import epml.token.code.*;
import epml.token.pattern.PatternExpression;
import epml.token.pattern.PatternTokenizer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// TODO: redo everything ;)
// TODO: add booleans
// TODO: change from (annotated) class to (annotated) function
// TODO: add multi patterns
// TODO: add simple repetition to the patterns
//  (if a placeholder is mentioned multiple times or in a repetition it is passed to the evaluator as a list)

public class Main
{
    private static final List<Class<? extends Evaluator>> EVALUATORS = List.of(
        AssignmentEvaluator.class,
        MultiplicationEvaluator.class,
        DivisionEvaluator.class,
        AdditionEvaluator.class,
        SubtractionEvaluator.class,
        PrintEvaluator.class,
        EmptyListEvaluator.class,
        SingletonListEvaluator.class,
        DualListEvaluator.class,
        AppendEvaluator.class,
        AppendAllEvaluator.class,
        ConcatenationEvaluator.class,
        ForEachEvaluator.class,
        RangeToEvaluator.class,
        RangeToWithIncrementEvaluator.class,
        RangeUntilEvaluator.class,
        RangeUntilWithIncrementEvaluator.class,
        IsEvenEvaluator.class,
        ListFilterEvaluator.class,
        TakeWhileWhereEvaluator.class,
        LessThanEvaluator.class,
        ListSelectorEvaluator.class,
        ListHeadSelectorEvaluator.class,
        SetVariableEvaluator.class,
        RepeatStringEvaluator.class,
        RepeatedStringEvaluator.class,
        AttributeAccessorEvaluator.class,
        ObjectCreationEvaluator.class,
        FunctionDefinitionEvaluator.class,
        CalledWithEvaluator.class,
        ReturnEvaluator.class,
        ResultOfEvaluator.class,
        IndexEvaluator.class,
        RandomNumberEvaluator.class,
        IfEvaluator.class,
        GreaterThanEvaluator.class,
        DivisibleByEvaluator.class,
        IsEqualToEvaluator.class,
        CallEvaluator.class,
        RandomNumberInRangeEvaluator.class,
        AppendAfterEvaluator.class,
        ListLengthEvaluator.class,
        SwapElementEvaluator.class,
        FloorEvaluator.class,
        WhileEvaluator.class,
        AndEvaluator.class,
        IfElseEvaluator.class,
        SublistEvaluator.class,
        DoWhileDoEvaluator.class
    );

    public static void main(String[] args) throws IOException
    {
        List<PatternExpression> expressions = new ArrayList<>();
        List<Constructor<?>> constructors = new ArrayList<>();

        for (Class<? extends Evaluator> evaluator : EVALUATORS)
        {
            if(evaluator.isAnnotationPresent(SingleLanguagePattern.class))
            {
                SingleLanguagePattern annotation = evaluator.getAnnotation(SingleLanguagePattern.class);
                String pattern = annotation.pattern();
                expressions.add(PatternTokenizer.tokenize(pattern));
            }
            else if(evaluator.isAnnotationPresent(MultiLanguagePattern.class))
            {
                MultiLanguagePattern annotation = evaluator.getAnnotation(MultiLanguagePattern.class);
                String[] patterns = annotation.patterns();
                for(String pattern : patterns)
                {
                    expressions.add(PatternTokenizer.tokenize(pattern));
                }
            }
            else
            {
                throw new RuntimeException("Invalid language (not annotated with SingleLanguagePattern or MultiLanguagePattern) " + evaluator.getName());
            }

            Constructor<?>[] classConstructors = evaluator.getDeclaredConstructors();
            constructors.add(classConstructors[0]);
        }

        Environment env = new Environment();

        String text = Files.readString(Path.of("data/code.txt"));
        System.out.println(text);
        List<GroupToken> tokens = CodeTokenizer.tokenize(text);
        tokens.forEach(System.out::println);
        List<Evaluator> eval = tokens.stream().map(t -> getEvaluator(env, expressions, constructors, t)).toList();
        eval.forEach(Evaluator::evaluate);
    }

    private static Evaluator getEvaluator(Environment env, List<PatternExpression> expressions, List<Constructor<?>> constructors, GroupToken group)
    {
        List<Token> content = group.content();
        int index = getExpressionIndex(expressions, content);
        if (index == -1)
        {
            if (content.size() == 1)
            {
                if (content.get(0) instanceof NumberToken n)
                {
                    return new NumberEvaluator(n.number());
                }
                else if (content.get(0) instanceof WordToken w)
                {
                    if(w.content().equals("true")) return new BooleanEvaluator(true);
                    if(w.content().equals("false")) return new BooleanEvaluator(false);
                    return new VariableEvaluator(env, w.content());
                }
                else if (content.get(0) instanceof StringToken s)
                {
                    return new StringEvaluator(s.content());
                }
                else if (content.get(0) instanceof GroupToken g)
                {
                    return getEvaluator(env, expressions, constructors, g);
                }
            }

            // Multi Statement Evaluator
            List<Evaluator> evaluators = new ArrayList<>();
            for (Token token : content)
            {
                if (token instanceof GroupToken g)
                {
                    evaluators.add(getEvaluator(env, expressions, constructors, g));
                }
                else
                {
                    System.out.println("-----------------" + token.getClass());
                    System.out.println(content);
                    System.out.println(token.toString().replace("\n", "\\n"));
                    throw new RuntimeException("Unexpected token in multi statement evaluator!");
                }
            }
            return new MultiEvaluator(evaluators);
        }
        else
        {
            List<GroupToken> subs = content.stream()
                .filter(t -> t instanceof GroupToken)
                .map(t -> (GroupToken) t)
                .toList();
            Object[] args = new Object[subs.size() + 1];
            args[0] = env;
            for (int i = 0; i < subs.size(); i++)
            {
                args[i + 1] = getEvaluator(env, expressions, constructors, subs.get(i));
            }
            try
            {
                Object o = constructors.get(index).newInstance(args);
                if (o instanceof Evaluator eval)
                {
                    return eval;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            throw new RuntimeException("ERROR CREATING!");
        }
    }

    private static int getExpressionIndex(List<PatternExpression> expressions, List<Token> tokens)
    {
        for (int i = 0; i < expressions.size(); i++)
        {
            if (expressions.get(i).matches(tokens))
            {
                return i;
            }
        }
        return -1;
    }
}

