package com.lireherz.guidebook.client;

import com.lireherz.guidebook.GuidebookMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BookRegistryEvent extends Event {
	public void register (ResourceLocation bookLocation) {
		GuidebookMod.proxy.registerBook(bookLocation);
	}
}
