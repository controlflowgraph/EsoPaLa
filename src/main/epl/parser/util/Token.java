package epl.parser.util;

public interface Token<T>
{
    String token();

    T type();

    int position();
}
