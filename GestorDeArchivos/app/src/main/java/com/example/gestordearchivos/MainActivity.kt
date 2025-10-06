import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.MimeTypeMap
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import com.example.gestordearchivos.R
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pathTextView: TextView // Usado como breadcrumb [cite: 34]
    private lateinit var fileAdapter: FileAdapter
    private lateinit var currentDirectory: File

    private val STORAGE_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        pathTextView = findViewById(R.id.pathTextView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (checkPermission()) {
            initializeFileManager()
        } else {
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        // Para Android 10 (API 29) y superior, READ_EXTERNAL_STORAGE es suficiente para Scoped Storage.
        // La práctica pide compatibilidad desde API 24, donde este permiso es clave.
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeFileManager()
            } else {
                Toast.makeText(this, "Permiso denegado. La app necesita acceso al almacenamiento.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initializeFileManager() {
        val rootPath = Environment.getExternalStorageDirectory()
        currentDirectory = rootPath
        displayFiles(currentDirectory)
    }

    private fun displayFiles(directory: File) {
        // Manejo de excepciones para rutas inaccesibles [cite: 45]
        try {
            currentDirectory = directory
            pathTextView.text = directory.absolutePath

            val files = directory.listFiles()
                ?.filter { !it.isHidden } // Ocultar archivos ocultos
                ?.sortedWith(compareBy({ !it.isDirectory }, { it.name.lowercase() })) // Ordenar: carpetas primero, luego alfabéticamente
                ?: emptyList()

            fileAdapter = FileAdapter(files) { file ->
                onFileClicked(file)
            }
            recyclerView.adapter = fileAdapter
        } catch (e: SecurityException) {
            Toast.makeText(this, "No se puede acceder a esta carpeta.", Toast.SHORT).show()
            // Vuelve al directorio padre si hay un error de acceso
            currentDirectory.parentFile?.let { displayFiles(it) }
        }
    }

    private fun onFileClicked(file: File) {
        if (file.isDirectory) {
            displayFiles(file) // Navegar a subdirectorio [cite: 21]
        } else {
            openFile(file) // Abrir archivo [cite: 24, 25]
        }
    }

    private fun openFile(file: File) {
        // Usar un Intent para abrir el archivo con la app apropiada
        val uri = FileProvider.getUriForFile(this, "${applicationContext.packageName}.provider", file)
        val intent = Intent(Intent.ACTION_VIEW)

        // Obtener el tipo MIME para el intent
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.extension)
        intent.setDataAndType(uri, mimeType ?: "*/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "No se encontró una aplicación para abrir este archivo.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (currentDirectory.absolutePath != Environment.getExternalStorageDirectory().absolutePath) {
            currentDirectory.parentFile?.let {
                displayFiles(it)
            }
        } else {
            super.onBackPressed()
        }
    }
}