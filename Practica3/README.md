# Proyecto Final: Desarrollo de Aplicaciones Móviles Nativas

Este repositorio contiene el desarrollo de las prácticas para la asignatura de Desarrollo de Aplicaciones Móviles Nativas.

## Descripción de las Aplicaciones

### Ejercicio 1: Gestor de Archivos (No realizado)

- **Descripción:** Aplicación para explorar y gestionar archivos y directorios en el almacenamiento del dispositivo.

### Ejercicio 2: Aplicación de Cámara y Micrófono (No realizado)

- **Descripción:** App para capturar fotos, grabar audio y gestionar los archivos multimedia resultantes en una galería integrada.

### Ejercicio 3: Kit de Herramientas Sencillo (Aplicación Entregada)

- **Descripción:** Una aplicación nativa de libre elección que demuestra el uso de recursos del sistema. Esta app funciona como una caja de herramientas simple que integra tres funcionalidades nativas clave de Android:
    1.  **Linterna:** Permite encender y apagar el flash de la cámara con un solo botón.
    2.  **Vibración:** Activa una vibración corta en el dispositivo para proporcionar feedback háptico.
    3.  **Notificaciones:** Permite al usuario escribir un mensaje y programar una notificación local que aparecerá en el sistema después de 5 segundos.

---

## Requisitos del Sistema

- **Android Studio:** Hedgehog | 2023.1.1 o superior.
- **Gradle Version:** 8.0 o superior.
- **API Mínima:** API 24 (Android 7.0 Nougat).

---

## Instrucciones de Instalación

### Método 1: Instalación vía APK

1.  Descarga el archivo `kit-de-herramientas.apk` desde la sección de "Releases" de este repositorio.
2.  En tu dispositivo Android, habilita la opción de "Instalar aplicaciones de fuentes desconocidas".
3.  Abre el archivo APK descargado y sigue las instrucciones para instalarlo.

### Método 2: Compilación desde Fuente

1.  Clona este repositorio: `git clone <URL_DEL_REPOSITORIO>`
2.  Abre el proyecto en Android Studio.
3.  Espera a que Gradle sincronice todas las dependencias.
4.  Ejecuta la aplicación en un emulador o en un dispositivo físico conectado.

---

## Permisos Requeridos y Justificación

La aplicación solicita los siguientes permisos para funcionar correctamente:

- **`android.permission.CAMERA`**
  - **Justificación:** Necesario para acceder al hardware de la cámara y controlar el flash para la función de linterna. La app no captura fotos ni videos.
- **`android.permission.VIBRATE`**
  - **Justificación:** Requerido para utilizar el vibrador del dispositivo y proporcionar feedback háptico al usuario.
- **`android.permission.POST_NOTIFICATIONS`**
  - **Justificación:** (Para Android 13+) Este permiso es indispensable para que la aplicación pueda mostrar notificaciones programadas en la barra de estado del sistema.
