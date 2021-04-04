package multiteam.arcadia.setup.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class CloudPoofParticle extends SpriteTexturedParticle {

    protected CloudPoofParticle(ClientWorld world, double x, double y, double z){
        super(world, x, y, z);

        float f = this.random.nextFloat();
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;
        this.setSize(0.02f,0.02f);
        this.quadSize *= this.random.nextFloat()*1.1f;
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.age = (int)(20.0D / (Math.random()));
        //x  = posX
        //xo = prevPosX
        //xd = motionX
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        if(this.age-- <=0){
            this.remove();
        }else{
            this.move(this.xd, this.yd, this.zd);
            this.xd *= (double)0.1F;
            this.yd *= (double)0.03F;
            this.zd *= (double)0.1F;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite sprite){
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            CloudPoofParticle cloudPoofParticle = new CloudPoofParticle(worldIn, x, y, z);
            cloudPoofParticle.setColor(1.0f, 1.0f, 1.0f);
            cloudPoofParticle.pickSprite(this.sprite);
            return cloudPoofParticle;
        }
    }

}
