package com.lireherz.guidebook.guidebook.drawing;

import com.lireherz.guidebook.guidebook.IBookGraphics;
import com.lireherz.guidebook.guidebook.util.Size;

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
