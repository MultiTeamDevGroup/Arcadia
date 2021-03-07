package multiteam.arcadia.setup.entitys.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class CloudPoofParticle extends SpriteTexturedParticle {

    protected CloudPoofParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);

        float f = this.rand.nextFloat() * 1.0f;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.setSize(0.02f,0.02f);
        this.particleScale *= this.rand.nextFloat()*1.1f;
        this.motionX *= (double)0.2f;
        this.motionY *= (double)0.2f;
        this.motionZ *= (double)0.2f;
        this.maxAge = (int)(14.0D / (Math.random() * 1.0D));

    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if(this.maxAge-- <=0){
            this.setExpired();
        }else{
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *=1.0D;
            this.motionY *=1.0D;
            this.motionZ *=1.0D;

        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType>{
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite){
            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            CloudPoofParticle cloudPoofParticle = new CloudPoofParticle(worldIn, x, y, z);
            cloudPoofParticle.setColor(1.0f, 1.0f, 1.0f);
            cloudPoofParticle.selectSpriteRandomly(this.spriteSet);
            return cloudPoofParticle;
        }
    }
}
