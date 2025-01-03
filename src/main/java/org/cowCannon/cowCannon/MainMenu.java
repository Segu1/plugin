/*package org.cowCannon.cowCannon;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.mineacademy.fo.ItemUtil;
import org.mineacademy.fo.collection.StrictMap;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.MenuContainer;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.ButtonMenu;
import org.mineacademy.fo.menu.button.ButtonReturnBack;
import org.mineacademy.fo.menu.button.StartPosition;
import org.mineacademy.fo.menu.button.annotation.Position;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.model.MenuClickLocation;
import org.mineacademy.fo.remain.CompMaterial;

import com.comphenix.net.bytebuddy.asm.Advice.This;
import com.sk89q.worldedit.internal.expression.SlotTable;



public class MainMenu extends Menu {

    @Position(start = StartPosition.CENTER, value = -2)
    private final Button openPreferencesButton;
    @Position(start = StartPosition.CENTER)
    private final Button selectMobButton;
    @Position(start = StartPosition.CENTER, value = 2)
    private final Button getItemsButton;

    public MainMenu() {
        setSlotNumbersVisible();
        setTitle("&b&Main Menu");
        setSize(9 * 3);

        this.openPreferencesButton = new ButtonMenu(new PreferencesMenu(),
                CompMaterial.BUCKET, "&6&lOpen Preferences", "", "Click to open the menu");

        this.selectMobButton = new ButtonMenu(new MobsMenu(), CompMaterial.ENDER_DRAGON_SPAWN_EGG,
                "&c&lMobs spawn", "", "Click to open the menu");

        this.getItemsButton = new ButtonMenu(new GetItemMenus(), CompMaterial.GOLD_NUGGET, "&d&lFree items!",
                "", "Click to open the menu");
    }

    private class PreferencesMenu extends Menu {
        @Position(start = StartPosition.CENTER, value = -1)
    	private final Button clearInventoryButton; 
        @Position(start = StartPosition.CENTER, value = 1)
    	private final Button healthFillerButton; 
    	
        public PreferencesMenu() {
            super(MainMenu.this);
            ButtonReturnBack.setMaterial(CompMaterial.RED_BANNER);
            setTitle("&8Preferences");
            setSize(9 * 3);

           this.clearInventoryButton = new Button() {
                @Override
                public void onClickedInMenu(Player player, Menu menu, ClickType click) {
                    player.getInventory().clear();
                }

                @Override
                public ItemStack getItem() {
                    return ItemCreator.of(CompMaterial.LAVA_BUCKET, "&cClear your inventory").make();
                }
            };

            this.healthFillerButton = new Button() {
                @Override
                public void onClickedInMenu(Player player, Menu menu, ClickType click) {
                    player.setHealth(player.getMaxHealth());
                    restartMenu("&aRefilled health!");
                } //action to perform 

                @Override
                public ItemStack getItem() {
                    Player viewer = getViewer();
                    double health = (viewer != null) ? Math.round(viewer.getHealth()) : 0;
                    return ItemCreator.of(CompMaterial.GOLDEN_APPLE, "&aFill your health", "",
                            "Current health: &c" + health).glow(true).make();
                } //item to show, clicking it will execute onClicked defined action.
            };
        }

        @Override
        protected void onPostDisplay(Player viewer) {
            super.onPostDisplay(viewer);
            animate(20, new MenuRunnable() {
                private boolean toggle = true;

                @Override
                public void run() {
                    setItem(getCenterSlot(),
                            ItemCreator.of(toggle ? CompMaterial.RED_WOOL : CompMaterial.BLUE_WOOL).make());
                    toggle = !toggle;
                }
            });
        }
        @Override 
        protected String[] getInfo() {
        	return new String[] {"Click bucket to clear your inventory", "",
        			"Click Apple to refill your health"};
        };
    }
    
    private class MobsMenu extends MenuPagged<EntityType> {
    	
    	public MobsMenu() {
    		super(MainMenu.this, Arrays.asList(EntityType.values()).stream()
    				.filter(type -> type.isSpawnable())
    				.collect(Collectors.toList()));
    		
    		setTitle("&c&lMobs spawning");
    		setSize(9*6);
    	}

		@Override
		protected ItemStack convertToItemStack(EntityType item) {
			// TODO Auto-generated method stub  
			return ItemCreator.ofEgg(item, "&6" + ItemUtil.bountifyCapitalized(item.name()), "", 
					"Click to obtain an egg of this kind").make();
		}

		@Override
		protected void onPageClick(Player player, EntityType entityType, ClickType click) {
			// TODO Auto-generated method stub
			ItemCreator.ofEgg(entityType).give(player);
		}
    	
    }
    
    private class GetItemMenus extends MenuContainer{
    	
    	public GetItemMenus() {
			super(MainMenu.this);
			setSize(9 * 2);
			setTitle("&4&lContainer");
    	}

		@Override
		protected ItemStack getDropAt(int slot) {
			if(slot < 9) {
				return ItemCreator.ofPotion(PotionEffectType.BLINDNESS).make();
			}
			return NO_ITEM;
		}

		@Override
		protected void onMenuClose(StrictMap<Integer, ItemStack> items) {
			// TODO Auto-generated method stub
			
		}
		
		@Override 
		protected boolean isActionAllowed(MenuClickLocation location, int slot,
				@Nullable ItemStack clicked, @Nullable ItemStack cursor) {
			return slot <= 9;
		}
    	
    }

    private class Vip extends MenuContainer{

		@Override
		protected ItemStack getDropAt(int slot) {
			if(slot == 1) {
				return ItemStack.()
			}
			return null;
		}

		@Override
		protected void onMenuClose(StrictMap<Integer, ItemStack> items) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
}*/


