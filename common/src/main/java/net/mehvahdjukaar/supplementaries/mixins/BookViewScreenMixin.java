package net.mehvahdjukaar.supplementaries.mixins;

import net.mehvahdjukaar.supplementaries.common.block.IAntiquable;
import net.mehvahdjukaar.supplementaries.reg.ModTextures;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BookViewScreen.class)
public abstract class BookViewScreenMixin {

    @Shadow
    private BookViewScreen.BookAccess bookAccess;

    @Shadow
    protected abstract void init();

    @Shadow
    private PageButton forwardButton;

    @Shadow
    private PageButton backButton;

    @ModifyArg(method = "renderBackground", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    public ResourceLocation setTatteredBookTexture(ResourceLocation resourceLocation) {
        IAntiquable antiquable = ((IAntiquable) (Object) this.bookAccess);
        if (antiquable.supplementaries$isAntique()) {
            ((IAntiquable) this.forwardButton).supplementaries$setAntique(true);
            ((IAntiquable) this.backButton).supplementaries$setAntique(true);
            return ModTextures.TATTERED_BOOK_GUI_TEXTURE;
        }
        return resourceLocation;
    }
}
