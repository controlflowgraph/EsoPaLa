package epl.parser.code.segment;

public record NumberCodeSegment(String number, int position) implements CodeSegment
{
    @Override
    public String toString()
    {
        return this.number;
    }
}
