import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.gestordearchivos.R

class FileAdapter(private var files: List<File>, private val onFileClick: (File) -> Unit) :
    RecyclerView.Adapter<FileAdapter.FileViewHolder>() {

    class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = view.findViewById(R.id.iconImageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val detailsTextView: TextView = view.findViewById(R.id.detailsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.nameTextView.text = file.name

        // Asignar icono según el tipo de archivo
        holder.iconImageView.setImageResource(getFileIcon(file))

        // Mostrar detalles del archivo
        val date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(file.lastModified()))
        val size = formatFileSize(file.length())
        holder.detailsTextView.text = if (file.isDirectory) "$date" else "$size | $date"

        holder.itemView.setOnClickListener {
            onFileClick(file)
        }
    }

    override fun getItemCount() = files.size

    private fun getFileIcon(file: File): Int {
        if (file.isDirectory) {
            return R.drawable.ic_folder
        }
        return when (file.extension.lowercase(Locale.getDefault())) {
            "jpg", "jpeg", "png", "gif" -> R.drawable.ic_image
            "txt", "md", "log", "json", "xml" -> R.drawable.ic_text
            // Añade más extensiones y sus iconos aquí
            else -> R.drawable.ic_file
        }
    }

    private fun formatFileSize(size: Long): String {
        if (size <= 0) return "0 B"
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
        return String.format("%.1f %s", size / Math.pow(1024.0, digitGroups.toDouble()), units[digitGroups])
    }
}