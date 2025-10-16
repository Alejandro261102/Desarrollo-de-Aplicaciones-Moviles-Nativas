/*
 * Nombre del archivo: MainActivity.kt
 * Descripción: Actividad principal de la aplicación. Implementa tres funcionalidades:
 *  - Control de la linterna del dispositivo.
 *  - Activación de una vibración corta.
 *  - Envío de notificaciones diferidas.
 *
 * Autor: [Tu nombre o equipo de desarrollo]
 * Fecha: [Coloca la fecha de entrega o creación]
 */

package com.example.practica3

// Importaciones necesarias para manejo de permisos, cámara, vibración y notificaciones
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity" // Etiqueta para mensajes de Logcat
        private const val CHANNEL_ID = "CHANNEL_ID_1" // ID del canal de notificación
        private const val NOTIFICATION_ID = 1 // Identificador único para notificaciones
    }

    private lateinit var cameraManager: CameraManager // Controlador del hardware de cámara
    private var isFlashlightOn = false // Estado de la linterna (encendida/apagada)

    /*
     * ActivityResultLauncher para solicitar permiso de cámara en tiempo de ejecución.
     * Si el usuario niega el permiso, se muestra un mensaje informativo.
     */
    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) {
            Toast.makeText(this, "Permiso de cámara denegado. La linterna no funcionará.", Toast.LENGTH_LONG).show()
            Log.w(TAG, "Permiso de cámara denegado por el usuario.")
        } else {
            Log.d(TAG, "Permiso de cámara concedido.")
        }
    }

    /*
     * ActivityResultLauncher para solicitar permiso de notificaciones (Android 13+).
     * Si el permiso es denegado, la app no podrá mostrar notificaciones.
     */
    private val requestNotificationPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) {
            Toast.makeText(this, "Permiso de notificaciones denegado. No se podrán mostrar notificaciones en Android 13+.", Toast.LENGTH_LONG).show()
            Log.w(TAG, "Permiso de notificaciones denegado por el usuario.")
        } else {
            Log.d(TAG, "Permiso de notificaciones concedido.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Asocia el layout XML con la actividad

        // Inicializa el administrador de cámara
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        // Solicita los permisos requeridos, si aún no han sido otorgados
        requestNecessaryPermissionsIfNeeded()

        // -------------------- LÓGICA DE LA LINTERNA --------------------
        val buttonFlashlight = findViewById<Button>(R.id.button_flashlight)
        buttonFlashlight.setOnClickListener {
            // Verifica permiso antes de acceder a la cámara
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestCameraPermission.launch(Manifest.permission.CAMERA)
                Toast.makeText(this, "Solicitando permiso de cámara...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cambia el estado de la linterna
            isFlashlightOn = !isFlashlightOn
            toggleFlashlight(isFlashlightOn)
            buttonFlashlight.text = if (isFlashlightOn) "Apagar" else "Encender"
        }

        // -------------------- LÓGICA DE VIBRACIÓN --------------------
        val buttonVibrate = findViewById<Button>(R.id.button_vibrate_short)
        buttonVibrate.setOnClickListener {
            vibratePhone()
        }

        // -------------------- LÓGICA DE NOTIFICACIONES --------------------
        createNotificationChannel() // Crea el canal de notificación (requerido en Android 8+)
        val buttonNotification = findViewById<Button>(R.id.button_send_notification)
        val editTextNotification = findViewById<EditText>(R.id.edittext_notification)

        buttonNotification.setOnClickListener {
            val message = editTextNotification.text.toString().ifBlank { "Recordatorio" }

            // Verifica permiso de notificaciones en Android 13+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                Toast.makeText(this, "Solicitando permiso de notificaciones...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Programa la notificación con un retraso de 5 segundos
            Handler(Looper.getMainLooper()).postDelayed({
                sendNotification(message)
            }, 5000)
        }

        Log.d(TAG, "onCreate finalizado correctamente.")
    }

    /*
     * Verifica y solicita los permisos necesarios para la cámara y notificaciones,
     * dependiendo de la versión de Android.
     */
    private fun requestNecessaryPermissionsIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestCameraPermission.launch(Manifest.permission.CAMERA)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    /*
     * Enciende o apaga la linterna del dispositivo.
     * Se prioriza el uso de la cámara trasera con flash disponible.
     */
    private fun toggleFlashlight(turnOn: Boolean) {
        try {
            // Busca una cámara con flash, preferiblemente la trasera
            val cameraIdWithFlash = cameraManager.cameraIdList.firstOrNull { id ->
                val characteristics = cameraManager.getCameraCharacteristics(id)
                val hasFlash = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
                val lensFacing = characteristics.get(CameraCharacteristics.LENS_FACING)
                val isBack = lensFacing == CameraCharacteristics.LENS_FACING_BACK
                hasFlash && isBack
            } ?: cameraManager.cameraIdList.firstOrNull { id ->
                cameraManager.getCameraCharacteristics(id)
                    .get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
            }

            if (cameraIdWithFlash == null) {
                Toast.makeText(this, "No hay cámara con flash disponible en este dispositivo.", Toast.LENGTH_LONG).show()
                Log.w(TAG, "No hay cámara con flash disponible.")
                return
            }

            // Verifica el permiso antes de manipular la cámara
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Falta permiso de cámara.", Toast.LENGTH_SHORT).show()
                Log.w(TAG, "Intento de encender linterna sin permiso de cámara.")
                return
            }

            // Activa o desactiva el modo linterna
            cameraManager.setTorchMode(cameraIdWithFlash, turnOn)
            Log.d(TAG, "Linterna alternada: setTorchMode($cameraIdWithFlash, $turnOn)")
        } catch (se: SecurityException) {
            Log.e(TAG, "Permiso de cámara faltante: ${se.message}")
            Toast.makeText(this, "Permiso de cámara no concedido.", Toast.LENGTH_SHORT).show()
        } catch (iae: IllegalArgumentException) {
            Log.e(TAG, "Error en setTorchMode: ${iae.message}")
            Toast.makeText(this, "Error al acceder al flash.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(TAG, "Error inesperado al alternar linterna: ${e.message}")
            Toast.makeText(this, "Error inesperado al usar la linterna.", Toast.LENGTH_SHORT).show()
        }
    }

    /*
     * Activa una vibración corta (200 ms) en el dispositivo.
     * Compatible con versiones modernas y antiguas de Android.
     */
    private fun vibratePhone() {
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            // Crea un patrón de vibración dependiendo de la versión de Android
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(200)
            }

            Log.d(TAG, "Vibración ejecutada correctamente (200 ms).")
        } catch (e: Exception) {
            Log.e(TAG, "Error al vibrar: ${e.message}")
            Toast.makeText(this, "No fue posible activar la vibración.", Toast.LENGTH_SHORT).show()
        }
    }

    /*
     * Crea un canal de notificación (requerido desde Android 8.0)
     * para poder mostrar notificaciones de tipo recordatorio.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Canal de Recordatorios"
            val descriptionText = "Canal utilizado para notificaciones simples de recordatorio"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            Log.d(TAG, "Canal de notificación creado correctamente.")
        }
    }

    /*
     * Envía una notificación con el mensaje proporcionado por el usuario.
     * Si el permiso no está otorgado (Android 13+), la operación se cancela.
     */
    private fun sendNotification(message: String) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Log.w(TAG, "Intento de enviar notificación sin permiso.")
                Toast.makeText(this, "Permiso de notificaciones no concedido.", Toast.LENGTH_SHORT).show()
                return
            }

            // Construcción de la notificación
            val smallIcon = R.mipmap.ic_launcher
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(smallIcon)
                .setContentTitle("Recordatorio")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, builder.build())

            Log.d(TAG, "Notificación enviada exitosamente: $message")
            Toast.makeText(this, "Notificación enviada", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(TAG, "Error al enviar notificación: ${e.message}")
            Toast.makeText(this, "No fue posible enviar la notificación.", Toast.LENGTH_SHORT).show()
        }
    }
}
