# Documentación técnica del proyecto Android T2

## 1. Visión general

Este proyecto es una aplicación Android nativa desarrollada con Kotlin. Su estructura es una app de tipo “educativa / práctica” con varias pantallas y un flujo simple:

- Login inicial
- Navegación interna por fragments
- Menú inferior con bottom navigation
- Carga de datos desde una API REST externa
- Listado de recetas usando RecyclerView

El objetivo principal del proyecto parece ser demostrar conocimientos de:

- Android Views con XML
- Navigation Component
- RecyclerView
- Retrofit + Gson
- Material Design
- Integración de API externa
- Flujo básico de autenticación local

---

## 2. Stack tecnológico

### 2.1 Lenguaje
- Kotlin

### 2.2 Framework / plataforma
- Android SDK
- AndroidX
- Jetpack Components

### 2.3 Gradle y configuración
- Gradle Kotlin DSL (`build.gradle.kts`)
- Plugin Android Application
- Versiones centralizadas en `gradle/libs.versions.toml`

### 2.4 Librerías principales
- `androidx.core:core-ktx`
- `androidx.appcompat:appcompat`
- `androidx.constraintlayout:constraintlayout`
- `androidx.fragment:fragment`
- `androidx.navigation:navigation-fragment-ktx`
- `androidx.navigation:navigation-ui-ktx`
- `androidx.recyclerview:recyclerview`
- `com.google.android.material:material`
- `com.github.bumptech.glide:glide`
- `com.squareup.retrofit2:retrofit`
- `com.squareup.retrofit2:converter-gson`
- JUnit para testing
- Espresso + AndroidJUnit para pruebas instrumentadas

### 2.5 API y red
- Uso de Retrofit para consumo REST
- Base URL: `https://dummyjson.com/`
- Conversión JSON a objetos con Gson
- Cliente OkHttp con timeouts configurados

### 2.6 UI / diseño
- Se usa XML para layouts
- Material Components para inputs y botones
- ConstraintLayout para maquetación responsive
- BottomNavigationView para navegación inferior
- CardView para items de lista
- TextInputLayout y TextInputEditText para formularios

---

## 3. Arquitectura técnica del proyecto

La arquitectura es muy simple y manual, sin usar MVVM ni Clean Architecture formal. Se trata de una base de Android clásica en la que la vista y la lógica se gestionan de forma directa desde Activities y Fragments.

### 3.1 Estructura general
- `Activity` para pantallas principales
- `Fragment` para secciones internas de contenido
- `adapter/` para listados y renderizado de elementos UI
- `retrofit/` para acceso a servicios HTTP
- `res/layout/` para definiciones XML de pantallas
- `res/navigation/` para la estructura de navegación del proyecto

### 3.2 Patrón tecnológico dominante
- Android Views con XML
- Jetpack AndroidX
- Navigation Component
- RecyclerView
- Material Components
- Retrofit para consumo REST

Se mantiene una organización funcional, pero sin capa de dominio ni capa de presentación separada. La app funciona como un prototipo técnico de UI + integración remota básica.

---

## 4. Diseño visual actual

### 4.1 Pantalla de login
La pantalla de inicio se compone de:

- Fondo blanco / claro
- Título grande: “Iniciar Sesión”
- Campo de texto “Usuario (Código)”
- Campo de texto “Contraseña (DNI)”
- Botón principal “Ingresar”
- Uso de `TextInputLayout` para diseño Material
- Contraseña con toggle de visualización

La pantalla usa `ConstraintLayout` y un padding general de 24dp.

Diseño lógico:
- Título centrado arriba
- formulario centralizado verticalmente
- un botón ancho al final
- estilo simple, escolar / académico

Archivo principal:
- `app/src/main/res/layout/activity_login.xml`

### 4.2 Pantalla principal
La pantalla principal tiene:

- `CoordinatorLayout` como contenedor base
- `FragmentContainerView` ocupando toda la pantalla
- `BottomNavigationView` pegado al fondo

Esto indica una navegación tipo app moderna con tabs inferiores, aunque visualmente no es ni Material 3 ni Compose, sino un estilo clásico Android XML.

Archivo:
- `app/src/main/res/layout/activity_main.xml`

### 4.3 Diseño de listas
Para la lista de recetas se usa un `CardView` con:

- margen 8dp
- radio de esquina 8dp
- elevación 4dp
- texto con ID, nombre, tiempo de preparación, dificultad y cocina

