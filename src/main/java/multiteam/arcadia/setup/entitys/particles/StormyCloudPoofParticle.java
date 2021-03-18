package multiteam.arcadia.setup.entitys.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class StormyCloudPoofParticle extends SpriteTexturedParticle {

    protected StormyCloudPoofParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);

        float f = this.rand.nextFloat();
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.setSize(0.02f,0.02f);
        this.particleScale *= this.rand.nextFloat()*1.1f;
        this.motionX *= 0.2f;
        this.motionY *= 0.2f;
        this.motionZ *= 0.2f;
        this.maxAge = (int)(20.0D / (Math.random()));

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
            this.motionX = this.motionX + ((Math.random() * (0.1f - -0.1f)) + -0.1f);//Math.random() * (max - min) + min
            this.motionY = this.motionY + ((Math.random() * (0.03f - 0f)) + 0f);
            this.motionZ = this.motionZ + ((Math.random() * (0.1f - -0.1f)) + -0.1f);
            this.move(this.motionX, this.motionY, this.motionZ);
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
            StormyCloudPoofParticle stormyCloudPoofParticle = new StormyCloudPoofParticle(worldIn, x, y, z);
            stormyCloudPoofParticle.setColor(1.0f, 1.0f, 1.0f);
            stormyCloudPoofParticle.selectSpriteRandomly(this.spriteSet);
            return stormyCloudPoofParticle;
        }
    }
}
