package gigaherz.guidebook.guidebook.drawing;

import gigaherz.guidebook.guidebook.HoverContext;
import gigaherz.guidebook.guidebook.IBookGraphics;
import gigaherz.guidebook.guidebook.elements.LinkContext;
import gigaherz.guidebook.guidebook.util.LinkHelper;
import gigaherz.guidebook.guidebook.util.Size;
import net.minecraft.util.ResourceLocation;

public class VisualImage extends VisualElement implements LinkHelper.ILinkable
{
    final public ResourceLocation textureLocation;
    /**
     * The {@link ResourceLocation} to use for the image when the mouse is hovered
     * over the image instead of using {@link #hoverTextureLocation}
     */
    final public ResourceLocation hoverTextureLocation;
    final public int tx;
    final public int ty;
    final public int tw;
    final public int th;
    final public int w;
    final public int h;
    final public float scale;

    public LinkContext linkContext = null;

    /**
     * true iff mouse is hovering over this {@link VisualImage}
     */
    private boolean isHovering;

    public VisualImage(Size size, int positionMode, float baseline, int verticalAlign, ResourceLocation textureLocation,
                       ResourceLocation hoverTextureLocation, int tx, int ty, int tw, int th, int w, int h, float scale)
    {
        super(size, positionMode, baseline, verticalAlign);
        this.textureLocation = textureLocation;
        this.hoverTextureLocation = hoverTextureLocation;
        this.tx = tx;
        this.ty = ty;
        this.tw = tw;
        this.th = th;
        this.w = w;
        this.h = h;
        this.scale = scale;

        this.isHovering = false;
    }

    @Override
    public void draw(IBookGraphics nav)
    {
        super.draw(nav);
        if (hoverTextureLocation != null && isHovering) {
            nav.drawImage(hoverTextureLocation, position.x, position.y, tx, ty, w, h, tw, th, scale);
        } else {
            nav.drawImage(textureLocation, position.x, position.y, tx, ty, w, h, tw, th, scale);
        }
    }

    //public int colorHover = 0xFF77cc66;

    @Override
    public boolean wantsHover()
    {
        return (linkContext != null || hoverTextureLocation != null);
    }

    @Override
    public void mouseOver(IBookGraphics nav, HoverContext hoverContext)
    {
        if (linkContext != null ) {
            linkContext.isHovering = true;
            //Mouse.setNativeCursor(Cursor.)
        }
        isHovering = true;
    }

    @Override
    public void mouseOut(IBookGraphics nav, HoverContext hoverContext)
    {
        if (linkContext != null ) {
            linkContext.isHovering = false;
        }
        isHovering = false;
    }

    @Override
    public void click(IBookGraphics nav)
    {
        if (linkContext != null)
            LinkHelper.click(nav, linkContext);
    }

    @Override
    public void setLinkContext(LinkContext ctx)
    {
        linkContext = ctx;
    }
}
