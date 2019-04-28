package lirelent_gigaherz.guidebook.guidebook.conditions;

import lirelent_gigaherz.guidebook.guidebook.BookDocument;
import org.w3c.dom.Node;

import java.util.function.Predicate;

@FunctionalInterface
public interface IDisplayConditionFactory
{
    Predicate<ConditionContext> parse(BookDocument document, Node xmlNode);
}
