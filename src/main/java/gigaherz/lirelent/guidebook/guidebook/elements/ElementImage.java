package gigaherz.lirelent.guidebook.guidebook.elements;

import gigaherz.lirelent.guidebook.guidebook.IBookGraphics;
import gigaherz.lirelent.guidebook.guidebook.IConditionSource;
import gigaherz.lirelent.guidebook.guidebook.util.Rect;
import gigaherz.lirelent.guidebook.guidebook.util.Size;
import gigaherz.lirelent.guidebook.guidebook.drawing.VisualElement;
import gigaherz.lirelent.guidebook.guidebook.drawing.VisualImage;
import net.minecraft.util.ResourceLocation;
import org.w3c.dom.NamedNodeMap;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ElementImage extends ElementInline
{
    public ResourceLocation textureLocation;
    /**
     * The {@link ResourceLocation} to use for the image when the mouse is hovered
     * over the image instead of using {@link #hoverTextureLocation}
     */
    public ResourceLocation hoverTextureLocation;
    public int tx = 0;
    public int ty = 0;
    public int tw = 0;
    public int th = 0;

    public float scale = 1.0f;

    public ElementImage(boolean isFirstElement, boolean isLastElement)
    {
        super(isFirstElement, isLastElement);
    }

    private Size getVisualSize()
    {
        int width = (int) ((w > 0 ? w : tw) * scale);
        int height = (int) ((h > 0 ? h : th) * scale);
        return new Size(width, height);
    }

    private VisualImage getVisual()
    {
        return new VisualImage(getVisualSize(), position, baseline, verticalAlignment, textureLocation, hoverTextureLocation, tx, ty, tw, th, (w > 0 ? w : tw), (h > 0 ? h : th), scale);
    }

    @Override
    public List<VisualElement> measure(IBookGraphics nav, int width, int firstLineWidth)
    {
        return Collections.singletonList(getVisual());
    }

    @Override
    public int reflow(List<VisualElement> paragraph, IBookGraphics nav, Rect bounds, Rect page)
    {
        VisualImage element = getVisual();
        element.position = applyPosition(bounds.position, bounds.position);
        paragraph.add(element);
        if (position != POS_RELATIVE)
            return bounds.position.y;
        return bounds.position.y + element.size.height;
    }

    @Override
    public void findTextures(Set<ResourceLocation> textures)
    {
        // No need to require them, since they are used dynamically and not stitched.
        //textures.add(textureLocation);
    }

    @Override
    public void parse(IConditionSource book, NamedNodeMap attributes)
    {
        super.parse(book, attributes);

        tx = getAttribute(attributes, "tx", tx);
        ty = getAttribute(attributes, "ty", ty);
        tw = getAttribute(attributes, "tw", tw);
        th = getAttribute(attributes, "th", th);
        textureLocation = getAttribute(attributes, "src", textureLocation);
        hoverTextureLocation = getAttribute(attributes, "hoverSrc", textureLocation);
        scale = getAttribute(attributes, "scale", scale);
    }

    @Override
    public String toString(boolean complete)
    {
        // TODO: Complete mode
        return String.format("<img src=\"%s\" .../>", textureLocation);
    }

    @Override
    public ElementInline copy()
    {
        ElementImage img = super.copy(new ElementImage(isFirstElement, isLastElement));

        img.textureLocation = new ResourceLocation(textureLocation.toString());
        img.tx = tx;
        img.ty = ty;
        img.tw = tw;
        img.th = th;
        img.scale = scale;
        return img;
    }

    @Override
    public boolean supportsPageLevel()
    {
        return true;
    }
}
