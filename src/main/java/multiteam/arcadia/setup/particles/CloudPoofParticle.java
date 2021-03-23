package multiteam.arcadia.setup.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
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
        this.xo = 0.2f;
        this.yo = 0.2f;
        this.zo = 0.2f;
        this.age = (int)(20.0D / (Math.random()));
        //x  = prev pos
        //xo = position
        //xd = motion
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.y;
        if(this.age-- <=0){
            this.remove();
        }else{
            this.xd = this.xd + ((Math.random() * (0.1f - -0.1f)) + -0.1f);//Math.random() * (max - min) + min
            this.yd = this.yd + ((Math.random() * (0.03f - 0f)) + 0f);
            this.zd = this.zd + ((Math.random() * (0.1f - -0.1f)) + -0.1f);
            this.move(this.xd, this.yd, this.zd);
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
