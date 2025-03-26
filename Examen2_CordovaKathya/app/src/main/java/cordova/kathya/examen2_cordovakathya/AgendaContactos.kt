package cordova.kathya.examen2_cordovakathya

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AgendaContactos : AppCompatActivity() {

    var adapter: ListContactosAdapter? = null
    var listaContactos = ArrayList<Contacto>()

    companion object {
        private const val REQUEST_CODE_NUEVO_CONTACTO = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_contactos)

        val agregarContacto = findViewById<Button>(R.id.btnAgregarContacto)
        val listView = findViewById<ListView>(R.id.listContactos)

        agregarContacto.setOnClickListener {
            val intent = Intent(this, NuevoContacto::class.java)
            startActivity(intent)
        }

        crearContacto()
        adapter = ListContactosAdapter(this, listaContactos, this::eliminarContacto)
        listView.adapter = adapter


        listView.setOnItemClickListener { _, _, position, _ ->
            val contacto = listaContactos[position]
            val intent = Intent(this, InformacionContacto::class.java).apply {
                putExtra("nombre", contacto.nombre)
                putExtra("apellido", contacto.apellido)
                putExtra("compania", contacto.compañia)
                putExtra("correo", contacto.correo)
                putExtra("telefono", contacto.telefono)
            }
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_NUEVO_CONTACTO && resultCode == RESULT_OK) {
            val nombre = data?.getStringExtra("nombre") ?: ""
            val apellido = data?.getStringExtra("apellido") ?: ""
            val telefono = data?.getStringExtra("telefono") ?: ""
            val correo = data?.getStringExtra("correo") ?: ""
            val compania = data?.getStringExtra("compania") ?: ""

            val nuevoContacto = Contacto(nombre, apellido, compania, correo, telefono)
            listaContactos.add(nuevoContacto)

            adapter?.notifyDataSetChanged()
        }
    }

    private fun crearContacto() {
        listaContactos.add(Contacto("Samuel", "Zamora", "Pemex", "chis@gmail.com", "2233384944"))
        listaContactos.add(Contacto("Mary", "Estrada", "Itson", "agua@gmail.com", "34566767454"))
        listaContactos.add(Contacto("Hector", "Beltran", "Caffenio", "adios@gmail.com", "4334345665767"))
        listaContactos.add(Contacto("Dalia", "Rosales", "Federal", "sopa@gmail.com", "77776754457"))
    }

    fun eliminarContacto(position: Int) {
        listaContactos.removeAt(position)
        adapter?.notifyDataSetChanged()
    }
}

class ListContactosAdapter(private val context: Context, private val contactos: ArrayList<Contacto>, private val eliminarContacto: (Int) -> Unit) : BaseAdapter() {

    override fun getCount(): Int = contactos.size

    override fun getItem(position: Int): Any = contactos[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_list_contactos, parent, false)
        val contacto = contactos[position]
        val nombreApellido = view.findViewById<TextView>(R.id.tv_nombreApellido)
        val compania = view.findViewById<TextView>(R.id.tv_compañia)
        val btnEliminar = view.findViewById<ImageButton>(R.id.btnEliminar)

        nombreApellido.text = "${contacto.nombre} ${contacto.apellido}"
        compania.text = contacto.compañia

        btnEliminar.setOnClickListener {
            eliminarContacto(position)
        }

        return view
    }
}