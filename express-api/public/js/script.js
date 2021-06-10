const socket = io('http://104.248.207.133:5000', {
  query:
    'auth_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwOGYxNmE0OTg0MjU2YTI0NTM4ODkxNyIsImlhdCI6MTYxOTk5MDE4MCwiZXhwIjoxNjIyNTgyMTgwfQ.W-TIWArSzO2EGKejapZQqlSarqSdKxnpnzF1GizGjaM',
});

let div = document.getElementById('income-message');
let order = document.getElementById('send-order');

order.addEventListener('click', () => {
  socket.emit('phone-send', {
    name: 'burger menu',
    menuItem: '606eb96d70a4c309609d1e30',
  });
});

socket.on('phone-receive', (data) => {
  div.innerHTML += '<h1>' + data + '</h1>';
  console.log(data);
});

socket.on('pc-receive', (data) => {
  //div.innerHTML += '<h1>' + data + '</h1>';
  console.log(data);
});

socket.on('user', (data) => {
  div.innerHTML += '<h1>' + data.data + '</h1>';
});

socket.on('success', (data) => {
  console.log(data.message);
  //console.log('user info: ' + data.user);
  console.log('logged in: ' + data.user.name);
  div.innerHTML += '<h1>' + 'logged in: ' + data.user.name + '</h1>';
  //alert(Object.keys(data.user));
});

socket.on('error', function (err) {
  throw new Error(err);
});
