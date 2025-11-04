# FitFileApp
Hicimos Una Aplicación de sobre la vida sana, la app tiene login , token de contraseña, colores y recursos nativos
# FitLife SPA - Aplicación Móvil (MVP)

![FitLife App Icon](URL_DEL_ICONO_O_LOGO_DE_TU_APP)  <!-- Opcional: Sube un logo a tu repo y enlaza aquí -->

## 1. Descripción del Proyecto

Esta es la implementación del MVP (Producto Mínimo Viable) para la aplicación móvil de **FitLife SPA**, una plataforma integral de salud en línea. La aplicación se conecta a un backend real para ofrecer a los usuarios una experiencia personalizada de seguimiento de su salud y estado físico.

### Alcance del MVP

El alcance de este proyecto se centra en cubrir los flujos esenciales para un usuario final, demostrando competencias en el desarrollo de aplicaciones Android modernas con Jetpack Compose. Las funcionalidades cubiertas son:

*   **Autenticación de Usuario:** Registro e inicio de sesión.
*   **Visualización de Perfil:** El usuario puede ver sus datos y cambiar su foto de perfil.
*   **Gestión de Estado:** La interfaz de usuario reacciona a estados de carga, éxito y error.
*   **Persistencia de Datos:** La sesión del usuario y sus datos de perfil se mantienen aunque cierre la aplicación.
*   **Integración Nativa:** Uso de recursos nativos como la galería/cámara y las notificaciones locales.

---

## 2. Stack Tecnológico y Arquitectura

### Stack Tecnológico

*   **Lenguaje:** Kotlin
*   **UI Toolkit:** Jetpack Compose
*   **Arquitectura:** MVVM (Model-View-ViewModel)
*   **Navegación:** Jetpack Navigation for Compose
*   **Asincronía:** Corrutinas de Kotlin + Flow
*   **Inyección de Dependencias:** Manual (o Hilt si lo usas)
*   **Networking:** Retrofit & OkHttp
*   **Serialización JSON:** Gson
*   **Persistencia Local:**
    *   **DataStore:** Para datos simples y persistencia de sesión (token JWT).
    *   **Room:** Para almacenar datos complejos del usuario (perfil, progreso).
*   **Gestión de Permisos:** Accompanist Permissions (o la librería integrada de Compose).

### Arquitectura Aplicada (MVVM)

La aplicación sigue un patrón de arquitectura MVVM claro para separar responsabilidades y facilitar el mantenimiento y la escalabilidad.

*   **View (UI Layer):** Compuesta por `@Composable` functions (Screens) que observan el estado expuesto por el ViewModel y le notifican eventos del usuario. No contiene lógica de negocio.
*   **ViewModel (State Layer):** Contiene la lógica de la UI y gestiona el estado. Expone el estado a la UI (ej. `LoginUiState`) y consume los datos del `Repository`.
*   **Model (Data Layer):** Compuesta por:
    *   **Repository:** Actúa como única fuente de verdad. Centraliza el acceso a los datos, decidiendo si obtenerlos desde la API (remoto) o desde la base de datos local (Room/DataStore).
    *   **Fuentes de Datos:**
        *   **Remota:** `ApiService` (interfaz de Retrofit) que se comunica con el backend.
        *   **Local:** Base de datos Room y Jetpack DataStore.
    *   **Modelos:** Clases de datos (`data class`) que representan las entidades de la aplicación (Usuario, Plan, etc.).

---

## 3. Funcionalidades Cubiertas

*   **[✅] Autenticación de Usuario:**
    *   Pantalla de Login con validación de campos (email y contraseña no vacíos).
    *   Comunicación con el endpoint `/auth/login` del backend.
    *   Manejo de estados: `Cargando` (spinner visible), `Éxito` (navega al dashboard), `Error` (muestra un Snackbar con el mensaje del servidor).
    *   Persistencia del token JWT en DataStore para mantener la sesión activa.

*   **[✅] Gestión del Perfil:**
    *   Pantalla de Perfil que consume el endpoint `/me` (o similar) para obtener los datos del usuario autenticado.
    *   **Recurso Nativo 1:** El usuario puede pulsar sobre su foto de perfil para seleccionar una nueva imagen desde la **Galería** del dispositivo.
    *   La imagen de perfil seleccionada se persiste localmente (guardando su URI).

*   **[✅] Recordatorios de Entrenamiento:**
    *   **Recurso Nativo 2:** Pantalla (o sección) donde el usuario puede activar un recordatorio. Al hacerlo, se programa una **Notificación Local** para el día siguiente.

*   **[✅] Diseño y Experiencia de Usuario:**
    *   UI coherente con un tema de colores y tipografía definido (`Theme.kt`).
    *   Uso de animaciones sutiles (`AnimatedVisibility`) para mostrar mensajes de error y transiciones entre pantallas.

---

## 4. Endpoints de la API Utilizados

| Verbo HTTP | Endpoint          | Descripción                                        |
| :--------- | :---------------- | :------------------------------------------------- |
| `POST`     | `/auth/login`     | Inicia sesión de un usuario y devuelve un token JWT. |
| `GET`      | `/users/me`       | Obtiene los datos del perfil del usuario autenticado.  |
| `PUT`      | `/users/profile`  | (Opcional) Actualiza los datos del perfil de usuario.   |
| ...        | ...               | *(Añade aquí cualquier otro que uses)*              |

---

## 5. Manejo de Errores y Permisos

### Manejo de Errores de Red

La aplicación gestiona los errores de red de forma robusta en la capa de `Repository` y los comunica a la UI a través del `ViewModel`.

*   **Errores de Conectividad:** Se capturan excepciones generales (ej. `IOException`) en un bloque `try-catch` y se muestra un mensaje genérico ("Error de red, revisa tu conexión").
*   **Errores del Servidor (4xx, 5xx):** Se comprueba el código de respuesta de Retrofit (`response.isSuccessful`). Si no es exitoso, se extrae el mensaje de error del cuerpo de la respuesta para mostrar al usuario un feedback más específico (ej. "Credenciales incorrectas").

### Gestión de Permisos Nativos

Los permisos para recursos nativos se solicitan en tiempo de ejecución, siguiendo las mejores prácticas de Android.

*   **Galería:** En Android 13 y superior, se solicita el permiso `READ_MEDIA_IMAGES`. En versiones anteriores, se usa `READ_EXTERNAL_STORAGE`. Se proporciona un mensaje explicativo al usuario antes de lanzar la solicitud de permiso.
*   **Notificaciones:** En Android 13 y superior, se solicita el permiso `POST_NOTIFICATIONS` para poder mostrar notificaciones.

---
## 6. Instalación y Ejecución

1.  Clonar el repositorio: `git clone https://github.com/tu-usuario/tu-repositorio.git`
2.  Abrir el proyecto en Android Studio (versión Iguana o superior recomendada).
3.  Configurar la URL del backend en el archivo `data/network/RetrofitClient.kt`.
4.  Ejecutar la aplicación en un emulador o dispositivo físico (API 26+).
