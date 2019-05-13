package com.lireherz.guidebook.guidebook.client;

import com.aranaira.arcanearchives.ArcaneArchives;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ArcaneArchivesBackground implements IAnimatedBookBackground {
	private ResourceLocation background = new ResourceLocation(ArcaneArchives.MODID, "textures/gui/arcana_documentation.png");
	private GuiGuidebook gui;

	private float ticks = 10;

	public ArcaneArchivesBackground (GuiGuidebook gui) {
		this.gui = gui;
	}

	@Override
	public void startClosing () {
	}

	@Override
	public boolean isFullyOpen () {
		return ticks <= 0;
	}

	@Override
	public boolean update () {
		if (ticks > 0) {
			ticks--;
		}

		return false;
	}

	@Override
	public void draw (float partialTicks, int bookHeight, float scalingFactor) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(scalingFactor, scalingFactor, scalingFactor);

		GlStateManager.enableBlend();
		GlStateManager.color(1f, 1f, 1f);

		gui.mc.renderEngine.bindTexture(background);
		// 0, 0, 0, 0, gui.width, gui.height
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 243, 254, 243, 254);

		GlStateManager.disableBlend();

		GlStateManager.popMatrix();
	}
}
