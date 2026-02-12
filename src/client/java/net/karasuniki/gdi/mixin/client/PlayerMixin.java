package net.karasuniki.gdi.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.karasuniki.gdi.impl.IGoldenDandelionMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (!((LevelAccessor) this.level()).isClientSide())
            return;

        if (this.getMainHandItem().getItem().equals(Items.GOLDEN_DANDELION.asItem())) {
            this.level().getEntitiesOfClass(Animal.class, this.getBoundingBox().inflate(5.0)).forEach(animal -> {
                if (animal instanceof IGoldenDandelionMob baby && !animal.canAgeUp()) {
                    baby.showGoldenParticle();
                }
            });
        }
    }
}
