package com.asadbek.permissions

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.asadbek.permissions.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissions()

    }
    private fun permissions(){
        // SnacbarPermissionListener -> Dexter orqali so`ralayotgan ruxsat javobi ruxsat berilishi yoki aksi
        // ruxsat berilmasa ruxsat berilmadi xabar chiqadi
        val snackbarPermissionListener:PermissionListener =
            SnackbarOnDeniedPermissionListener.Builder
                .with(binding.txt1,"Ruxsatlar")
                .withOpenSettingsButton("Sozlamalar")
                .withCallback(object : Snackbar.Callback(){
                    override fun onShown(sb: Snackbar?) {
                        super.onShown(sb)
                        binding.holat.text = "Ruxsat berildi"
                    }

                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        binding.holat.text = "Ruxsat berilmadi"
                    }
                }).build()

        // Foydalanuvchi uchun so`rov, permission(lar)dan foydalanish uchun
        Dexter.withActivity(this)
            .withPermission(android.Manifest.permission.CAMERA) // foydalanuvchidan so`ralishi kerak bo`lgan ruxsatlar
            .withListener(snackbarPermissionListener) // ruxsat xolati
            .check() // permissionni tekshiriladi
    }
}