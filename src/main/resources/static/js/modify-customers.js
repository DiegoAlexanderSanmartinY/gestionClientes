async function loadCustomer() {
  if (isNew()) {
    return;
  }

  let id = getCustomerId();
  let customer = await getCustomerById(id);

  document.getElementById("txtFirstname").value = customer.nombre;
  document.getElementById("txtLastname").value = customer.apellidos;
  document.getElementById("txtPhone").value = customer.telefono;
  document.getElementById("txtEmail").value = customer.email;
  document.getElementById("txtAddress").value = customer.direccion;
}

function getCustomerId() {
  let auxSplit = window.location.href.split("id=");
  let id = auxSplit[1];
  return id;
}

function isNew() {
  let hasIdInUrl = window.location.href.includes("id=");
  return !hasIdInUrl;
}

async function getCustomerById(id) {
  let url = URL_SERVER + id;
  let response = await fetch(url);
  let json = await response.json();
  return json;
}

function crearCliente() {
  let firstname = document.getElementById("txtFirstname").value;
  let lastname = document.getElementById("txtLastname").value;
  let email = document.getElementById("txtEmail").value;
  let phone = document.getElementById("txtPhone").value;
  let address = document.getElementById("txtAddress").value;

  let customer = {
    nombre: firstname,
    apellidos: lastname,
    email: email,
    telefono: phone,
    direccion: address,
  };
  save(customer);
}

async function save(customer) {
  let url = URL_SERVER;
  let methodType = isNew() ? "POST" : "PUT";

  if (!isNew()) {
    url += getCustomerId();
  }

  let config = {
    method: methodType,
    body: JSON.stringify(customer),
    headers: {
      "Content-Type": "application/json",
    },
  };

  await fetch(url, config);
  alert("El cliente se guardo correctamente");
  window.location.href = "customers.html";
}

loadCustomer();
