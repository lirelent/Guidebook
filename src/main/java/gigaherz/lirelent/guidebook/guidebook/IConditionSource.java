package gigaherz.lirelent.guidebook.guidebook;

import gigaherz.lirelent.guidebook.guidebook.conditions.ConditionContext;

import java.util.function.Predicate;

public interface IConditionSource
{
    Predicate<ConditionContext> getCondition(String name);
}
