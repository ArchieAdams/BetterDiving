package meldexun.better_diving.client;

import meldexun.better_diving.BetterDiving;
import meldexun.better_diving.client.gui.screen.ScreenSeamoth;
import meldexun.better_diving.client.renderer.entity.RenderSeamoth;
import meldexun.better_diving.init.BetterDivingContainers;
import meldexun.better_diving.init.BetterDivingEntities;
import meldexun.better_diving.oxygenprovider.DivingGearManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BetterDiving.MOD_ID, value = Dist.CLIENT, bus = Bus.MOD)
public class ClientBetterDiving {

	public static final KeyBinding KEY_BIND_SEAMOTH_DESCEND = new KeyBinding("Seamoth Descend", 67, BetterDiving.MOD_ID);

	@SuppressWarnings("resource")
	public static PlayerEntity getPlayer() {
		return Minecraft.getInstance().player;
	}

	@SuppressWarnings("resource")
	public static World getWorld() {
		return Minecraft.getInstance().world;
	}

	@SubscribeEvent
	public static void onFMLClientSetupEvent(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(KEY_BIND_SEAMOTH_DESCEND);

		RenderingRegistry.registerEntityRenderingHandler(BetterDivingEntities.SEAMOTH.get(), RenderSeamoth::new);

		ScreenManager.registerFactory(BetterDivingContainers.SEAMOTH_ENTITY.get(), ScreenSeamoth::new);
		ScreenManager.registerFactory(BetterDivingContainers.SEAMOTH_ITEM.get(), ScreenSeamoth::new);
	}

	@SubscribeEvent
	public static void onTextureStitchPreEvent(TextureStitchEvent.Pre event) {
		if (event.getMap().getTextureLocation().equals(PlayerContainer.LOCATION_BLOCKS_TEXTURE)) {
			event.addSprite(new ResourceLocation(BetterDiving.MOD_ID, "item/empty_power_cell"));
		}
	}

	@SubscribeEvent
	public static void onPlayerLoggedInEvent(ModConfigEvent event) {
		DivingGearManager.reloadConfigs();
	}

}
