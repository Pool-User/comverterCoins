# Conversor de Monedas

## Descripción

Este es un proyecto en **Java** que permite convertir entre diferentes monedas y el dólar estadounidense (USD) utilizando una API externa para obtener las tasas de cambio en tiempo real. El usuario puede seleccionar convertir su moneda local a dólares o viceversa, desde las siguientes opciones de monedas disponibles:

- Peso Argentino (ARS)
- Real Brasileño (BRL)
- Peso Colombiano (COP)

El programa usa la API de **ExchangeRate API** para obtener las tasas de cambio actualizadas.

## Características

- Convierte **monedas locales a dólares (USD)**.
- Convierte **dólares (USD) a monedas locales**.
- Soporta **tres monedas**:
  - Peso Argentino (ARS)
  - Real Brasileño (BRL)
  - Peso Colombiano (COP)
- Interfaz de línea de comandos para una experiencia de usuario sencilla.

## Requisitos

Para ejecutar este proyecto, necesitas tener instalado:

- **Java 11** o superior.
- Conexión a Internet para acceder a la API de ExchangeRate.
- **Librería Gson** para la conversión de datos JSON (si no la tienes instalada, el proyecto usa Maven para la gestión de dependencias).
