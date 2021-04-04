package multiteam.arcadia.setup.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class ZapBoltParticle extends SpriteTexturedParticle {
    private final double xStart;
    private final double yStart;
    private final double zStart;

    protected ZapBoltParticle(ClientWorld cleintWorld, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(cleintWorld, x, y, z);

        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.xStart = x;
        this.yStart = y;
        this.zStart = z;
        this.xo = x + motionX;
        this.yo = y + motionY;
        this.zo = z + motionZ;
        this.x = this.xo;
        this.y = this.yo;
        this.z = this.zo;
        this.quadSize = 0.5f;
        this.setSize(0.02f,0.02f);
        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
        this.hasPhysics = false;
        this.lifetime = (int)(Math.random() * 10.0D) + 30;
        this.age = 0;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            f = 1.0F - f;
            float f1 = 1.0F - f;
            f1 = f1 * f1;
            f1 = f1 * f1;
            this.x = this.xStart + this.xd * (double)f;
            this.y = this.yStart + this.yd * (double)f - (double)(f1 * 1.2F);
            this.z = this.zStart + this.zd * (double)f;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(BasicParticleType basicParticleType, ClientWorld cleintWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ZapBoltParticle zapBoltParticle = new ZapBoltParticle(cleintWorld, x, y, z, xSpeed, ySpeed, zSpeed);
            zapBoltParticle.setColor(1.0f,1.0f,1.0f);
            zapBoltParticle.setAlpha(1.0f);
            zapBoltParticle.pickSprite(this.sprite);
            return zapBoltParticle;
        }
    }
}
