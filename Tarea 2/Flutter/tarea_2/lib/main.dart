import 'package:flutter/material.dart';

// Importaremos nuestras pantallas (equivalentes a fragments)
import 'screens/textfields_screen.dart';
import 'screens/buttons_screen.dart';
import 'screens/selection_screen.dart';
import 'screens/list_screen.dart';
import 'screens/info_screen.dart';

void main() {
  runApp(const UIElementsApp());
}

class UIElementsApp extends StatelessWidget {
  const UIElementsApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Tarea 2',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MainPage(),
    );
  }
}

class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  State<MainPage> createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  int _selectedIndex = 0;

  // Lista de "fragments" → aquí meteremos cada pantalla
  final List<Widget> _screens = [
    const TextFieldsScreen(),
    const ButtonsScreen(),
    const SelectionScreen(),
    const ListScreen(),
    const InfoScreen(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _screens[_selectedIndex],
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        currentIndex: _selectedIndex,
        onTap: _onItemTapped,
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.edit), label: "TextFields"),
          BottomNavigationBarItem(icon: Icon(Icons.smart_button), label: "Botones"),
          BottomNavigationBarItem(icon: Icon(Icons.check_box), label: "Selección"),
          BottomNavigationBarItem(icon: Icon(Icons.list), label: "Lista"),
          BottomNavigationBarItem(icon: Icon(Icons.info), label: "Info"),
        ],
      ),
    );
  }
}
