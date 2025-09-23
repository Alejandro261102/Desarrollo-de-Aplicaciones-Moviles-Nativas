Practica 1 — README

Descripción
---------------------
Esta practica es una aplicación educativa que demuestra los elementos de interfaz de usuario (UI) más comunes en Android, implementada en dos versiones:

- Kotlin (Android nativo) — arquitectura clásica con una MainActivity y cinco Fragments: TextFields, Buttons, Selection, List e Info. Navegación con BottomNavigationView.
- Flutter (Dart) — réplica funcional de la misma app usando MaterialApp, Scaffold y BottomNavigationBar, con una pantalla (Widget) por cada fragment original.

Cada pantalla ofrece:
- Un título descriptivo.
- Ejemplos visuales y demostraciones interactivas.
- Comportamiento equivalente (ej. Toast → SnackBar, EditText → TextField, ProgressBar → LinearProgressIndicator).

Objetivo: mostrar y comparar cómo se construyen los mismos elementos UI en Android nativo y en Flutter, y facilitar la práctica y la documentación académica.

--------------------------------------
Estructura recomendada del repositorio
--------------------------------------
/README.md
/kotlin_uielements_app/    # Proyecto Android nativo (Kotlin)
  app/
  build.gradle
  ...
/flutter_uielements_app/   # Proyecto Flutter (Dart)
  lib/
    main.dart
    screens/
      textfields_screen.dart
      buttons_screen.dart
      selection_screen.dart
      list_screen.dart
      info_screen.dart
  pubspec.yaml
  ...


------------------
Requisitos previos
------------------
- Android Studio (2021.3 o superior recomendado).
- Flutter SDK (estable) y Dart (incluido con Flutter).
- JDK (Amazon Corretto o OpenJDK), configurado para Android Studio.
- Android SDK y Android SDK Command-line Tools instalados.
- Git (opcional, pero recomendado).
- Emulador Android (AVD) o dispositivo Android con Depuración USB.
- Para compilar Windows Desktop (opcional): Visual Studio con workload Desktop development with C++.


--------------------------------------------
Instalación y configuración (resumen rápido)
--------------------------------------------
1. Instalar Android Studio y abrir SDK Manager → en SDK Tools activar:
   - Android SDK Command-line Tools (latest)
   - Android SDK Platform-Tools
   - Android SDK Build-Tools

2. Instalar Flutter:
   - Descargar SDK y agregar flutter/bin a PATH.
   - Ejecutar en terminal:
     flutter doctor
     flutter doctor --android-licenses
   - Instalar plugins en Android Studio: Flutter y Dart.

3. (Opcional) Configurar VS Code con extensión Flutter.

4. Crear/confirmar un emulador en Device Manager o conectar un dispositivo físico.


------------------------------------
Cómo ejecutar y probar la aplicación
------------------------------------

A) Kotlin (Android nativo)
1. Abre Android Studio → Open an existing project → selecciona la carpeta kotlin_uielements_app.
2. Si faltan dependencias, deja que Android Studio sincronice Gradle.
3. Selecciona un dispositivo/emulador en la barra superior.
4. Ejecuta con Run o Shift + F10.
5. Comportamiento esperado:
   - App inicia en la pantalla TextFields.
   - Cambia entre fragments con la barra inferior.
   - TextFields: escribir nombre y presionar "Mostrar saludo" muestra resultado y limpia campo.
   - Buttons: presionar botón / ImageButton muestra Toast. FAB navega al fragment Lista.
   - Selection: cambia CheckBoxes, Radio y Switch; TextView muestra estado.
   - List: tocar un ítem muestra Toast y navega a Info.
   - Info: presionar "Simular carga" dispara la barra de progreso.

B) Flutter (Dart)
1. Abre flutter_uielements_app en Android Studio o VS Code.
2. Ejecuta en terminal:
   flutter pub get
   flutter devices
   flutter run
3. Si en la barra superior aparece Windows (desktop) y no tienes Visual Studio, cámbialo al emulador Android.
4. Comportamiento esperado (paralelo al Kotlin):
   - TextField con botón que genera saludo y limpia el campo.
   - Buttons usa SnackBar para mensajes; FAB puede cambiar tab.
   - Selection actualiza el texto de estado.
   - List es ListView.builder; al tocar muestra SnackBar y puede navegar a Info.
   - Info usa LinearProgressIndicator y Timer.periodic para simular carga.


------------------------------------------------
Dificultades encontradas y cómo fueron resueltas
------------------------------------------------
1. flutter doctor marcaba cmdline-tools missing / licencias Android
   - Solución: instalar Android SDK Command-line Tools y aceptar licencias con flutter doctor --android-licenses.

2. Error CMake / Visual Studio al correr en Windows
   - Solución: cambiar target al emulador Android o instalar Visual Studio con Desktop development with C++.

3. No existe conversor automático Kotlin → Flutter para UI
   - Solución: reescribir manualmente, mapear equivalencias: EditText→TextField, Toast→SnackBar, ProgressBar→LinearProgressIndicator.

4. Comunicación entre pantalla hija y tab
   - Solución: usar callback o GlobalKey para exponer selectTab(int) del MainPage en Flutter.

5. Assets y rutas
   - Solución: declarar imágenes en pubspec.yaml y ejecutar flutter pub get.

6. Limpieza y errores de gradle
   - Solución: flutter clean, flutter pub get, reiniciar IDE.


---------
Hallazgos
---------
- Flutter permite desarrollar UI cross-platform con rapidez (hot reload) pero requiere reescribir la UI.
- Modelo de estado: Flutter usa StatefulWidget; Android nativo usa View + listeners.
- Navegación: BottomNavigationView (Android) vs BottomNavigationBar (Flutter) son equivalentes conceptuales.
- Equivalencias útiles: Toast→SnackBar, Handler→Timer, EditText→TextField.
- Si no compilas para Windows, no necesitas Visual Studio, solo SDK Android.
- Depuración: usar emulador de Android Studio y flutter doctor para diagnosticar.

-----------
Evidencias
-----------
![Screenshot 1](../Imagenes/Screenshot_20250907_135758.png)
![Screenshot 2](../Imagenes/Screenshot_20250907_135848.png)
![Screenshot 3](../Imagenes/Screenshot_20250907_135913.png)
![Screenshot 4](../Imagenes/Screenshot_20250907_135941.png)
![Screenshot 5](../Imagenes/Screenshot_20250907_135952.png)
![Screenshot 6](../Imagenes/Screenshot_20250922_213251.png)
![Screenshot 7](../Imagenes/Screenshot_20250922_213407.png)
![Screenshot 8](../Imagenes/Screenshot_20250922_213424.png)
![Screenshot 9](../Imagenes/Screenshot_20250922_213432.png)
![Screenshot 10](../Imagenes/Screenshot_20250922_213446.png)

------------------------------------------------------
Autor
------------------------------------------------------
Alejandro Hernández Aranda
Estudiante de Ingeniería en Sistemas Computacionales - ESCOM
