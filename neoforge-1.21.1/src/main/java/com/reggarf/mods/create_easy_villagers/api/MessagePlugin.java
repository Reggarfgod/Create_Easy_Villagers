package com.reggarf.mods.create_easy_villagers.api;

import com.reggarf.mods.better_lib.message.api.JoinMessagePlugin;
import com.reggarf.mods.better_lib.message.api.JoinMessagePlugins;
import com.reggarf.mods.better_lib.message.api.JoinMessageSet;
import com.reggarf.mods.create_easy_villagers.Create_easy_villagers;
import com.reggarf.mods.create_easy_villagers.config.CreateEasyVillagersConfig;

import java.util.List;

public class MessagePlugin implements JoinMessagePlugin {

    @Override
    public String getModId() {
        return Create_easy_villagers.MODID;
    }

    @Override
    public boolean enabled() {
        return CreateEasyVillagersConfig.areMessagesEnabled();
    }

    @Override
    public List<JoinMessageSet> getMessageSets() {
        return List.of(
                new JoinMessageSet()
                        .addText(
                                "Thank you for using create: easy villagers! recent updates improve stability, syncing, and goggles accuracy.",
                                0xFFFFFF
                        )
                        .addLink(
                                "(support & updates)",
                                "https://discord.gg/kb6BntpcYq",
                                0x5599FF,
                                "discord server • announcements • help"
                        )
                        .addLink(
                                "(zap-hosting)",
                                "https://zap-hosting.com/reggarf",
                                0x00FFFF,
                                "20% off game servers with code reggarf-1047"
                        )
                        .addLink(
                                "(github / issues)",
                                "https://github.com/Reggarfgod/Create_Easy_Villagers/issues",
                                0xA9A9A9,
                                "bug reports, suggestions, and tracking"
                        )
                        .addLink(
                                "(config)",
                                "",
                                0xF7C742,
                                "this message shows once and can be disabled in the config"
                        )
        );
    }

    public static void register() {
        JoinMessagePlugins.register(new MessagePlugin());
    }
}
