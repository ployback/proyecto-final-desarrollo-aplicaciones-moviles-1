# Proyecto T2 - Android + Spring Boot (API REST) + BD H2 + SQLLite

Este repositorio contiene dos partes principales:

1. Una aplicación Android nativa desarrollada en Kotlin.
2. Una API REST en Spring Boot con Java 25 y base de datos H2 para gestionar lentes.
3. SQLLite para cachear los lentes seleccionados

La idea general es mantener una arquitectura simple y clara: la app móvil consume la API REST de lentes, y el backend ofrece los métodos CRUD necesarios para gestionar el catálogo.

---

## 1. Arquitectura general

El proyecto está dividido en dos módulos funcionales:

### 1.1 App Android (Kotlin)
Ubicación:
- `app/`

Responsabilidades:
- UI de la app móvil
- Login mock de administrador
- Navegación entre pantallas
- Consumo de la API REST
- Visualización y gestión del catálogo de lentes

Tecnologías:
- Kotlin
- Android SDK
- Jetpack Navigation
- RecyclerView
- Retrofit
- Gson
- SQLLiTE

### 1.2 API Spring Boot (Java 25)
Ubicación:
- `lentes-api/`

Responsabilidades:
- Exponer endpoints REST para lentes
- Persistencia en base de datos H2
- CRUD completo
- Validación básica y manejo de errores simple

Tecnologías:
- Java 25
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- H2 Database
- SQLLite

---

## 2. Estructura del repositorio

```text
T2/
├── app/
│   ├── src/main/java/pe/edu/cibertec/t2/
│   ├── src/main/res/
│   ├── build.gradle.kts
│   └── ...
├── lentes-api/
│   ├── src/main/java/com/example/lentesapi/
│   ├── src/main/resources/
│   ├── pom.xml
│   └── ...
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
├── README.md
└── ...
```

---

## 3. Backend: API de lentes

### 3.1 Objetivo
La API REST gestiona una entidad llamada `Lente` con los siguientes campos:
- id
- nombre
- marca
- precio
- stock
- descripcion

### 3.2 Base de datos
Se usa H2 en memoria para facilitar el desarrollo y pruebas locales.

Configuración:
- URL: `jdbc:h2:mem:lentesdb`
- Usuario: `sa`
- Password: vacía

Consola H2:
- `http://localhost:8080/h2-console`

### 3.3 Endpoints disponibles

#### Crear lente
- `POST /api/lentes`

#### Listar lentes
- `GET /api/lentes`

#### Obtener lente por ID
- `GET /api/lentes/{id}`

#### Actualizar lente
- `PUT /api/lentes/{id}`

#### Eliminar lente
- `DELETE /api/lentes/{id}`

---

## 4. Frontend: app Android

### 4.1 Login mock
La app incluye un login simulado para administrador con credenciales mock:

- usuario: `admin`
- contraseña: `1234`

Esto permite entrar sin backend de autenticación real, como parte del proyecto final.

### 4.2 Dashboard del administrador
Una vez autenticado, la app muestra un panel para:
- crear lentes
- editar lentes
- eliminar lentes
- refrescar la lista desde la API

### 4.3 Flujo de consumo de API
La app usa Retrofit para consumir la API del backend:
- `LenteApiService`
- `LenteRepository`
- `ClienteRetrofit`

El cliente apunta a la URL:
- `http://10.0.2.2:8080/`

Esto es lo correcto para Android Emulator, ya que `10.0.2.2` redirige al localhost del equipo.

---

## 5. Flujo de trabajo recomendado

### 5.1 Ejecutar el backend
Desde la carpeta `lentes-api`:

```bash
cd lentes-api
mvn spring-boot:run
```

La API quedará disponible en:
- `http://localhost:8080`

### 5.2 Ejecutar la app Android
En Android Studio:
1. abre la carpeta raíz del proyecto
2. sincroniza Gradle
3. ejecuta el módulo `app`
4. usa un emulador o dispositivo Android

---

## 6. Dependencias principales

### Backend
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- H2 Database

### Android
- Retrofit 3
- Converter Gson
- SQLLIte
- Navigation Components
- RecyclerView
- Material Components

---

## 7. Buenas prácticas aplicadas

- Separación entre entidad, repositorio y servicio en backend
- Endpoints REST organizados por responsabilidad
- Login mock controlado por sesión local
- API consumida desde una capa repository en la app Android
- Código organizado por paquetes funcionales

---


## 8. Resumen

Este proyecto combina dos capas:

- Android app para la experiencia móvil del usuario
- Spring Boot + H2 para la lógica de negocio y persistencia del catálogo de lentes



---

