const io = require('socket.io')(5000)

io.on('connection', socket => {
console.log('someone connected');
    socket.on('pc-send', (data)=>{
        io.sockets.emit('phone-receive',data);
    });
    socket.on('phone-send',(data)=>{
        io.socket.emit('pc-receive', data);
    });
    
});
