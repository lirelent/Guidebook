package com.lireherz.guidebook.guidebook.drawing;

import com.lireherz.guidebook.guidebook.IBookGraphics;
import com.lireherz.guidebook.guidebook.client.BookRendering;
import com.lireherz.guidebook.guidebook.util.Rect;
import com.lireherz.guidebook.guidebook.util.Size;
import net.minecraft.client.gui.Gui;

public abstract class VisualElement extends Rect
{
    // Only position modes 1 and 2 are valid here, mode 0 will have been handled by reflow
    public int positionMode = 1;

    public int verticalAlign = 1;

    public float baseline = 0;

    public VisualElement(Size size, int positionMode, float baseline, int verticalAlign)
    {
        this.size = size;
        this.positionMode = positionMode;
        this.baseline = baseline;
        this.verticalAlign = verticalAlign;
    }

    public void draw(IBookGraphics nav)
    {
        if(BookRendering.DEBUG_DRAW_BOUNDS)
        {
            Gui.drawRect(this.position.x, this.position.y, this.position.x + this.size.width, this.position.y + this.size.height, 0x3f000000);
        }
    }

    public void mouseOver(IBookGraphics nav, int x, int y)
    {
    }

    public void mouseOut(IBookGraphics nav, int x, int y)
    {
    }

    public void click(IBookGraphics nav)
    {
    }

    public boolean wantsHover()
    {
        return false;
    }

    public String getText()
    {
        return "";
    }
}
