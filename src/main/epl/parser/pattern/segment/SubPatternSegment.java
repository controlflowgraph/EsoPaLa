package epl.parser.pattern.segment;

import java.util.ArrayList;
import java.util.List;

public record SubPatternSegment(List<PatternSegment> segments, Quantifier quantifier) implements PatternSegment
{
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
