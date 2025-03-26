package cordova.kathya.examen2_cordovakathya

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NuevoContacto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_contacto)

        val etNombres = findViewById<EditText>(R.id.et_nombres)
        val etApellidos = findViewById<EditText>(R.id.et_apellidos)
        val etTelefono = findViewById<EditText>(R.id.et_telefono)
        val etCorreo = findViewById<EditText>(R.id.et_correo)
        val etCompania = findViewById<EditText>(R.id.et_compañia)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombre = etNombres.text.toString()
            val apellido = etApellidos.text.toString()
            val telefono = etTelefono.text.toString()
            val correo = etCorreo.text.toString()
            val compania = etCompania.text.toString()

            val intent = Intent()

            intent.putExtra("nombre", nombre)
            intent.putExtra("apellido", apellido)
            intent.putExtra("telefono", telefono)
            intent.putExtra("correo", correo)
            intent.putExtra("compania", compania)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}