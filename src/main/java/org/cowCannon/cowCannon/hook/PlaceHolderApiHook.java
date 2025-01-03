package org.cowCannon.cowCannon.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceHolderApiHook extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "cowcannon";
    }

    @Override
    public @NotNull String getAuthor() {
        return "segu";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.BETA";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params){
        if(player != null && player.isOnline()){
            Player onlinePlayer = player.getPlayer();
        }
        return null;
    };


}

