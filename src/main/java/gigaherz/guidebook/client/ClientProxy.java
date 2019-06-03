package gigaherz.guidebook.client;

import gigaherz.common.client.ModelHandle;
import gigaherz.guidebook.GuidebookMod;
import gigaherz.guidebook.common.IModProxy;
import gigaherz.guidebook.guidebook.BookDocument;
import gigaherz.guidebook.guidebook.client.BookBakedModel;
import gigaherz.guidebook.guidebook.client.BookRegistry;
import gigaherz.guidebook.guidebook.client.BookRendering;
import gigaherz.guidebook.guidebook.client.GuiGuidebook;
import gigaherz.guidebook.guidebook.client.background.StaticImage1PageBackground;
import gigaherz.guidebook.guidebook.client.background.StaticImage2PageBackground;
import gigaherz.guidebook.guidebook.conditions.AdvancementCondition;
import gigaherz.guidebook.guidebook.conditions.BasicConditions;
import gigaherz.guidebook.guidebook.conditions.CompositeCondition;
import gigaherz.guidebook.guidebook.conditions.GameStageCondition;
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
public class ClientProxy implements IModProxy
{
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        OBJLoader.INSTANCE.addDomain(GuidebookMod.MODID);
        ModelLoaderRegistry.registerLoader(new BookBakedModel.ModelLoader());

        //registerItemModel(GuidebookMod.guidebook);

        ModelLoader.setCustomMeshDefinition(GuidebookMod.guidebook, new ItemMeshDefinition()
        {
            final ModelResourceLocation defaultModel = new ModelResourceLocation(GuidebookMod.guidebook.getRegistryName(), "inventory");

            {
                ModelLoader.registerItemVariants(GuidebookMod.guidebook, defaultModel);
            }

            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                BookDocument book = BookRegistry.get(stack);

                ModelResourceLocation mrl = book == null ? null : book.getModel();

                return mrl != null ? mrl : defaultModel;
            }
        });

        ModelLoader.registerItemVariants(GuidebookMod.guidebook, BookRegistry.gatherBookModels());
    }

    /*@SubscribeEvent
    public static void colors(ColorHandlerEvent.Item event)
    {
    }*/

    @Override
    public void preInit()
    {
        BookRegistry.injectCustomResourcePack();
        
        BookRendering.BACKGROUND_FACTORY_MAP.put(new ResourceLocation("gbook:textures/gui/two_side_2d_background.png"), StaticImage2PageBackground.twoSide2dBackgroundFactory);
        BookRendering.BACKGROUND_FACTORY_MAP.put(new ResourceLocation("gbook:textures/gui/single_side_2d_background.png"), StaticImage1PageBackground.oneSide2dBackgroundFactory);

        ModelHandle.init();

        BasicConditions.register();
        CompositeCondition.register();
        AdvancementCondition.register();

        if (Loader.isModLoaded("gamestages"))
            GameStageCondition.register();
        MinecraftForge.EVENT_BUS.post(new BookRegistryEvent());

        ClientCommandHandler.instance.registerCommand(new GbookCommand());
    }

    @Override
    public void registerBook(ResourceLocation bookLocation, boolean excludeItemRegistry)
    {
        BookRegistry.registerBook(bookLocation, excludeItemRegistry);
    }

    @Override
    public Collection<ResourceLocation> getBooksListForItem()
    {
        return BookRegistry.getLoadedBooksForItem().keySet();
    }

    @Override
    public void displayBook(String book)
    {
        ResourceLocation loc = new ResourceLocation(book);
        BookDocument br = BookRegistry.get(loc);
        if (br != null && br.chapterCount() > 0)
            Minecraft.getMinecraft().displayGuiScreen(new GuiGuidebook(loc));
    }

    @Override
    public String getBookName(String book)
    {
        BookDocument bookDocument = BookRegistry.get(new ResourceLocation(book));
        if (bookDocument != null)
        {
            String name = bookDocument.getName();
            if (name != null)
                return name;
        }
        return String.format("Guidebook - %s unknown", book);
    }
}
