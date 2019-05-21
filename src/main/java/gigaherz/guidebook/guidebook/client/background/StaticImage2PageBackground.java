package gigaherz.guidebook.guidebook.client.background;

import gigaherz.guidebook.guidebook.client.GuiGuidebook;
import gigaherz.guidebook.guidebook.elements.ElementImage;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * {@link IBookBackground} that is a static image which shows 2 pages at a time (left and right side of the book)
 */
public class StaticImage2PageBackground implements IBookBackground
{
    private final GuiGuidebook gui;

    private final ResourceLocation imageLocation;
    private final int imageX;
    private final int imageY;
    private final int imageWidth;
    private final int imageHeight;
    private final int imageFileWidth;
    private final int imageFileHeight;
    private final float scale;

    private boolean closing = false;

    public StaticImage2PageBackground(GuiGuidebook gui, ElementImage image)
    {
        this.gui = gui;
        this.imageLocation = image.textureLocation;
        this.imageX = image.tx;
        this.imageY = image.ty;
        this.imageWidth = image.tw;
        this.imageHeight = image.th;
        this.imageFileWidth = (image.w > 0 ? image.w : image.tw);
        this.imageFileHeight = (image.h > 0 ? image.h : image.th);
        this.scale = image.scale;
    }

    @Override
    public Layout getLayout()
    {
        return Layout.TWO_PAGES;
    }

    @Override
    public void draw(float partialTicks, int bookHeight, float scalingFactor)
    {
        if (closing)
            return;

        GlStateManager.enableDepth();
        GlStateManager.disableBlend();

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        // ====================================================================

        // translate to "center" of gui
        // and make sure the texture is "behind" other GUI elements
        GlStateManager.translate(gui.width * 0.5, gui.height * 0.5, -50);
        float effectiveScale = 1.05f * scalingFactor;
        GlStateManager.scale(effectiveScale, effectiveScale, 1.0f);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        gui.mc.getTextureManager().bindTexture(imageLocation);

        // then because we're at the "center" of the gui draw the texture at half the texture size left and up
        Gui.drawModalRectWithCustomSizedTexture(-imageWidth / 2, -imageHeight / 2,
                imageX, imageY, imageWidth, imageHeight, imageFileWidth, imageFileHeight);

        // ====================================================================

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
    }

    @Override
    public void startClosing()
    {
        closing = true;
    }

    @Override
    public boolean isFullyOpen()
    {
        // always fully open
        return true;
    }

    @Override
    public boolean update()
    {
        // if closing, than instantly closed, and reset
        if (closing) {
            closing = false;
            return true;
        }

        return false;
    }

    @Override
    public int getWidth()
    {
        return (int)(imageWidth * scale);
    }

    @Override
    public int getHeight()
    {
        return (int)(imageHeight * scale);
    }

    @Override
    public int getInnerMargin()
    {
        return 8;
    }

    @Override
    public int getOuterMargin()
    {
        return 5;
    }

    @Override
    public int getTopMargin()
    {
        return 5;
    }

    @Override
    public int getBottomMargin()
    {
        return 8;
    }

    @Override
    public int getBookScaleMargin()
    {
        return 10;
    }

    /**
     * {@link IBookBackgroundFactory} for demo "gbook:gui/two_side_2d_background" background registered to
     * {@link gigaherz.guidebook.guidebook.client.BookRendering#BACKGROUND_FACTORY_MAP} during
     * {@link gigaherz.guidebook.GuidebookMod#preInit(FMLPreInitializationEvent)}
     */
    public static IBookBackgroundFactory twoSide2dBackgroundFactory = (gui, backgroundLocation) -> {
        ElementImage elementImage = new ElementImage(false, false);

        elementImage.textureLocation = backgroundLocation;
        elementImage.tx = 0;
        elementImage.ty = 0;
        elementImage.tw = 209;
        elementImage.th = 127;
        elementImage.w = 256;
        elementImage.h = 128;
        elementImage.scale = 1f;

        return new StaticImage2PageBackground(gui, elementImage);
    };
}
