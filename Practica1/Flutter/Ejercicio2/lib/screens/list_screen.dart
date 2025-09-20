import 'package:flutter/material.dart';

class ListScreen extends StatelessWidget {
  const ListScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final items = ["Manzana", "Banana", "Naranja", "Fresa", "Mango"];

    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text("📋 Lista (ListView)",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            const Text(
                "Muestran colecciones de ítems desplazables. Toca un elemento para mostrar un detalle."),
            const SizedBox(height: 8),

            Expanded(
              child: ListView.builder(
                itemCount: items.length,
                itemBuilder: (context, index) {
                  return ListTile(
                    title: Text(items[index]),
                    onTap: () {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text("Seleccionaste: ${items[index]}")),
                      );
                      // Aquí podrías navegar al tab Info
                      // (Lo dejamos como SnackBar porque el cambio de tab se maneja desde MainPage)
                    },
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
