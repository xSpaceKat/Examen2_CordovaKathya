package cordova.kathya.examen2_cordovakathya

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InformacionContacto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_contacto)

        val nombreApellido = findViewById<TextView>(R.id.tv_nombreApellidoInformacion)
        val compania = findViewById<TextView>(R.id.tv_compañiaInformacion)
        val correo = findViewById<TextView>(R.id.tv_correoInformacion)
        val telefono = findViewById<TextView>(R.id.tv_telefonoInformacion)
        val btnLlamar = findViewById<Button>(R.id.btn_Llamar)

        val extras = intent.extras
        if (extras != null) {
            val nombre = extras.getString("nombre", "")
            val apellido = extras.getString("apellido", "")
            val companiaText = extras.getString("compania", "")
            val correoText = extras.getString("correo", "")
            val telefonoText = extras.getString("telefono", "")

            nombreApellido.text = "$nombre $apellido"
            compania.text = companiaText
            correo.text = correoText
            telefono.text = telefonoText

            btnLlamar.text = "Llamar a $nombre"

            btnLlamar.setOnClickListener {
                val intent: Intent = Intent(this, Llamada::class.java)
                startActivity(intent)
            }
        }
    }
}