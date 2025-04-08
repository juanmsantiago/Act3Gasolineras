// Ejecutar después de que el DOM se haya cargado
document.addEventListener("DOMContentLoaded", function() {
  const formulario = document.getElementById("formularioGasolineras");
  const btnGeolocalizacion = document.getElementById("btnGeolocalizacion");
  const mensaje = document.getElementById("mensaje");
  const listaGasolineras = document.getElementById("listaGasolineras");

  // Manejar la acción del formulario (envío de parámetros)
  formulario.addEventListener("submit", function(event) {
    event.preventDefault(); // Evitar comportamiento por defecto de envío de formulario
    buscarGasolineras();
  });

  // Manejar el botón de geolocalización
  btnGeolocalizacion.addEventListener("click", function() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        position => {
          // Actualiza los campos de latitud y longitud
          document.getElementById("lat").value = position.coords.latitude;
          document.getElementById("lon").value = position.coords.longitude;
          mostrarMensaje("Ubicación detectada correctamente.", "info");
        },
        error => {
          console.error("Error en geolocalización:", error);
          mostrarMensaje("No se pudo obtener la ubicación. Por favor, ingrésela manualmente.", "danger");
        }
      );
    } else {
      mostrarMensaje("La geolocalización no es compatible con este navegador.", "danger");
    }
  });

  // Función que realiza la búsqueda mediante AJAX
  function buscarGasolineras() {
    // Recopila los valores del formulario
    const lat = document.getElementById("lat").value;
    const lon = document.getElementById("lon").value;
    const empresa = document.getElementById("empresa").value;
    const carburante = document.getElementById("carburante").value;

    // Construir la URL para la petición
    const url = `/api/gasolineras?lat=${lat}&lon=${lon}&empresa=${encodeURIComponent(empresa)}&carburante=${encodeURIComponent(carburante)}`;

    // Realiza la petición AJAX usando fetch
    fetch(url)
      .then(response => {
        if (!response.ok) {
          throw new Error("Error en la respuesta del servidor.");
        }
        return response.json();
      })
      .then(data => {
        mostrarGasolineras(data);
      })
      .catch(error => {
        console.error("Error al obtener datos:", error);
        mostrarMensaje("Ocurrió un error al obtener los datos.", "danger");
      });
  }

  // Función para mostrar el mensaje de estado o error
  function mostrarMensaje(texto, tipo) {
    mensaje.style.display = "block";
    mensaje.textContent = texto;
    mensaje.className = ""; // Limpiar clases previas
    mensaje.classList.add("alert", `alert-${tipo}`);
  }

  // Función para renderizar la lista de gasolineras en la sección de resultados
  function mostrarGasolineras(gasolineras) {
    // Limpiar la lista de resultados anteriores
    listaGasolineras.innerHTML = "";
    if (!gasolineras || gasolineras.length === 0) {
      mostrarMensaje("No se encontraron gasolineras con esos parámetros.", "warning");
      return;
    }
    mensaje.style.display = "none"; // Ocultar mensaje si hay resultados

    // Crear cada elemento de la lista
    gasolineras.forEach(g => {
      const item = document.createElement("div");
      item.classList.add("list-group-item");

      // Se muestran algunos de los campos (puedes añadir más)
      item.innerHTML = `
        <h5>${g.rotulo || "Sin rótulo"}</h5>
        <p><strong>Dirección:</strong> ${g.direccion || "No disponible"}</p>
        <p><strong>Municipio:</strong> ${g.municipio || "No disponible"}, ${g.provincia || "No disponible"}</p>
        <p><strong>Precios:</strong> Gasolina 95 E5: ${g.precioGasolina95E5 || "n/a"} € - Gasóleo A: ${g.precioGasoleoA || "n/a"} €</p>
        <p><strong>Distancia aproximada:</strong> ${calcularDistanciaEnTexto(g.latitud, g.longitud)}</p>
      `;
      listaGasolineras.appendChild(item);
    });
  }

  // Función auxiliar para calcular y formatear la distancia
  // Esta función espera que se hayan establecido las coordenadas del usuario en el formulario.
  function calcularDistanciaEnTexto(latEst, lonEst) {
    const latUsuario = parseFloat(document.getElementById("lat").value);
    const lonUsuario = parseFloat(document.getElementById("lon").value);
    if (!latUsuario || !lonUsuario || !latEst || !lonEst) {
      return "No disponible";
    }
    const distKm = haversineDistance(latUsuario, lonUsuario, latEst, lonEst);
    return `${distKm.toFixed(2)} km`;
  }

  // Función que calcula la distancia entre dos puntos (fórmula Haversine)
  function haversineDistance(lat1, lon1, lat2, lon2) {
    const R = 6371; // Radio de la Tierra en km
    const dLat = toRadians(lat2 - lat1);
    const dLon = toRadians(lon2 - lon1);
    const a =
      Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) *
      Math.sin(dLon/2) * Math.sin(dLon/2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    return R * c;
  }

  // Función para convertir grados a radianes
  function toRadians(degrees) {
    return degrees * Math.PI / 180;
  }
});

