package lirelent_gigaherz.guidebook.guidebook.elements;

import lirelent_gigaherz.guidebook.guidebook.IBookGraphics;
import lirelent_gigaherz.guidebook.guidebook.util.Rect;
import lirelent_gigaherz.guidebook.guidebook.util.Size;
import lirelent_gigaherz.guidebook.guidebook.drawing.VisualElement;
import lirelent_gigaherz.guidebook.guidebook.drawing.VisualPageBreak;

import java.util.List;

public class ElementBreak extends Element
{
    @Override
    public int reflow(List<VisualElement> list, IBookGraphics nav, Rect bounds, Rect page)
    {
        list.add(new VisualPageBreak(new Size()));
        return bounds.position.y;
    }

    @Override
    public Element copy()
    {
        return new ElementBreak();
    }

    @Override
    public boolean supportsPageLevel()
    {
        return true;
    }

    @Override
    public boolean supportsSpanLevel()
    {
        return false;
    }

    @Override
    public String toString(boolean complete)
    {
        return "<br/>";
    }
}
