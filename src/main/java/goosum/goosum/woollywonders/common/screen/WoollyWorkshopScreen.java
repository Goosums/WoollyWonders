package goosum.goosum.woollywonders.common.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import goosum.goosum.woollywonders.WoollyWonders;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WoollyWorkshopScreen extends AbstractContainerScreen<WoollyWorkshopMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(WoollyWonders.MODID, "textures/gui/container/woolly_workshop_gui.png");

    public WoollyWorkshopScreen(WoollyWorkshopMenu woollyWorkshopMenu, Inventory pInventory, Component pComponent) {
        super(woollyWorkshopMenu, pInventory, pComponent);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(pPoseStack, x, y);
    }

    private void renderProgressArrow(PoseStack pPoseStack, int pX, int pY) {
        if(menu.isCrafting()) {
            blit(pPoseStack, pX + 90, pY + 34, 176, 0, menu.getScaledProgress(), 14);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pDelta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pDelta);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }
}
