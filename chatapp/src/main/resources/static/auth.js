const BACKEND_URL = 'http://localhost:9090';

const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const loginBtn = document.getElementById('loginBtn');
const registerBtn = document.getElementById('registerBtn');
const authMsg = document.getElementById('authMsg');

// Toggle Login/Register
loginBtn.addEventListener('click', () => {
  loginForm.style.display = 'block';
  registerForm.style.display = 'none';
  loginBtn.classList.add('active');
  registerBtn.classList.remove('active');
  authMsg.textContent = '';
});
registerBtn.addEventListener('click', () => {
  loginForm.style.display = 'none';
  registerForm.style.display = 'block';
  loginBtn.classList.remove('active');
  registerBtn.classList.add('active');
  authMsg.textContent = '';
});

// Login
loginForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const username = document.getElementById('loginUsername').value;
  const password = document.getElementById('loginPassword').value;

  try {
    const res = await fetch(`${BACKEND_URL}/users/login`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ username, password })
    });
    if (!res.ok) throw new Error("Invalid credentials");
    const data = await res.json();
    sessionStorage.setItem('token', data.token);
    sessionStorage.setItem('username', data.username);
    window.location.href = 'chat.html';
  } catch (err) {
    authMsg.textContent = err.message;
  }
});

// Register
registerForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const username = document.getElementById('regUsername').value;
  const password = document.getElementById('regPassword').value;

  try {
    const res = await fetch(`${BACKEND_URL}/users/register`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({ username, password })
    });
    if (!res.ok) throw new Error("Registration failed");
    const data = await res.json();
    sessionStorage.setItem('token', data.token);
    sessionStorage.setItem('username', data.username);
    window.location.href = 'chat.html';
  } catch (err) {
    authMsg.textContent = err.message;
  }
});
