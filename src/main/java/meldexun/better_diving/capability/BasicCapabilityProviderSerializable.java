package meldexun.better_diving.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

public abstract class BasicCapabilityProviderSerializable<C> extends BasicCapabilityProvider<C> implements INBTSerializable<NBTBase> {

	public BasicCapabilityProviderSerializable(Capability<C> capability, C instance) {
		super(capability, instance);
	}

	@Override
	public NBTBase serializeNBT() {
		return this.capability.getStorage().writeNBT(this.capability, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		this.capability.getStorage().readNBT(this.capability, this.instance, null, nbt);
	}

}
