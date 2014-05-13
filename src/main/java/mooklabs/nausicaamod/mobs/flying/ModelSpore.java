package mooklabs.nausicaamod.mobs.flying;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;

public class ModelSpore extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;

	public ModelSpore() {
		textureWidth = 16;
		textureHeight = 16;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 23F, 0F, 1, 1, 1);//the 23 makes it render on the ground :/
		Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Shape1.setTextureSize(16, 16);
		Shape1.mirror = false;
		float rotation = 45;


		if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
			 setRotation(Shape1, rotation, 0, 0);
			Shape2 = new ModelRenderer(this, 0, 0);
			Shape2.addBox(0F, 23F, 0F, 1, 1, 1);
			Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
			Shape2.setTextureSize(1, 1);
			Shape2.mirror = true;
			setRotation(Shape1, 0, rotation, 0);

			Shape3 = new ModelRenderer(this, 0, 0);
			Shape3.addBox(0F, 23F, 0F, 1, 1, 1);
			Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
			Shape3.setTextureSize(1, 1);
			Shape3.mirror = true;
			setRotation(Shape1, 0, 0, rotation);

		}
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
			Shape2.render(f5);
			Shape3.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}