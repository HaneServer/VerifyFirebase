package net.serveron.hane.verifyfirebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.bukkit.plugin.java.JavaPlugin
import java.io.FileInputStream
import java.io.InputStream

class VerifyFirebase : JavaPlugin() {
    var plugin: JavaPlugin? = null
    lateinit var db: Firestore

    override fun onEnable() {
        // Plugin startup logic
        plugin = this

        saveDefaultConfig()

        val serviceAccount: InputStream = FileInputStream(config.getString("Path"))
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()
        FirebaseApp.initializeApp(options)
        db = FirestoreClient.getFirestore()

        server.pluginManager.registerEvents(Join(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}