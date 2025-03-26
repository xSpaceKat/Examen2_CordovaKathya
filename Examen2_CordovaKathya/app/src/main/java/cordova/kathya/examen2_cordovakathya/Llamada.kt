package cordova.kathya.examen2_cordovakathya

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.whenStarted

class Llamada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_llamada)

        val btnColgar = findViewById<Button>(R.id.btnColgar)

        btnColgar.setOnClickListener(){
            val intent: Intent = Intent(this, InformacionContacto::class.java)
            startActivity(intent)
        }
    }
}