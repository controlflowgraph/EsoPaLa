package epml;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.evaluators.arithmetic.AdditionEvaluator;
import epml.eval.evaluators.arithmetic.DivisionEvaluator;
import epml.eval.evaluators.arithmetic.MultiplicationEvaluator;
import epml.eval.evaluators.arithmetic.SubtractionEvaluator;
import epml.eval.evaluators.comparator.*;
import epml.eval.evaluators.functions.*;
import epml.eval.evaluators.list.*;
import epml.eval.evaluators.util.*;
import epml.eval.evaluators.util.random.RandomNumberEvaluator;
import epml.eval.evaluators.util.random.RandomNumberInRangeEvaluator;
import epml.token.code.*;
import epml.token.pattern.PatternExpression;
import epml.token.pattern.PatternTokenizer;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

// TODO: redo everything ;)
// TODO: add strings
// TODO: add booleans
// TODO: change from (annotated) class to (annotated) function
// TODO: add multi patterns
// TODO: add simple repetition to the patterns
//  (if a placeholder is mentioned multiple times or in a repetition it is passed to the evaluator as a list)
// TODO: abstract accessor and implement VariableAccessor and AttributeAccessor
// TODO: add floating point numbers
// TODO: add value objects that have a map of attributes
//

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
        RangeEvaluator.class,
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
        SwapElementEvaluator.class
    );

    public static String code0()
    {
        return """
            for each (x) in (the range from (1) to (10) incrementing by (1)) do ((
                print ((x) times (2)).
            )).
            """;
    }

    public static String code1()
    {
        return """
            let (r) be equal to (the range from (1) to (20) incrementing by (1)).
            print (only select (x) from (r) where ((x) is even)).
            """;
    }

    public static String code2()
    {
        return """
            let (r) be equal to (the range from (0) to (100) incrementing by (1)).
            let (s) be equal to (from (r) filter all (x) where ((x) is even) while ((x) is less than (20))).
            print (s).
            """;
    }

    public static String code3()
    {
        return """
            let (r) be equal to (the range from (0) to (100) incrementing by (1)).
                        
            let (s) be equal to (
                a list containing all elements (x) from (r)
                where ((x) is even)
                while ((x) is less than (50))).
                        
            for each (x) in (s) do (print (x)).
            """;
    }

    public static String code4()
    {
        return """
            let (x) be equal to (1).
            let (y) be equal to (100).
            let (z) be equal to (1).
            let (r) be equal to (the range from (x) to (y) incrementing by (z)).
            for each (i) in (r) do (print (i)).
            print ((y) minus (x)).
            """;
    }

    public static String code5()
    {
        return """
            let (x) be equal to ("Hello World!").
            let (y) be equal to ((x) repeated (5) times).
            print (x).
            print (y).
            """;
    }

    public static String code6()
    {
        return """
            let (test) be equal to (a new object).
            set ((x) of (test)) to ("Hello World!").
            print ((x) of (test)).
            """;
    }

    public static String code7()
    {
        return """
            let (func) be equal to (a function that receives (r) and does the following ((
                    for each (x) in (r) do ((
                        print (x).
                    )).
                    return (100).
                ))).
            let (tr) be equal to (the range from (1) to (20) incrementing by (1)).
            let (ret) be equal to ((func) called with (tr)).
            print (ret).
            """;
    }

    public static String code8()
    {
        return """
            let (func) be equal to (a function that receives ((r1) (r2)) and does the following ((
                    for each (x) in (r1) do ((
                        for each (y) in (r2) do ((
                            print ((x) times (y)).
                        )).
                    )).
                    return (100).
                ))).
            let (tr) be equal to (the range from (1) to (11) incrementing by (1)).
            let (ret) be equal to (the result of ((func) called with ((tr) (tr)))).
            print (ret).
            """;
    }

    public static String code9()
    {
        return """
        let (a) be equal to (the empty list).
        let (r) be equal to (the range from (0) to (100) incrementing by (1)).
        for each (x) in (r) do ((
            (a) append (a random number).
        )).
        print(a).
        
        let (bubble) be equal to (a function that receives ((list)) and does the following ((
            let (len) be equal to (the length of (list)).
            let (r) be equal to (the range from (0) to (len) incrementing by (1)).
            for each (leftIndex) in (r) do ((
                let (sub) be equal to (the range from ((leftIndex) plus (1)) to (len) incrementing by (1)).
                for each (rightIndex) in (sub) do ((
                    if (((list) at (leftIndex)) is greater than ((list) at (rightIndex))) then do ((
                        swap ((list) at (leftIndex)) and ((list) at (rightIndex)).
                    )).
                )).
            )).
            return (l).
        ))
        ).
        call (bubble) with (a).
        for each (x) in (a) do (print(x)).
        
        """;
    }

    public static String code10()
    {
        return """
            let (r) be equal to (the range from (1) to (10) incrementing by (1)).
            let (l) be equal to (the empty list).
            for each (x) in (r) do ((
                append (a random number between (0) and (x)) to (l).
            )).
            print(l).
            """;
    }

    public static String code(int index)
    {
        return new String[]{
            code1(),
            code0(),
            code2(),
            code3(),
            code4(),
            code5(),
            code6(),
            code7(),
            code8(),
            code9(),
            code10(),
        }[index];
    }

    public static void main(String[] args)
    {
        List<PatternExpression> expressions = new ArrayList<>();
        List<Constructor<?>> constructors = new ArrayList<>();

        for (Class<? extends Evaluator> evaluator : EVALUATORS)
        {
            LanguagePattern annotation = evaluator.getAnnotation(LanguagePattern.class);
            String pattern = annotation.pattern();
            expressions.add(PatternTokenizer.tokenize(pattern));

            Constructor<?>[] classConstructors = evaluator.getDeclaredConstructors();
            constructors.add(classConstructors[0]);
        }

        Environment env = new Environment();

        String text = code(9);
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
                    System.out.println(token);
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

