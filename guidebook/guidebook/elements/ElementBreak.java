package com.lireherz.guidebook.guidebook.elements;

import com.lireherz.guidebook.guidebook.IBookGraphics;
import com.lireherz.guidebook.guidebook.drawing.VisualElement;
import com.lireherz.guidebook.guidebook.drawing.VisualPageBreak;
import com.lireherz.guidebook.guidebook.util.Rect;
import com.lireherz.guidebook.guidebook.util.Size;

import java.util.List;

public class ElementBreak extends Element {
	@Override
	public int reflow (List<VisualElement> list, IBookGraphics nav, Rect bounds, Rect page) {
		list.add(new VisualPageBreak(new Size()));
		return bounds.position.y;
	}

	@Override
	public Element copy () {
		return new ElementBreak();
	}

	@Override
	public boolean supportsPageLevel () {
		return true;
	}

	@Override
	public boolean supportsSpanLevel () {
		return false;
	}

	@Override
	public String toString (boolean complete) {
		return "<br/>";
	}
}
