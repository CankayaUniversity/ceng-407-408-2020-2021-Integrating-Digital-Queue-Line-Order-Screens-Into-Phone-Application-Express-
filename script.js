const socket = io('http://104.248.207.133:5000', {
  query:
    'auth_token='+localStorage.getItem("token"),
    
});

//let div = document.getElementById('income-message');

//socket.emit('pc-send', 'can ozzzal');

socket.on('phone-receive', (data) => {
  
  console.log(data);
});

socket.on('pc-receive', (data) => {
  
});

socket.on('user', (data) => {
  
});

socket.on('success', (data) => {
  console.log(data.message);
  //console.log('user info: ' + data.user);
  console.log('logged in: ' + data.user.name);
 
  //alert(Object.keys(data.user));
});

socket.on('error', function (err) {
  throw new Error(err);
});
