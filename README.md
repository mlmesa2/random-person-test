## RandomUserGenerator

RandomUserGenerator es una aplicación android creada con java siguiendo el patrón **MVVM**, usando rxjava e inyección de dependencias con hilt.

La aplicación consume los datos de un api llamada [Random User Generator](https://randomuser.me/) y muestra los usuarios generados en una lista

### Endpoint:

El endpoint utilizado es https://randomuser.me/api/?page=1&results=10&seed=myperson ya que es el que permite la paginación de la api.

- el parámetro **page** indica el número de la página de datos
- le parámetro **result** indica la cantidad de elementos que van a mostrar por cada página
- el parámetro **seed** permite que se mantengan los mismo datos en cada consulta y en cada página generada

### Librerías utilizadas:

- Live Data para crear vistas que respondan a los cambios en los datos
- Data Binding para vincular los datos a las vistas
- Navigation para la navegación entre pantalla
- ViewModel para almacenar momentáneamente el estado de las vistas
- Hilt para la inyección de dependencias

**De terceros:**

- Retrofit para las llamadas por red al api
- RxJava para hacer las llamada asíncronas
- Circularimageview para hacer las imágenes redondeadas

### Arquitectura:

El código esta implementado siguiendo las pautas de clean arquitecture y MVVM, separando así la lógica de la app en diferentes capas:

- data: En la capa data tenemos todo lo referente al manejo de los datos
    - model: El modelos de los datos que se van a utilizar
    - network: Todo lo referente al llamado del api
    - repository: Aquí se hace la llamada al api y se obtienen los datos
- di: Lo referente a los módulos para la inyección de dependencia, tanto de Retrofit como del repositorio
- ui: Todo lo referente a las vistas de la app, los fragment y el viewmodel
    - adapter: El adapter para utilizar el recyclerview así como las clases para el manejo de la misma
    - state: Componente para manejar el estado de la vista en la pantalla principal
