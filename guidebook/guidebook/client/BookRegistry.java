package com.lireherz.guidebook.guidebook.client;

import com.aranaira.arcanearchives.ArcaneArchives;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lireherz.guidebook.GuidebookMod;
import com.lireherz.guidebook.guidebook.BookDocument;
import com.lireherz.guidebook.guidebook.templates.TemplateLibrary;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static net.minecraftforge.fml.common.LoaderState.INITIALIZATION;

public class BookRegistry {
	// TODO
	public static ResourceLocation ARCANE_TOME = new ResourceLocation(ArcaneArchives.MODID, "arcane_tome");
	public static BookDocument BOOK = null;

	public static void parseAllBooks (IResourceManager manager) {
		TemplateLibrary.clear();

		LanguageManager lm = Minecraft.getMinecraft().getLanguageManager();

		String lang = ObfuscationReflectionHelper.getPrivateValue(LanguageManager.class, lm, "field_135048_c");
		if (lang == null) {
			lang = "en_us";
		}

		BOOK = parseBook(manager, ARCANE_TOME, lang);
	}

	@Nullable
	private static BookDocument parseBook (IResourceManager manager, ResourceLocation location, String lang) {
		BookDocument bookDocument = new BookDocument(location);
		try {
			ResourceLocation bookLocation = bookDocument.getLocation();
			String domain = bookLocation.getNamespace();
			String path = bookLocation.getPath();
			String pathWithoutExtension = path;
			String extension = "";
			int ext = path.lastIndexOf('.');
			if (ext >= 0) {
				pathWithoutExtension = path.substring(0, ext);
				extension = path.substring(ext);
			}

			String localizedPath = pathWithoutExtension + "." + lang + extension;
			ResourceLocation localizedLoc = new ResourceLocation(domain, localizedPath);

			IResource bookResource;
			try {
				bookResource = manager.getResource(localizedLoc);
			} catch (IOException e) {
				bookResource = null;
			}

			if (bookResource == null) {
				bookResource = manager.getResource(bookLocation);
			}
			try (InputStream stream = bookResource.getInputStream()) {
				if (!bookDocument.parseBook(stream, false)) {
					return null;
				}
			}
		} catch (IOException e) {
			bookDocument.initializeWithLoadError(e.toString());
		}
		return bookDocument;
	}
}
