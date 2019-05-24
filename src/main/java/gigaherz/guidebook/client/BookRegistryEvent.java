package gigaherz.guidebook.client;

import gigaherz.guidebook.GuidebookMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BookRegistryEvent extends Event
{
    /**
     * Register a Guidebook xml {@link ResourceLocation} with {@link gigaherz.guidebook.guidebook.client.BookRegistry}.
     * Will automatically add this Guidebook as a subItem of the Guidebook mod guidebook.
     *
     * @param bookLocation {@link ResourceLocation} of the xml of a Guidebook contents definition
     */
    public void register(ResourceLocation bookLocation)
    {
        register(bookLocation, false);
    }

    /**
     * Register a Guidebook xml {@link ResourceLocation} with {@link gigaherz.guidebook.guidebook.client.BookRegistry}
     *
     * @param bookLocation        {@link ResourceLocation} of the xml of a Guidebook contents definition
     * @param excludeItemRegistry if false will automatically add this Guidebook as a subItem of the Guidebook mod guidebook. If true,
     *                            it's up to you to extend {@link gigaherz.guidebook.guidebook.ItemGuidebook} and register your
     *                            {@link gigaherz.guidebook.guidebook.ItemGuidebook}s yourself
     */
    public void register(ResourceLocation bookLocation, boolean excludeItemRegistry)
    {
        GuidebookMod.proxy.registerBook(bookLocation, excludeItemRegistry);
    }
}
