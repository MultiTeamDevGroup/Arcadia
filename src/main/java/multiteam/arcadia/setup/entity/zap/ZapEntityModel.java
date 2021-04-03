package multiteam.arcadia.setup.entity.zap;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ZapEntityModel extends EntityModel<ZapEntity> {
    private final ModelRenderer LeftLeg;
    private final ModelRenderer cube_r1;
    private final ModelRenderer Body;
    private final ModelRenderer RightLeg;
    private final ModelRenderer cube_r2;

    public ZapEntityModel() {
        texWidth = 64;
        texHeight = 64;

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setPos(3.0F, 19.0F, 0.0F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(-2.0F, 5.0F, 0.0F);
        LeftLeg.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, -0.1745F, 0.0F);
        cube_r1.texOffs(0, 7).addBox(0.0F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, false);
        cube_r1.texOffs(0, 0).addBox(0.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);

        Body = new ModelRenderer(this);
        Body.setPos(0.0F, 14.5F, 0.0F);
        Body.texOffs(0, 4).addBox(-5.0F, -5.5F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
        Body.texOffs(0, 12).addBox(0.0F, -10.5F, 5.0F, 0.0F, 16.0F, 12.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setPos(-3.0F, 19.0F, 0.0F);


        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(3.0F, 5.0F, 0.0F);
        RightLeg.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.1309F, 0.0F);
        cube_r2.texOffs(0, 7).addBox(-5.0F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, false);
        cube_r2.texOffs(0, 0).addBox(-4.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(ZapEntity zapEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.LeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.RightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.LeftLeg.yRot = 0.0F;
        this.RightLeg.yRot = 0.0F;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        Body.render(matrixStack, buffer, packedLight, packedOverlay);
        RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

}
