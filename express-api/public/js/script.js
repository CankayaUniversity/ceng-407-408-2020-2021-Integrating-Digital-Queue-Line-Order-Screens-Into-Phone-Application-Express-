const socket = io('http://104.248.207.133:5000');

let div = document.getElementById('income-message');

socket.on('phone-receive', (data) => {
  div.innerHTML += '<h1>' + data + '</h1>';
});

socket.on('pc-receive', (data) => {
  div.innerHTML += '<h1>' + data + '</h1>';
});

socket.on('user', (data) => {
  div.innerHTML += '<h1>' + data.data + '</h1>';
});
