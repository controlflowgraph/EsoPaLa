package epml.token.code;

import java.util.regex.Pattern;

public record TokenType(String name, Pattern pattern)
{
}
