package epl.parser.pattern;

import epl.parser.pattern.segment.PatternSegment;

import java.util.List;
import java.util.Map;

public record PatternMatcher(List<PatternSegment> segments, Map<String, Integer> occurrences)
{

}
