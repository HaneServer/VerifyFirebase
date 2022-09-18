package net.serveron.hane.verifyfirebase

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.QuerySnapshot
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class Join (var plugin: VerifyFirebase) : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val value = checkUser(event.player.name)
        plugin.logger.info("ユーザーネーム" + value?.name + " さんが入室しました")
        if (value != null) {
            event.player.sendMessage("はね鯖へようこそ！\n~ 現在のあなたの情報 ~\n| Name: ${value.name}\n| Level: ${value.level}")
        } else {
            event.player.kickPlayer("ユーザー情報の取得に失敗しました。ユーザー登録してください\nhtps://hanesansaikyou.com")
        }
    }

    fun checkUser(name: String): Value? {
        try {
            val future: ApiFuture<QuerySnapshot> =
                plugin.db.collection("users").whereEqualTo("Minecraft", name).get()
            val documents = future.get().documents

            val value = Value()
            value.name = documents[0].getString("Name")!!
            value.level = documents[0].getLong("Level")!!

            return value
        } catch (e: Exception) {
            plugin.logger.info(e.toString())
            return null
        }
    }

    class Value() {
        var name: String? = null
        var level: Long = 0
    }
}