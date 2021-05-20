const socket = io('http://104.248.207.133:5000', {
  query:
    'auth_token='+localStorage.getItem("token"),
    
});

//let div = document.getElementById('income-message');
let b =document.getElementById('onayla');
socket.emit('pc-send', 'r._id');

// socket.on('phone-receive', (data) => {
  
//   console.log(data);
// });

socket.on('pc-receive', (data) => {
  console.log(data);
  //user_id == rstar_id aynı ise 
  //orders da onları bas
  location.reload();  
});

socket.on('user', (data) => {
  
});

socket.on('success', (data) => {
 // console.log(data.message);
  //console.log('user info: ' + data.user);
 // console.log('logged in: ' + data.user.name);
 
  //alert(Object.keys(data.user));
});

socket.on('error', function (err) {
  throw new Error(err);
});
