package lirelent_gigaherz.guidebook.guidebook.drawing;

import lirelent_gigaherz.guidebook.guidebook.IBookGraphics;
import lirelent_gigaherz.guidebook.guidebook.util.Size;

public class VisualPageBreak extends VisualElement
{
    public VisualPageBreak(Size size)
    {
        super(size, 0, 0, 0);
    }

    @Override
    public void draw(IBookGraphics nav)
    {
        // not a drawableelement
    }
}
