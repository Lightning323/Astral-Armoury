package org.lightning323.astral.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import org.lightning323.astral.client.I_OptionInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(OptionInstance.class)
public abstract class OptionInstanceMixin<T> implements I_OptionInstance<T> {

    @Shadow
    T value;

    @Shadow
    private Consumer<T> onValueUpdate;


    //Add a new method
    public void setUnchecked(T pValue) {
        if (!Minecraft.getInstance().isRunning()) {
            this.value = pValue;
        } else {
            if (!Objects.equals(this.value, pValue)) {
                this.value = pValue;
                this.onValueUpdate.accept(this.value);
            }
        }
//        System.out.println("Set value to " + this.value);
    }

}
