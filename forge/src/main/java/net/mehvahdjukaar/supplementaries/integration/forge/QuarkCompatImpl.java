package net.mehvahdjukaar.supplementaries.integration.forge;

import net.mehvahdjukaar.supplementaries.common.items.SackItem;
import net.mehvahdjukaar.supplementaries.configs.CommonConfigs;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.IItemHandler;
import org.violetmoon.quark.addons.oddities.item.BackpackItem;
import org.violetmoon.zeta.event.load.ZGatherAdvancementModifiers;
import org.violetmoon.zetaimplforge.api.GatherAdvancementModifiersEvent;

import java.util.Set;

public class QuarkCompatImpl {


    public static float getEncumbermentFromBackpack(ItemStack stack) {
        float j = 0;
        if (stack.getItem() instanceof BackpackItem) {
            LazyOptional<IItemHandler> handlerOpt = stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null);
            if (handlerOpt.isPresent()) {
                IItemHandler handler = handlerOpt.resolve().get();
                for (int i = 0; i < handler.getSlots(); ++i) {
                    ItemStack slotItem = handler.getStackInSlot(i);
                    j += SackItem.getEncumber(slotItem);
                }
            }
        }
        return j;
    }
}
