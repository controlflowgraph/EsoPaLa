package epl.parser.code.segment;

import java.util.ArrayList;
import java.util.List;

public record SubCodeSegment(List<CodeSegment> segments) implements CodeSegment
{
    public SubCodeSegment()
    {
        this(new ArrayList<>());
    }

    public void add(CodeSegment segment)
    {
        this.segments.add(segment);
    }
}
