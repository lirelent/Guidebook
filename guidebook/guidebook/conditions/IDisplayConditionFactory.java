package com.lireherz.guidebook.guidebook.conditions;

import com.lireherz.guidebook.guidebook.BookDocument;
import org.w3c.dom.Node;

import java.util.function.Predicate;

@FunctionalInterface
public interface IDisplayConditionFactory {
	Predicate<ConditionContext> parse (BookDocument document, Node xmlNode);
}
