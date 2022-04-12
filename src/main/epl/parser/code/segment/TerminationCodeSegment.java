package epl.parser.code.segment;

public record TerminationCodeSegment(int position) implements CodeSegment
{
    @Override
    public String toString()
    {
        return ".";
    }
}
