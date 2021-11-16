package epl.parser.util;

import java.util.List;

public class IndexedIterator<T>
{
    public static <T> IndexedIterator<T> iterator(List<T> tokens)
    {
        return new IndexedIterator<>(tokens);
    }

    private int index;
    private final int length;
    private final List<T> tokens;

    private IndexedIterator(List<T> tokens)
    {
        this.length = tokens.size();
        this.tokens = tokens;
    }

    public boolean hasNext()
    {
        return this.index < this.length;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return this.index;
    }

    public T next()
    {
        return this.tokens.get(this.index++);
    }

    public T peek()
    {
        return this.tokens.get(this.index);
    }

    public T last()
    {
        return this.tokens.get(this.index - 1);
    }
}
