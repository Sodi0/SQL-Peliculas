# Aplicación de Gestión de Reseñas de Películas
>[!NOTE] 
>Proyecto para la asignatura programacion Android

Este proyecto es una aplicación Android para gestionar reseñas de películas. Permite a los usuarios agregar, editar, eliminar y visualizar reseñas almacenadas en una base de datos SQLite.

---

## Características

- **Pantalla de inicio:** Permite ingresar el nombre del usuario para comenzar.
- **Gestión de reseñas:**
  - Crear nuevas reseñas de películas.
  - Visualizar una lista de reseñas existentes.
  - Editar y eliminar reseñas directamente desde la interfaz.
- **Interfaz intuitiva:** Uso de `ListView` para mostrar las reseñas y formularios modales para editar/crear entradas.

---

## Tecnologías utilizadas

- **Lenguaje:** Java
- **Base de datos:** SQLite
- **Interfaz gráfica:** XML para layouts
- **Android SDK:** API mínima requerida especificada en el archivo `build.gradle`.

---

## Estructura del proyecto

### Clases principales

1. **MainActivity:** Pantalla inicial donde el usuario ingresa su nombre.
2. **Adaptador:** Clase que implementa un `BaseAdapter` para gestionar la vista de cada elemento en la lista de reseñas.
3. **DaoContacto:** Clase encargada de manejar las operaciones CRUD sobre la base de datos SQLite.
4. **Resenia:** Modelo que representa una reseña con atributos como título, género, director, año, puntuación y el texto de la reseña.

---

## Funcionalidades principales

### Crear una reseña
- Se utiliza un formulario modal que permite ingresar los detalles de la película:
  - Título
  - Género
  - Director
  - Año
  - Puntuación
  - Texto de la reseña

### Editar una reseña
- Abre un diálogo prellenado con los datos actuales de la reseña para que el usuario pueda realizar modificaciones.

### Eliminar una reseña
- Solicita confirmación mediante un `AlertDialog` antes de eliminar la reseña seleccionada.

### Visualizar reseñas
- Las reseñas se muestran en un `ListView`, con cada elemento que incluye:
  - Información básica de la película.
  - Botones para editar y eliminar.

