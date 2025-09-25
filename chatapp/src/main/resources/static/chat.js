// ========================
// Configuration
// ========================
const BACKEND_URL = 'http://localhost:9090'; // your backend URL & port
const chatBox = document.getElementById('chatBox');
const receiverInput = document.getElementById('receiver');
const messageInput = document.getElementById('message');
const sendBtn = document.getElementById('sendBtn');
const modeToggle = document.getElementById('modeToggle');

let stompClient = null;
const token = sessionStorage.getItem('token');
const username = sessionStorage.getItem('username');

if (!token || !username) {
    window.location.href = 'index.html';
}

// ========================
// Show Message
// ========================
function showMessage(sender, content, isOwn) {
    const msgDiv = document.createElement('div');
    msgDiv.className = `message ${isOwn ? 'sender' : 'receiver'}`;
    msgDiv.innerHTML = `<span class="username">${sender}</span>${content}`;
    chatBox.appendChild(msgDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// ========================
// WebSocket Connect
// ========================
function connect() {
    const socket = new SockJS(`${BACKEND_URL}/ws`);
    stompClient = Stomp.over(socket);

    stompClient.connect({ Authorization: `Bearer ${token}` }, () => {
        // Subscribe to your personal queue
        stompClient.subscribe(`/user/queue/messages`, (msg) => {
            const body = JSON.parse(msg.body);
            const isOwn = body.sender.username === username;
            showMessage(body.sender.username, body.content, isOwn);
        });
    });
}

// ========================
// Send Message
// ========================
function sendMessage() {
    const receiver = receiverInput.value.trim();
    const content = messageInput.value.trim();
    if (!receiver || !content || !stompClient) return;

    const msg = {
        senderUsername: username,
        receiverUsername: receiver,
        content: content
    };

    stompClient.send("/app/chat", {}, JSON.stringify(msg));
    showMessage(username, content, true); // show on sender side
    messageInput.value = '';
}

// Send button click
sendBtn.addEventListener('click', sendMessage);

// Send on Enter key
messageInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') sendMessage();
});

// ========================
// Dark Mode Toggle
// ========================
modeToggle.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');
    if (document.body.classList.contains('dark-mode')) {
        modeToggle.textContent = '☀️ Light Mode';
    } else {
        modeToggle.textContent = '🌙 Dark Mode';
    }
});

// ========================
// Logout
// ========================
function logout() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
    window.location.href = 'index.html';
}

// Optional: add logout button dynamically
const logoutBtn = document.createElement('button');
logoutBtn.textContent = 'Logout';
logoutBtn.style.marginLeft = '10px';
logoutBtn.onclick = logout;
document.querySelector('header').appendChild(logoutBtn);

// ========================
// Initialize
// ========================
connect();
