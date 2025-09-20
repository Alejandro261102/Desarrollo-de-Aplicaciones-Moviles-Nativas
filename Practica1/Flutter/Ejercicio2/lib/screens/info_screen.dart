import 'package:flutter/material.dart';
import 'dart:async';

class InfoScreen extends StatefulWidget {
  const InfoScreen({super.key});

  @override
  State<InfoScreen> createState() => _InfoScreenState();
}

class _InfoScreenState extends State<InfoScreen> {
  double progress = 0.0;
  Timer? timer;

  void startProgress() {
    setState(() {
      progress = 0.0;
    });

    timer?.cancel();
    timer = Timer.periodic(const Duration(milliseconds: 150), (t) {
      setState(() {
        progress += 0.1;
        if (progress >= 1.0) {
          progress = 1.0;
          t.cancel();
        }
      });
    });
  }

  @override
  void dispose() {
    timer?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const Text("ℹ️ Elementos de información",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            const Text(
                "Presentan datos al usuario sin requerir entrada. Ejemplos: textos, imágenes y barras de progreso."),
            const SizedBox(height: 12),

            // Imagen
            Image.asset(
              "images/icon.png", // recuerda agregar un asset en pubspec.yaml
              width: 96,
              height: 96,
            ),

            const SizedBox(height: 16),

            // Barra de progreso
            LinearProgressIndicator(
              value: progress,
              minHeight: 10,
            ),

            const SizedBox(height: 12),

            ElevatedButton(
              onPressed: startProgress,
              child: const Text("Simular carga"),
            ),
          ],
        ),
      ),
    );
  }
}
