# SecurityChecksIntegration

Este repositório contém:

- Uma biblioteca Kotlin com detecções de root, Magisk, emulador, debugger, Frida/Xposed e Play Integrity API.
- Um app de exemplo MAUI usando a biblioteca compilada como `.aar`.

## Instruções

### 1. Compilar a biblioteca Android

Abra `android-library` no Android Studio e compile como `.aar`. Coloque o `.aar` em:

```
example-maui/Platforms/Android/Bindings/
```

### 2. Abrir o exemplo MAUI

Abra `example-maui` no Visual Studio. Garanta que o `.csproj` referencie o `.aar` corretamente.

### 3. Configuração extra

No `AndroidManifest.xml`, adicione:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

Instale a dependência no Android Studio:

```gradle
implementation 'com.google.android.play:integrity:1.3.0'
```

### 4. Clonar

Se desejar subir este conteúdo ao seu GitHub:

```bash
git init
git add .
git commit -m "Initial version"
git remote add origin https://github.com/SEU_USUARIO/SecurityChecksIntegration.git
git push -u origin master
```
