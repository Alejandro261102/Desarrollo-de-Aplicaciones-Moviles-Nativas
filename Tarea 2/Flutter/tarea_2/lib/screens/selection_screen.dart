import 'package:flutter/material.dart';

class SelectionScreen extends StatefulWidget {
  const SelectionScreen({super.key});

  @override
  State<SelectionScreen> createState() => _SelectionScreenState();
}

class _SelectionScreenState extends State<SelectionScreen> {
  bool cb1 = false;
  bool cb2 = false;
  String radio = "—";
  bool sw = false;

  String get estado {
    final cbs = [
      if (cb1) "A",
      if (cb2) "B",
    ].join(", ");
    return "Estado: CheckBox=[$cbs] | Radio=$radio | Switch=${sw ? "ON" : "OFF"}";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text("✅ Elementos de selección",
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            const Text(
                "Permiten elegir opciones: múltiples (CheckBox), exclusivas (Radio) o activar/desactivar (Switch)."),
            const SizedBox(height: 12),

            // CheckBoxes
            CheckboxListTile(
              title: const Text("Opción A"),
              value: cb1,
              onChanged: (val) => setState(() => cb1 = val!),
            ),
            CheckboxListTile(
              title: const Text("Opción B"),
              value: cb2,
              onChanged: (val) => setState(() => cb2 = val!),
            ),

            // Radios
            Row(
              children: [
                Radio<String>(
                  value: "Sí",
                  groupValue: radio,
                  onChanged: (val) => setState(() => radio = val!),
                ),
                const Text("Sí"),
                Radio<String>(
                  value: "No",
                  groupValue: radio,
                  onChanged: (val) => setState(() => radio = val!),
                ),
                const Text("No"),
              ],
            ),

            // Switch
            SwitchListTile(
              title: const Text("Activar notificaciones"),
              value: sw,
              onChanged: (val) => setState(() => sw = val),
            ),

            // Estado
            const SizedBox(height: 12),
            Text(estado),
          ],
        ),
      ),
    );
  }
}
