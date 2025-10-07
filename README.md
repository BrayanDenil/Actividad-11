Retrofit + MVVM Android App – Productos
Estructura del proyecto
com.example.retrofit
 ├─ data
 │   ├─ model
 │   │   ├─ Product.kt
 │   │   └─ ProductRequest.kt
 │   ├─ remote
 │   │   ├─ ProductApi.kt
 │   │   └─ RetrofitInstance.kt
 │   └─ repository
 │       └─ ProductRepository.kt
 ├─ ui
 │   └─ products
 │       └─ ProductsScreen.kt
 └─ viewmodel
     └─ ProductViewModel.kt

Prompts clave usados

Planeación de la app y arquitectura MVVM:

Quiero una app Android (Kotlin, Compose) que consuma un API REST en http://10.0.2.2:8080/ con endpoints de productos (GET/POST/PUT/DELETE y búsqueda por nombre). Sugiere la arquitectura MVVM + Repository + Retrofit y dame la lista de archivos a crear (paquetes, clases) con una breve descripción de cada uno.


Modelos:

Genérame el data class Product con propiedades id: Long?, name: String, price: Double, category: String?, createdAt: String?, y un ProductRequest (name, price, category). Comenta por qué id y createdAt son opcionales.


Retrofit:

Define una interfaz ProductApi con Retrofit para estos endpoints exactos (lista, get por id, create, update, delete, search con productName). Luego crea un RetrofitInstance con base URL http://10.0.2.2:8080/, GsonConverterFactory y logging de OkHttp en nivel BODY.


Repository:

Crea una clase ProductRepository que use RetrofitInstance.api y exponga funciones listAll, get, create, update, delete, search. Solo delega, sin lógica extra.


ViewModel + State:

Crea un ProductViewModel con ProductUiState(loading, items, message) usando MutableStateFlow/StateFlow. Implementa loadAll, searchByName, create, update, delete con viewModelScope.launch y runCatching. Explica cómo actualizas la lista inmutable.


UI Compose:

Dame una pantalla ProductsScreen(vm) que tenga: barra de búsqueda (nombre), formulario simple (name, price, category) para crear, una LazyColumn de productos con botones de actualizar rápido (+1.00 al precio) y eliminar. Usa collectAsStateWithLifecycle para el estado.


Depuración CLEARTEXT:

Tengo error CLEARTEXT communication not permitted al llamar http://10.0.2.2:8080/. Dame pasos para solucionarlo en Android 9+ y cómo confirmar que el fix funcionó.


Mejoras (bonus):

Sugiere 3 mejoras pequeñas: validación de precio, feedback de errores con Snackbar/Dialog, y formateo de moneda. Dame snippets puntuales.

Capturas de pantalla
Acción	Captura
Listar	

Crear	

Actualizar	

Eliminar	

Búsqueda	

Sustituye las imágenes con tus capturas reales desde el emulador o dispositivo físico.

Explicación breve de MVVM + Retrofit

Model: Product y ProductRequest representan los datos y DTOs para crear/actualizar productos.

Repository: ProductRepository delega llamadas a la API y mantiene la UI desacoplada del backend.

ViewModel: ProductViewModel maneja el estado de la UI (ProductUiState) con StateFlow y coroutines, actualizando listas inmutables para recomposición.

View/Compose: ProductsScreen muestra los datos, formulario y botones de acción; usa collectAsStateWithLifecycle() para observar cambios en tiempo real.

Retrofit: se encarga de la comunicación HTTP con Gson para JSON y OkHttp Logging para depuración.

Errores encontrados y cómo los resolví

CLEARTEXT communication not permitted

Solución: Añadí android:usesCleartextTraffic="true" en <application> del AndroidManifest.xml.

NumberFormatException al convertir precio

Solución: Validé y parseé con price.toDoubleOrNull() ?: 0.0 para evitar crashes.

Lista no se actualizaba después de CRUD

Solución: Cada operación llama a loadAll() y el ViewModel reemplaza la lista inmutable, asegurando recomposición.

NullPointerException al actualizar producto sin ID

Solución: Verifiqué product.id antes de llamar a update() o delete().

Reflexión

Durante la actividad pedí a ChatGPT que generara los modelos, Retrofit, repository, ViewModel y UI Compose para implementar CRUD y búsqueda.
Al principio copié el código directamente, pero encontré errores de nulos y parsing que corregí manualmente.
Ajusté la UI para asegurar recomposición y validación de entradas.
Aprendí a integrar MVVM con Retrofit, separar responsabilidades y manejar estado con StateFlow.
El uso de prompts aceleró la estructura base, mientras que la depuración me enseñó a manejar errores comunes de Android y mantener la app estable.

Setup mínimo de Android Studio

Permisos en AndroidManifest.xml:

<uses-permission android:name="android.permission.INTERNET"/>
<application android:usesCleartextTraffic="true" ... >


Dependencias en app/build.gradle:

implementation platform('androidx.compose:compose-bom:2025.1.0')
implementation 'androidx.compose.material3:material3'
implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.6.2'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
implementation 'androidx.lifecycle:lifecycle-runtime-compose:2.6.2'

implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11'

implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'
