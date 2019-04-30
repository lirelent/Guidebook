package com.lireherz.guidebook.client;

import com.aranaira.arcanearchives.ArcaneArchives;
import com.lireherz.guidebook.GuidebookMod;
import com.lireherz.guidebook.common.IModProxy;
import com.lireherz.guidebook.guidebook.client.GuiGuidebook;
import com.lireherz.guidebook.guidebook.conditions.AdvancementCondition;
import com.lireherz.guidebook.guidebook.conditions.BasicConditions;
import com.lireherz.guidebook.guidebook.conditions.CompositeCondition;
import gigaherz.common.client.ModelHandle;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = GuidebookMod.MODID)
public class ClientProxy implements IModProxy {
	// TODO: fix name
	public static ResourceLocation ARCANE_TOME = new ResourceLocation(ArcaneArchives.MODID, "tome_of_thingy");

	public ClientProxy () {
	}

	@Override
	public void preInit () {
		ModelHandle.init();

		BasicConditions.register();
		CompositeCondition.register();
		AdvancementCondition.register();

		ClientCommandHandler.instance.registerCommand(new GbookCommand());
	}

	@Override
	public void displayBook (String book) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiGuidebook(ARCANE_TOME));
	}
}
