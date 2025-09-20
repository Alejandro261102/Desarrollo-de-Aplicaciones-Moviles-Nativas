import 'package:flutter/material.dart';

class TextFieldsScreen extends StatefulWidget {
  const TextFieldsScreen({super.key});

  @override
  State<TextFieldsScreen> createState() => _TextFieldsScreenState();
}

class _TextFieldsScreenState extends State<TextFieldsScreen> {
  final TextEditingController _controller = TextEditingController();
  String _result = "Aquí verás el resultado";

  void _showGreeting() {
    setState(() {
      _result = "¡Hola, ${_controller.text}!";
      _controller.clear();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              "✏️ TextFields (EditText)",
              style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            const Text(
              "Permiten capturar texto del usuario, como nombre, correo o comentarios.",
            ),
            const SizedBox(height: 12),
            TextField(
              controller: _controller,
              decoration: const InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Escribe tu nombre",
              ),
            ),
            const SizedBox(height: 12),
            ElevatedButton(
              onPressed: _showGreeting,
              child: const Text("Mostrar saludo"),
            ),
            const SizedBox(height: 8),
            Text(
              _result,
              style: const TextStyle(fontSize: 16),
            ),
          ],
        ),
      ),
    );
  }
}
