Aventura Interactiva — README
Descripción
Aventura Interactiva es una aplicación de juego narrativo para Android que demuestra una arquitectura de navegación jerárqug.ca entre múltiples Activities y Fragments. La aplicación permite a los usuarios tomar decisiones que ramifican la historia, llevando a múltiples finales.

El proyecto también implementa funcionalidades modernas de Android, como la persistencia de datos con SharedPreferences para guardar la preferencia de tema del usuario (claro u oscuro) y el uso de Drawable Resources dinámicos para cambiar la apariencia de la aplicación según el tema seleccionado.

Funcionalidades Clave:

Navegación Jerárquica: Tres niveles de profundidad (World > Region > Ending).

Narrativa Ramificada: Las decisiones en la pantalla principal y en las regiones llevan a 4 finales distintos.

Temas Dinámicos: Implementación de un modo claro y un modo oscuro, seleccionables por el usuario.

Persistencia de Tema: La elección del tema se guarda con SharedPreferences y persiste entre sesiones.

Fondos Dinámicos: El fondo de cada pantalla cambia según el tema seleccionado gracias a Drawable Resources con el calificador -night.

Animaciones: Transiciones personalizadas entre Activities y Fragments para una experiencia de usuario más fluida.

Objetivo: Servir como un proyecto de demostración para explorar conceptos de desarrollo nativo en Android, incluyendo la gestión de estado de la UI, la navegación compleja, la persistencia de datos simple y el manejo avanzado de recursos.

Estructura del Repositorio
/README.md
/app/
  /src/main/
    /java/com/example/juegonarrativoaventura/
      /model/          # Clases de datos (PointOfInterest.kt)
      /ui/             # Activities y Fragments por cada nivel
        /world/
        /region/
        /endings/
      /utils/          # Clases de utilidad (ThemeManager.kt)
    /res/
      /drawable/       # Recursos gráficos para el tema claro y alias XML
      /drawable-night/ # Alias XML para los recursos del tema oscuro
      /layout/         # Archivos de UI (Activities y Fragments)
      /values/         # colors.xml, strings.xml, themes.xml
      /anim/           # Animaciones de transición
  build.gradle
...
Requisitos Previos
Android Studio (Hedgehog 2023.1.1 o superior recomendado).

Kotlin (versión 1.8.0 o superior).

JDK (Amazon Corretto o OpenJDK 17), configurado para Android Studio.

Android SDK y Android SDK Command-line Tools instalados.

Un Emulador Android (AVD) configurado en Android Studio o un dispositivo Android físico con la Depuración USB habilitada.

Git (opcional, pero recomendado para el control de versiones).

Cómo Ejecutar y Probar la Aplicación
Abre el proyecto en Android Studio usando la opción Open an existing project y seleccionando la carpeta raíz del proyecto.

Espera a que Android Studio sincronice las dependencias de Gradle. Si aparece alguna notificación, acepta las sugerencias para sincronizar.

Selecciona un dispositivo o emulador disponible en la barra de herramientas superior.

Ejecuta la aplicación presionando el botón Run 'app' (o usando el atajo Shift + F10).

Comportamiento Esperado
Pantalla Principal (Mundo):

La app inicia mostrando dos opciones de camino ("Bosque" y "Montañas").

Un Switch en la esquina superior derecha permite cambiar entre el tema claro y el oscuro. Al cambiar, la aplicación se reinicia para aplicar el nuevo tema.

El fondo cambia según el tema seleccionado.

Pantallas de Región (Nivel 2):

Al elegir un camino, navegas a una nueva pantalla (ForestRegionActivity o MountainRegionActivity) con una transición animada.

Cada pantalla de región presenta dos nuevas decisiones.

Un botón "Reiniciar Aventura" te devuelve a la pantalla principal, limpiando el historial de navegación.

Pantallas de Final (Nivel 3):

Tomar una decisión en una región te lleva a una de las cuatro pantallas de final (EndingActivity), que muestra un texto descriptivo del resultado.

El botón de reinicio también está presente aquí.

Persistencia del Tema:

Selecciona un tema (ej. oscuro), cierra la aplicación completamente (desde las apps recientes del sistema) y vuelve a abrirla. La aplicación deberá iniciarse con el tema oscuro ya aplicado.

Dificultades Encontradas y Cómo Fueron Resueltas
El cambio de tema solo actualizaba los colores del texto, pero no las imágenes de fondo.

Solución: Se verificó que los archivos de layout (.xml) usaran el alias drawable (ej. @drawable/world_background) en el atributo android:background, en lugar de una imagen específica (ej. @drawable/bg_world_light). Esto permite que el sistema Android elija automáticamente el recurso correcto de las carpetas drawable o drawable-night.

Error de compilación Failed to parse XML file en los alias drawable.

Solución: El error se debía a una sintaxis incorrecta en los archivos XML de alias. Se corrigió asegurando que el contenido fuera exactamente <bitmap xmlns:android="..." android:src="@drawable/nombre_imagen" /> y que la imagen referenciada existiera en la carpeta res/drawable.

La estructura de archivos drawable y drawable-night era confusa en la vista de Android Studio.

Solución: Se comprendió que la vista "Android" de Android Studio agrupa los recursos con el mismo nombre pero diferentes calificadores. Se confirmó que la estructura física de carpetas separadas en el explorador de archivos era la correcta.

El botón de "Reiniciar" no borraba las pantallas anteriores del historial.

Solución: Al crear el Intent para volver a WorldActivity, se añadieron los flags Intent.FLAG_ACTIVITY_CLEAR_TOP y Intent.FLAG_ACTIVITY_NEW_TASK para limpiar la pila de actividades y asegurar un reinicio limpio.

Hallazgos
El uso de alias drawable con el calificador -night es una técnica poderosa y eficiente para implementar fondos dinámicos que reaccionan a los temas sin necesidad de lógica en Kotlin.

SharedPreferences es una solución ideal para persistir configuraciones de usuario simples, como la preferencia de un tema, debido a su bajo coste y facilidad de implementación.

La correcta aplicación de un tema (setTheme()) debe ocurrir antes de setContentView() en el ciclo de vida de una Activity para que afecte a todos los elementos de la ventana.

La vista "Android" en el explorador de proyectos de Android Studio, aunque inicialmente confusa, agiliza el desarrollo al agrupar lógicamente los recursos con diferentes calificadores.
