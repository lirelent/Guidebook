package com.lireherz.guidebook;

import com.aranaira.arcanearchives.ArcaneArchives;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigValues {
	@Config.Comment("Use -1 for same as GUI scale, 0 for auto, 1+ for small/medium/large")
	@Config.RangeInt(min = -1, max = 10)
	public static int bookGUIScale = -1;

	@Config.Comment("Keep at false to use integral scaling, which makes the font pixels evently scaled. If set to true, the books will fill the screen space, even if the font becomes wonky.")
	public static boolean flexibleScale = false;

	@Mod.EventBusSubscriber(modid = ArcaneArchives.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged (final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(ArcaneArchives.MODID)) {
				ConfigManager.sync(ArcaneArchives.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
