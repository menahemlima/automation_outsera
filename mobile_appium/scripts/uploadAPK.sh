echo "Instalando APK no emulador 5554..."
adb -s emulator-5554 install -t -g mobile_appium/src/test/resources/apps/Android_Appium_Demo.apk
echo "Verificando pacotes no emulador 5554:"
adb -s emulator-5554 shell pm list packages | grep appiumdemo || echo "Pacote não encontrado no emulador 5554"