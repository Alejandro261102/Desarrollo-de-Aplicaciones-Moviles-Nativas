import 'package:flutter/material.dart';

class ButtonsScreen extends StatelessWidget {
  const ButtonsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              "🖲️ Botones",
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            const Text(
              "Los botones permiten ejecutar acciones como enviar formularios o navegar.",
            ),
            const SizedBox(height: 16),

            // Botón normal
            ElevatedButton(
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text("Botón presionado")),
                );
              },
              child: const Text("Mostrar Toast"),
            ),

            const SizedBox(height: 16),

            // Botón de imagen
            IconButton(
              icon: const Icon(Icons.camera_alt),
              onPressed: () {
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text("ImageButton: ¡clic en el ícono!")),
                );
              },
            ),

            const Spacer(),

            // FloatingActionButton
            Align(
              alignment: Alignment.bottomRight,
              child: FloatingActionButton(
                onPressed: () {
                  // Cambiar a la pantalla Lista (índice 3)
                  DefaultTabController.of(context);
                  // Como la navegación es con bottomNav, lo hacemos desde MainPage
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text("Ir a Lista (simulado)")),
                  );
                },
                child: const Icon(Icons.list),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
