package bagu_chan.nillo.item;

import bagu_chan.nillo.entity.Nillo;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AmuletItem extends Item {
    public AmuletItem(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResult interactLivingEntity(ItemStack p_42954_, Player p_42955_, LivingEntity p_42956_, InteractionHand p_42957_) {
        if (p_42956_ instanceof Nillo nillo) {
            if (p_42956_.isAlive() && nillo.isTame() && nillo.isOwnedBy(p_42955_)) {
                ItemStack itemstack = nillo.getAmuletItemStack().split(1);
                Player player = (Player) p_42955_;
                if (!itemstack.isEmpty() && !player.isCreative()) {
                    if (!player.getInventory().add(itemstack)) {
                        player.drop(itemstack, false);
                    }
                }
                player.swing(p_42957_);
                player.playSound(SoundEvents.ITEM_PICKUP, 1.0F, 1.25F);
                nillo.setAmuletItemStack(p_42954_.split(1));
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}
