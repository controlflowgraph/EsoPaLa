package epl.parser;

import epl.eval.Environment;
import epl.parser.code.segment.CodeSegment;
import epl.parser.util.IndexedIterator;

import java.util.List;
import java.util.Stack;

public class MatchingContext
{
    private final Stack<Integer> saves = new Stack<>();
    private final IndexedIterator<CodeSegment> iterator;
    private final List<MatcherUnit> units;
    private final Environment env;

    public MatchingContext(IndexedIterator<CodeSegment> iterator, List<MatcherUnit> units, Environment env)
    {
        this.iterator = iterator;
        this.units = units;
        this.env = env;
    }

    public boolean hasNext()
    {
        return this.iterator.hasNext();
    }

    public CodeSegment getNext()
    {
        return this.iterator.next();
    }

    public CodeSegment peekNext()
    {
        return this.iterator.peek();
    }

    public void save()
    {
        this.saves.push(this.iterator.getIndex());
    }

    public void restore()
    {
        this.iterator.setIndex(this.saves.pop());
    }

    public void discard()
    {
        this.saves.pop();
    }

    public List<MatcherUnit> getUnits()
    {
        return this.units;
    }

    public Environment getEnv()
    {
        return this.env;
    }

    public int getLength()
    {
        return this.iterator.getLength();
    }
}
