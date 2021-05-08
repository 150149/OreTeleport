package ciyuanwutuobang;

import ciyuanwutuobang.Check.CheckAirAndLiquid;
import ciyuanwutuobang.Check.CheckTorch;
import ciyuanwutuobang.Check.WorldCheck;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class OreTeleport extends JavaPlugin implements Listener {

    private Map<String, Boolean> IsOnline = new HashMap<>();
    public int X;
    public int Y;
    public static OreTeleport instance;
    public List<String> NotWorld;
    private int high;
    private int distance;
    private List<ItemStack> ores = new ArrayList<>();
    private ItemFactory it = this.getServer().getItemFactory();

    FileConfiguration config = getConfig();

    public static void debug(String s) {
        //Bukkit.getConsoleSender().sendMessage(s);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage("[OreTeleport]矿物传送插件已开启");
        Bukkit.getConsoleSender().sendMessage("[OreTeleport]作者：150149  QQ：1802796278");
        config.addDefault("偏移量X", "128");
        config.addDefault("偏移量Z", "128");
        config.addDefault("起作用的最高高度", "50");
        config.addDefault("起作用的最近距离", "3");
        config.addDefault("不生效的世界", new ArrayList<>());
        /*
        ItemStack a = new ItemStack(Material.getMaterial("DIAMOND_ORE"));
        config.addDefault("1", a);*/
        config.options().copyDefaults(true);
        saveConfig();

        X = Integer.parseInt(config.getString("偏移量X"));
        Y = Integer.parseInt(config.getString("偏移量Z"));
        high = Integer.parseInt(config.getString("起作用的最高高度"));
        distance = Integer.parseInt(config.getString("起作用的最近距离"));
        NotWorld = config.getStringList("不生效的世界");
        /*
        int i=1;
        while(config.contains(String.valueOf(i))) {
            if (config.isItemStack(String.valueOf(i))) {
                ores.add(config.getItemStack(String.valueOf(i)));
                debug("读取矿物:" + String.valueOf(i));
            }
            i++;
        }*/

        instance = this;
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[OreTeleport]矿物传送插件已关闭");
    }
/*
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if ("oresave".equalsIgnoreCase(cmd.getName())){
            debug("收到指令请求");
            Player p = (Player)sender;
            int i=1;
            while(this.getConfig().contains(String.valueOf(i))){
                i++;
            }
            debug("结束查找最后i=" + String.valueOf(i));
            this.getConfig().set(String.valueOf(i),p.getInventory().getItemInMainHand());
            debug("设置手上物品到内存完成");

            Material.addBlockMaterial(p.getLocation().add(0,-3,0).getBlock().getType());


            p.getLocation().add(0,-2,0).getBlock().setType(p.getLocation().add(0,-3,0).getBlock().getType());
            p.getLocation().add(0,-2,0).getBlock().set
            debug("方块替换完成");

            File file=new File(getDataFolder(),"config.yml");
            try{
                getConfig().save(file);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            debug("保存config完成");
            i=1;
            debug("设置i=1");
            if (!ores.isEmpty()) ores.clear();
            debug("清空ores完成");
            debug("存储完成");
            while(this.getConfig().contains(String.valueOf(i))) {
                debug("读取到i=" + String.valueOf(i) + "存在");
                if (this.getConfig().isItemStack(String.valueOf(i))) {
                    debug("读取到i为itemstack");
                    ores.add(this.getConfig().getItemStack(String.valueOf(i)));
                    debug("已添加");
                }
                i++;
            }

            debug("指令执行完成");
        }
        return true;
    }
*/
    public void Replace(Block block) {
        if ("STONE".equals(block.getType().toString())) {
            if (!CheckAirAndLiquid.HasAirAndLiquid(block)) {
                if (!CheckTorch.HasTorch(block)) {
                    Replace.ReplaceOre(block);
                }
            }
        }
        else if ("COAL_ORE".equals(block.getType().toString())) {
            debug("检测到煤炭" );
            if (!CheckAirAndLiquid.HasAirAndLiquid(block)) {
                if (!CheckTorch.HasTorch(block)) {
                    if (!"COAL_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
                        Replace.replaceStone(block);
                    }
                }
            }
        }
        else if ("DIAMOND_ORE".equals(block.getType().toString())) {
            debug("检测到钻石" );

            if (!CheckAirAndLiquid.HasAirAndLiquid(block)) {
                if (!CheckTorch.HasTorch(block)) {
                    if (!"DIAMOND_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
                        Replace.replaceStone(block);
                    }
                }
            }
        }
        else if ("EMERALD_ORE".equals(block.getType().toString())) {
            debug("检测到绿宝石" );
            if (!CheckAirAndLiquid.HasAirAndLiquid(block)) {
                if (!CheckTorch.HasTorch(block)) {
                    if (!"EMERALD_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
                        Replace.replaceStone(block);
                    }
                }
            }
        }
        else if ("GOLD_ORE".equals(block.getType().toString())) {
            debug("检测到金" );
            if (!CheckAirAndLiquid.HasAirAndLiquid(block)) {
                if (!CheckTorch.HasTorch(block)) {
                    if (!"GOLD_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
                        Replace.replaceStone(block);
                    }
                }
            }
        }
        else if ("IRON_ORE".equals(block.getType().toString())) {
            debug("检测到铁" );
            if (!CheckAirAndLiquid.HasAirAndLiquid(block)) {
                if (!CheckTorch.HasTorch(block)) {
                    if (!"IRON_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
                        Replace.replaceStone(block);
                    }
                }
            }
        }
        else if ("REDSTONE_ORE".equals(block.getType().toString())) {
            debug("检测到红石" );
            if (!CheckAirAndLiquid.HasAirAndLiquid(block)) {
                if (!CheckTorch.HasTorch(block)) {
                    if (!"REDSTONE_ORE".equals(block.getLocation().add(X,0,Y).getBlock().getType().toString())) {
                        Replace.replaceStone(block);
                    }
                }
            }
        }
        /*
        else {
            for (ItemStack s:ores) {
                Collection<ItemStack> i = block.getDrops();

                if (i.contains(s)) {
                    debug("检测到" + block.getType().toString());
                    if (!"AIR".equals(block.getLocation().add(1, 0, 0).getBlock().getType().toString())) {
                        if (!"AIR".equals(block.getLocation().add(-1, 0, 0).getBlock().getType().toString())) {
                            if (!"AIR".equals(block.getLocation().add(0, 1, 0).getBlock().getType().toString())) {
                                if (!"AIR".equals(block.getLocation().add(0, -1, 0).getBlock().getType().toString())) {
                                    if (!"AIR".equals(block.getLocation().add(0, 0, 1).getBlock().getType().toString())) {
                                        if (!"AIR".equals(block.getLocation().add(0, 0, -1).getBlock().getType().toString())) {
                                            debug("对面的方块为:" + block.getLocation().getBlock().getType().toString());
                                            Collection<ItemStack> j = block.getLocation().add(X, 0, Y).getBlock().getDrops();
                                            if (!i.equals(j)) {
                                                block.setType(Material.getMaterial("STONE"));
                                                debug("替换石头成功");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        */
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        debug("检测到玩家:" + event.getPlayer().getName()+ "加入游戏");
        IsOnline.put(event.getPlayer().getName(),true);
        debug("已将玩家登陆状态: " + IsOnline.get(event.getPlayer().getName())+ "写入Isonline哈希map") ;
        BukkitTask time=new BukkitRunnable() {
            @Override
            public void run() {
                debug("开始执行判断!玩家"+ event.getPlayer().getName()+ "登陆状态" + IsOnline.get(event.getPlayer().getName()));
                if(!IsOnline.get(event.getPlayer().getName())) {
                    debug("判断!玩家"+ event.getPlayer().getName()+ "不在线,停止task");
                    this.cancel();
                    return;
                }
                debug("判断!玩家"+ event.getPlayer().getName() + "在线");

                if (event.getPlayer().getLocation().getY()<high) {
                    for (int i=2;i<=distance*2;i++) {
                        for (int j=2;j<=distance*2;j++) {
                            for (int k=2;k<=distance*2;k++) {
                                if (!"AIR".equals(event.getPlayer().getLocation().add(-distance,-distance,-distance).add(i,j,k).getBlock().getType().toString())) {
                                    if (WorldCheck.IsInWorld(event.getPlayer().getLocation().add(-distance,-distance,-distance).add(i,j,k).getBlock())) {
                                        Replace(event.getPlayer().getLocation().add(-distance,-distance,-distance).add(i,j,k).getBlock());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(this, 20,10);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

            IsOnline.put(event.getPlayer().getName(),false);

    }

    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        if (!WorldCheck.IsInWorld(event.getBlock())) {
            return;
        }

        if (event.getPlayer().getLocation().getY()<high) {
            debug("玩家 " + event.getPlayer().getName() + " 破坏的方块为 " + event.getBlock().getType().toString());

            if ("REDSTONE_ORE".equals(event.getBlock().getType().toString())) {
                if ("REDSTONE_ORE".equals(event.getBlock().getLocation().add(X,0,Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                    debug("破坏设置石头" );
                }
            }
            else if ("GLOWING_REDSTONE_ORE".equals(event.getBlock().getType().toString())) {
                if ("GLOWING_REDSTONE_ORE".equals(event.getBlock().getLocation().add(X,0,Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                    debug("破坏设置石头" );
                }
            }
            else if ("COAL_ORE".equals(event.getBlock().getType().toString())) {
                if ("COAL_ORE".equals(event.getBlock().getLocation().add(X,0,Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                    debug("破坏设置石头" );
                }
            }
            else if ("DIAMOND_ORE".equals(event.getBlock().getType().toString())) {
                if ("DIAMOND_ORE".equals(event.getBlock().getLocation().add(X,0,Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                    debug("破坏设置石头" );
                }
            }
            else if ("EMERALD_ORE".equals(event.getBlock().getType().toString())) {
                if ("EMERALD_ORE".equals(event.getBlock().getLocation().add(X,0,Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                    debug("破坏设置石头" );
                }
            }
            else if ("GOLD_ORE".equals(event.getBlock().getType().toString())) {
                if ("GOLD_ORE".equals(event.getBlock().getLocation().add(X,0,Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                    debug("破坏设置石头" );
                }
            }
            else if ("IRON_ORE".equals(event.getBlock().getType().toString())) {
                if ("IRON_ORE".equals(event.getBlock().getLocation().add(X,0,Y).getBlock().getType().toString())) {
                    event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                    debug("破坏设置石头" );
                }
            }
            /*
            else {
                for (ItemStack s:ores) {
                    ItemStack i = new ItemStack(event.getBlock().getType());
                    if (s.equals(i)) {
                        i = new ItemStack(event.getBlock().getLocation().add(X,0,Y).getBlock().getType());
                        if (s.equals(i)) {
                            event.getBlock().getLocation().add(X,0,Y).getBlock().setType(Material.getMaterial("STONE"));
                            debug("破坏设置石头" );
                        }
                    }
                }
            }*/
        }
    }
}
