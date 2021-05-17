function getid(){
    fetch("http://104.248.207.133:5000/api/v1/auth/me", {
  'method': 'GET',
  'headers': {
    "Authorization": "Bearer" +localStorage.getItem("token"),
    
  }
})
  .then(response => console.log(response))
  .catch(err => console.error(err));
};
function getUserList(){
    document.getElementById("userTable");
  
//     fetch("http://104.248.207.133:5000/api/v1/auth/me", {
//                 'method': 'GET',
//                     'headers':{
//                     'Authorization': "Bearer "+localStorage.getItem("token"),
//   },
console.log(`${localStorage.getItem("seciliRestaurant2")}`);
      fetch(`http://104.248.207.133:5000/api/v1/menuitems/?menu=${localStorage.getItem("seciliRestaurant2")}`,
{
    
     method:'GET',
    headers:{
         'Content-Type':'application/json',
        
         'Authorization': 'Bearer ' +localStorage.getItem("token"),

     },
     })
    .then(response=>response.json())
     .then(data=>{
        
         //Tabloyu Hedefle
         const table=document.getElementById("userTable");
         //Tabloyu Temizledik
        document.getElementById("userTable").innerHTML="";

         //console.log(data.data);
         for(restaurant of data.data){
           //  console.log(id);
             table.innerHTML+=`
           
             <tr>
             <td><input type="text" id="user_id_${restaurant._id}" class="form-control" value="${restaurant._id}" /></td>
             <td><input type="text" id="user_name_${restaurant._id}" class="form-control" value="${restaurant.name}" /></td>
             <td><input type="text" id="user_description_${restaurant._id}" class="form-control" value="${restaurant.description}" /></td>
             <td><input type="text" id="user_type_${restaurant._id}" class="form-control" value="${restaurant.type}" /></td>
             <td><input type="text" id="user_price_${restaurant._id}" class="form-control" value="${restaurant.price}" /></td>
             <td><button onClick='updateUser("${restaurant._id}")' class="btn btn-warning">Güncelle</button>
            <button onClick='deleteUser("${restaurant._id}")' class="btn btn-danger">Sil</button></td>
            
            </tr>
             `
         }
    })
 } 
// Kullanıcı Listesini Getir
getUserList();

//Kullanıcı Tablosunu Yenile
function refreshTable(){
    getUserList();
}

//Yeni Kullanıcı Oluştur

function createUser() {
    console.log("Çalışıyor");
    let data={
        user_id: document.getElementById("user_id_"+id).value || "",
        user_name: document.getElementById("user_name_"+id).value || "",
        user_description: document.getElementById("user_description_"+id).value || "",
        user_address: document.getElementById("user_address_"+id).value || ""
    };
    fetch(`http://104.248.207.133:5000/api/v1/menus/?restaurant=${localStorage.getItem("seciliRestaurant")}`,{
        method:'POST',
        headers:{
            'Content-Type':'application/json',
            'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwOGZkZTZmYTkzZDZhZTQ4YzI3YmRiNCIsImlhdCI6MTYyMDc2OTMwNSwiZXhwIjoxNjIzMzYxMzA1fQ.GG9janFtCzbSFWiGc8CS57dLqibYfZcAFDZzC2-_CRw',
            'cookie': 'token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwOGZkZTZmYTkzZDZhZTQ4YzI3YmRiNCIsImlhdCI6MTYyMDgzMDM1NiwiZXhwIjoxNjIzNDIyMzU2fQ.52ej0aw9-eMuXhJtbb6n3Nb8-LNGQdw8pYEGjcZ4_OM'

        },
        body:JSON.stringify(data),
    })
    .then(response=>response.json())
    .then(data=>{
        console.log(data);
        const table=document.getElementById("userTable");
        console.log("Kullanıcı Oluşturuldu:",data);

        table.innerHTML+=`
            <tr><td><input type="text" class="form-control" value="${data.first_name}" /></td>
            <td><input type="text" class="form-control" value="${data.last_name}" /></td>
            <td><input type="text" class="form-control" value="${data.email}" /></td>
            <td><button onclick='updateUser("${data.id}")' class="btn btn-warning">Güncelle</button>
            <button onclick='deleteUser("${data.id}")' class="btn btn-danger">Sil</button></td></tr>
            `;
        // getUserList();
        
    })
    .catch((error)=>{
        console.error("Hata",error);
    });
}






function updateUser(id){
    console.log(id);
    
let data={
        user_id: document.getElementById("user_id_"+id).value || "",
        user_name: document.getElementById("user_name_"+id).value || "",
        user_description: document.getElementById("user_description_"+id).value || "",
        user_type: document.getElementById("user_type_"+id).value || "",
        user_price: document.getElementById("user_price_"+id).value || ""
    };
    fetch("http://104.248.207.133:5000/api/v1/menuitems/"+id,{

    method:'PUT',
    headers:{
        'Content-Type' : "application/json",
        'Authorization': 'Bearer ' +localStorage.getItem("token"),
        

    },
   body: JSON.stringify({

               
                
                name : data.user_name,
                description : data.user_description,
                type: data.user_type,
                price: data.user_price
                

                

            })
        })
    .then(response=>response.json())
    .then(data=>{
        console.log("Kullanıcı Güncellendi",data);
    })
    .catch((error)=>{
        console.log('Hata',error);
    })



}

function deleteUser(id){
    fetch("http://104.248.207.133:5000/api/v1/menuitems/"+id, {
  'method': 'DELETE',
  "headers": {
    'Authorization': 'Bearer ' +localStorage.getItem("token"),
  }
})
  .then(response => console.log(response))
  .catch(err => console.error(err));
}

function gotomenuekleme(restaurantid){
    localStorage.setItem("seciliRestaurant" , restaurantid);
    
    
    
}

    function menu(id){
        fetch("http://104.248.207.133:5000/api/v1/menus/"+id,{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                'Authorization': 'Bearer ' +localStorage.getItem("token"),
            }
        })
        .then(response=>console.log(response))
        .then(data=>{
            
           // console.log("Kullanıcı Silindi",data);
        })
        .catch((error)=>{
            //console.log("Hata:" + error);
        })
}






















