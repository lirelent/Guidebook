package com.lireherz.guidebook.guidebook;

import com.lireherz.guidebook.guidebook.conditions.ConditionContext;

import java.util.function.Predicate;

public interface IConditionSource
{
    Predicate<ConditionContext> getCondition(String name);
}
