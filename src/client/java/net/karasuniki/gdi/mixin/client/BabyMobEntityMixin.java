package net.karasuniki.gdi.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import net.karasuniki.gdi.impl.IGoldenDandelionMob;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

@Mixin(Animal.class)
public abstract class BabyMobEntityMixin extends AgeableMob implements IGoldenDandelionMob {
    protected BabyMobEntityMixin(EntityType<? extends AgeableMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void showGoldenParticle() {
        if (((LevelAccessor) this.level()).getIsClientSide()) {
            this.level().addParticle(ParticleTypes.WAX_ON, this.getRandomX(0.5D), this.getRandomY() + 0.5D,
                    this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
        }
    }
}
