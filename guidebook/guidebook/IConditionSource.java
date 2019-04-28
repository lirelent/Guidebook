package lirelent_gigaherz.guidebook.guidebook;

import lirelent_gigaherz.guidebook.guidebook.conditions.ConditionContext;

import java.util.function.Predicate;

public interface IConditionSource
{
    Predicate<ConditionContext> getCondition(String name);
}
