package ciyuanwutuobang.Check;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * @author 150149
 */
public class CheckTorch {

    public static boolean HasTorch(Block block) {
        if (!block.getLocation().add(1,0,0).getBlock().getType().equals(Material.TORCH)) {
            if (!block.getLocation().add(-1,0,0).getBlock().getType().equals(Material.TORCH)) {
                if (!block.getLocation().add(0,1,0).getBlock().getType().equals(Material.TORCH)) {
                    if (!block.getLocation().add(0,-1,0).getBlock().getType().equals(Material.TORCH)) {
                        if (!block.getLocation().add(0,0,1).getBlock().getType().equals(Material.TORCH)) {
                            if (!block.getLocation().add(0,0,-1).getBlock().getType().equals(Material.TORCH)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
