package epl.parser.code.segment;

public record WordCodeSegment(String word, int position) implements CodeSegment
{
    @Override
    public String toString()
    {
        return this.word;
    }
}
