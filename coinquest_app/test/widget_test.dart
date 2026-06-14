import 'package:flutter_test/flutter_test.dart';
import 'package:coinquest_app/main.dart';

void main() {
  testWidgets('Limpeza de teste padrão', (WidgetTester tester) async {
    await tester.pumpWidget(const CoinQuestApp());
  });
}
