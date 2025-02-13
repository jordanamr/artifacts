package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.PanicNecklaceModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class PanicNecklaceItem extends CurioItem {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Artifacts.MODID, "textures/entity/curio/panic_necklace.png");

    public PanicNecklaceItem() {
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurt);
    }

    public void onLivingHurt(LivingHurtEvent event) {
        if (!event.getEntity().world.isRemote && event.getAmount() >= 1) {
            if (CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).isPresent()) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SPEED, 160, 0, false, false));
            }
        }
    }

    @Override
    protected SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected BipedModel<LivingEntity> createModel() {
        return new PanicNecklaceModel();
    }

    @Override
    protected ResourceLocation getTexture() {
        return TEXTURE;
    }
}
