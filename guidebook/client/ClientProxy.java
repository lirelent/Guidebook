package com.lireherz.guidebook.client;

import com.lireherz.guidebook.GuidebookMod;
import com.lireherz.guidebook.common.IModProxy;
import com.lireherz.guidebook.guidebook.BookDocument;
import com.lireherz.guidebook.guidebook.client.BookBakedModel;
import com.lireherz.guidebook.guidebook.client.BookRegistry;
import com.lireherz.guidebook.guidebook.client.GuiGuidebook;
import com.lireherz.guidebook.guidebook.conditions.AdvancementCondition;
import com.lireherz.guidebook.guidebook.conditions.BasicConditions;
import com.lireherz.guidebook.guidebook.conditions.CompositeCondition;
import com.lireherz.guidebook.guidebook.conditions.GameStageCondition;
import gigaherz.common.client.ModelHandle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Collection;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = GuidebookMod.MODID)
public class ClientProxy implements IModProxy {
	public ClientProxy () {
		BookRegistry.injectCustomResourcePack();
	}

	@Override
	public void preInit () {
		ModelHandle.init();

		BasicConditions.register();
		CompositeCondition.register();
		AdvancementCondition.register();

		MinecraftForge.EVENT_BUS.post(new BookRegistryEvent());

		ClientCommandHandler.instance.registerCommand(new GbookCommand());
	}

	@Override
	public void registerBook (ResourceLocation bookLocation) {
		BookRegistry.registerBook(bookLocation);
	}

	@Override
	public Collection<ResourceLocation> getBooksList () {
		return BookRegistry.getLoadedBooks().keySet();
	}

	@Override
	public void displayBook (String book) {
		ResourceLocation loc = new ResourceLocation(book);
		BookDocument br = BookRegistry.get(loc);
		if (br != null && br.chapterCount() > 0) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiGuidebook(loc));
		}
	}

	@Override
	public String getBookName (String book) {
		BookDocument bookDocument = BookRegistry.get(new ResourceLocation(book));
		if (bookDocument != null) {
			String name = bookDocument.getName();
			if (name != null) {
				return name;
			}
		}
		return String.format("Guidebook - %s unknown", book);
	}
}
