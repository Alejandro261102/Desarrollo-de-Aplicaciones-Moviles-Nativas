# README Técnico: App "Kit de Herramientas"

## Nombres Descriptivos

- **Nombre de la App:** Kit de Herramientas Sencillo
- **Paquete:** `com.example.practica3`
- **Clase Principal:** `MainActivity.kt`

## Estructura y Arquitectura

Dado el alcance enfocado y la simplicidad de esta aplicación, se ha optado por una arquitectura simple contenida directamente dentro de la `MainActivity`. Esta decisión se tomó para centrar el desarrollo en la interacción directa con las APIs nativas de Android sin añadir capas de abstracción innecesarias.

- **Componentes:** La lógica de la interfaz de usuario y la interacción con los recursos del sistema (Cámara, Vibrador, Notificaciones) están gestionadas en `MainActivity.kt`.
- **Layout:** La interfaz se define en un único archivo XML, `activity_main.xml`.

Aunque no se implementó un patrón como MVVM o Clean Architecture por la escala del proyecto, se entiende que para una aplicación más compleja, la separación de responsabilidades sería crucial. Por ejemplo, en un patrón **MVVM**:

- **Model:** No aplica directamente, pero podría ser una clase que gestione el estado de la linterna.
- **View:** `MainActivity.kt` y `activity_main.xml` serían la vista.
- **ViewModel:** Una clase `ToolkitViewModel` manejaría el estado (`isFlashlightOn`) y la lógica para interactuar con los servicios del sistema, exponiendo los datos a la `View` a través de `LiveData` o `StateFlow`.
