package com.lireherz.guidebook;

import com.lireherz.guidebook.common.IModProxy;
import com.lireherz.guidebook.guidebook.ItemGuidebook;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.logging.log4j.Logger;

/*@Mod.EventBusSubscriber
@Mod(modid = GuidebookMod.MODID, version = GuidebookMod.VERSION,
        acceptedMinecraftVersions = "[1.12.0,1.13.0)",
        updateJSON = "https://raw.githubusercontent.com/gigaherz/guidebook/master/update.json")*/
public class GuidebookMod {
	public static final String MODID = "gbook";
	public static final String VERSION = "@VERSION@";

	@SubscribeEvent
	public static void registerItems (RegistryEvent.Register<Item> event) {
		// TODO: Move into Arcane Archives
		event.getRegistry().registerAll(new ItemGuidebook().setRegistryName("guidebook").setTranslationKey(GuidebookMod.MODID + ".guidebook").setHasSubtypes(true).setMaxStackSize(1));
	}

	public static ResourceLocation location (String location) {
		return new ResourceLocation(MODID, location);
	}
}
