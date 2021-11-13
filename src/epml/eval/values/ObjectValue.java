package epml.eval.values;

import java.util.HashMap;
import java.util.Map;

public class ObjectValue implements Value
{
    private final Map<String, Value> attributes = new HashMap<>();

    public void setAttribute(String attribute, Value value)
    {
        this.attributes.put(attribute, value);
    }

    public Value getAttribute(String attribute)
    {
        return this.attributes.getOrDefault(attribute, new NoneValue());
    }
}
