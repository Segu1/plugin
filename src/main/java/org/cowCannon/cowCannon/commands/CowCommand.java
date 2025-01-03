package org.cowCannon.cowCannon.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.cowCannon.cowCannon.Main.Keys;
import org.cowCannon.cowCannon.Main.CowSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CowCommand implements CommandExecutor , TabExecutor {


    public boolean spawnEntity(@NotNull LivingEntity livingEntity) {
        livingEntity.getPersistentDataContainer().set(Keys.CUSTOM_COW, PersistentDataType.BOOLEAN, true);
        livingEntity.setCustomName(ChatColor.RED + String.format("Exploding %s", livingEntity.getType().name()));
        livingEntity.setCustomNameVisible(true);
        return true;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }
       Player player = (Player) sender;

        if (args.length > 1 && args[0].equalsIgnoreCase("set")) {
            handleSetExplodingType(player, args[1]);
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("baby")) {
            return handleSpawnBaby(player);
        }

        return handleSpawnNormal(player);
    }

    private void handleSetExplodingType(@NotNull CommandSender sender, @NotNull String entityTypeName) {
        EntityType type;

        try {
            type = EntityType.valueOf(entityTypeName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            sender.sendMessage(ChatColor.RED + "Invalid entity type: " + entityTypeName);
            return;
        }

        if (!type.isAlive() || !type.isSpawnable()) {
            sender.sendMessage(ChatColor.RED + "You can't spawn that entity!");
            return;
        }

        CowSettings.getInstance().setExplodingType(type);
        CowSettings.getInstance().save();
        sender.sendMessage(ChatColor.GREEN + "Set exploding type to: " + type.name());
    }

    private boolean handleSpawnBaby(@NotNull Player player) {
        EntityType explodingType = CowSettings.getInstance().getExplodingType();
        if (!Ageable.class.isAssignableFrom(explodingType.getEntityClass())) {
            player.sendMessage(ChatColor.RED + "This entity cannot be spawned as a baby!");
            return true;
        }

        LivingEntity babyEntity = spawnEntity(player, explodingType);
        if (babyEntity instanceof Ageable ageableEntity) {
            ageableEntity.setBaby();
        }
        return true;
    }

    private boolean handleSpawnNormal(@NotNull Player player) {
        EntityType explodingType = CowSettings.getInstance().getExplodingType();
        spawnEntity(player, explodingType);
        return true;
    }

    private LivingEntity spawnEntity(@NotNull Player player, @NotNull EntityType explodingType) {
        LivingEntity entity = (LivingEntity) player.getWorld().spawn(
                player.getLocation(),
                Objects.requireNonNull(explodingType.getEntityClass())
        );
        spawnEntity(entity);
        return entity;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return Arrays.asList("baby");
        }

        if (args.length == 2) {
            String name = args[1].toUpperCase();
            return Arrays.stream(EntityType.values())
                    .filter(type -> type.isSpawnable() && type.isAlive() && type.name().startsWith(name))
                    .map(Enum::name)
                    .toList();
        }

        // Return null if no matching case
        return null;
    }}