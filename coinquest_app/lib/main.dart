import 'package:flutter/material.dart';
import 'views/login_view.dart';

void main() {
  runApp(const CoinQuestApp());
}

class CoinQuestApp extends StatelessWidget {
  const CoinQuestApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CoinQuest',
      debugShowCheckedModeBanner: false, // tira a faixa de debug do canto
      theme: ThemeData(
        brightness: Brightness.dark, // Define o tema como escuro
        primaryColor: const Color(0xff00e676), 
        scaffoldBackgroundColor: const Color(0xff121212), // Fundo do app
      ),
      home: const LoginView(), // Define a tela de login como a inicial
    );
  }
}