Este item está pensado para mostrar información comercial / catalogada de recetas.

Archivo:
- `app/src/main/res/layout/item_recipe.xml`

---

## 5. Base técnica que quedaría como recuerdo del proyecto

La parte útil para conservar como memoria técnica es esta:

- La app es nativa Android con Kotlin.
- La UI se define en XML usando `ConstraintLayout`, `CardView`, `TextInputLayout`, `BottomNavigationView`.
- El proyecto usa AndroidX y Material Design.
- La navegación se hace a nivel de `Navigation Component`, aunque la estructura funcional puede ser removida sin perder la base tecnológica.
- La capa de red usa Retrofit + Gson + OkHttp.
- El proyecto usa `Glide` para imágenes.
- Se mantiene una separación simple entre layouts, adapters, modelos y cliente HTTP.

Esto es lo que realmente representa el “recuerdo técnico” del proyecto si luego se borra la lógica de negocio.

---

## 6. Archivos clave del proyecto

### Android / configuración
- `build.gradle.kts`
- `app/build.gradle.kts`
- `settings.gradle.kts`
- `gradle/libs.versions.toml`
- `app/src/main/AndroidManifest.xml`

### Actividades y fragmentos
- `app/src/main/java/pe/edu/cibertec/t2/LoginActivity.kt`
- `app/src/main/java/pe/edu/cibertec/t2/MainActivity.kt`
- `app/src/main/java/pe/edu/cibertec/t2/Pregunta1Fragment.kt`
- `app/src/main/java/pe/edu/cibertec/t2/Pregunta2Fragment.kt`
- `app/src/main/java/pe/edu/cibertec/t2/Pregunta3Fragment.kt`
- `app/src/main/java/pe/edu/cibertec/t2/Pregunta4Fragment.kt`

### Modelos y acceso a red
- `app/src/main/java/pe/edu/cibertec/t2/Usuario.kt`
- `app/src/main/java/pe/edu/cibertec/t2/retrofit/ClienteRetrofit.kt`
- `app/src/main/java/pe/edu/cibertec/t2/retrofit/api/IRecipeService.kt`
- `app/src/main/java/pe/edu/cibertec/t2/retrofit/response/RecipeResponse.kt`

### Adaptadores
- `app/src/main/java/pe/edu/cibertec/t2/adapter/FrutaAdapter.kt`
- `app/src/main/java/pe/edu/cibertec/t2/adapter/RecipeAdapter.kt`

### Layouts
- `app/src/main/res/layout/activity_login.xml`
- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/fragment_pregunta_1.xml`
- `app/src/main/res/layout/fragment_pregunta_2.xml`
- `app/src/main/res/layout/fragment_pregunta_3.xml`
- `app/src/main/res/layout/fragment_pregunta_4.xml`
- `app/src/main/res/layout/item_recipe.xml`

---

## 7. Qué se puede conservar si solo quieres dejar la base técnica

Si el objetivo es borrar la lógica de negocio y dejar solo la huella técnica, lo recomendable es conservar este conjunto:

- Gradle y dependencias
- Manifest
- Estructura general de Activities y Fragments
- Layouts XML
- Material, ViewBinding, RecyclerView, Navigation
- Configuración de red y modelos de transporte
- Adapters base para lista
- Uso de Glide y Retrofit

Lo que no es “memoria técnica” sino “lógica de negocio” puede borrarse sin afectar la base del proyecto:

- usuarios hardcodeados
- validación de login
- flujos de negocio
- llamadas concretas a endpoints específicos
- texto de pantalla con intención funcional
- contenido mock o demo

---

## 8. Conclusión

El proyecto tiene una base tecnológica sólida para Android nativo con Kotlin, orientada a una app de tipo demo / práctica. No es un proyecto con arquitectura moderna avanzada, pero sí usa las herramientas estándar del ecosistema Android moderno: XML, Activities, Fragments, Navigation, RecyclerView, Retrofit y Material Design.

Es una estructura ideal para reutilizar como base técnica de una app Android nueva, siempre que se reemplace la lógica de negocio y la UI específica.

---

## 9. Nota para reutilización

Este proyecto puede servir como plantilla de:

- app Android con navegación inferior
- login local básico
- consumo de APIs REST
- listado con RecyclerView
- diseño Material con XML

Si se elimina solo la lógica de negocio, la base técnica sigue siendo válida y reutilizable en otro proyecto o prototipo.
