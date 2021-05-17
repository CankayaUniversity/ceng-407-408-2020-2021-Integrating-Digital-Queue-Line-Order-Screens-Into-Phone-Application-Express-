function getid(){
    fetch("http://104.248.207.133:5000/api/v1/auth/me", {
  'method': 'GET',
  'headers': {
    "Authorization": "Bearer" +localStorage.getItem("token"),
    
  }
})
  .then(response => console.log(response))
  .catch(err => console.error(err));
}
function getUserList(){
    document.getElementById("userTable");

    let restaurantid= localStorage.getItem("seciliRestaurant");
    
    fetch(`http://104.248.207.133:5000/api/v1/orders?restaurant=${localStorage.getItem("seciliRestaurant")}`,
    {
        'method': 'GET',
        'headers': {
            "Authorization": "Bearer" +localStorage.getItem("token"),
            
          }
    }
    
    
    )
    
    
    .then(response=>response.json())
    .then(data=>{
        //Tabloyu Hedefle
        const table=document.getElementById("userTable");
        //Tabloyu Temizledik
        document.getElementById("userTable").innerHTML="";

        //console.log(data);
        for(user of data.data){
            console.log(user);
            table.innerHTML+=`
           
            <tr><td><input type="text" id="user_id_${user._id}" class="form-control" value="${user._id}" /></td>
            <td><input type="text" id="user_name_${user._id}" class="form-control" value="${user.name}" /></td>
            <td><input type="text" id="user_description_${user._id}" class="form-control" value="${user.description}" /></td>
            <td><input type="text" id="user_type_${user._id}" class="form-control" value="${user.type}" /></td>
            <td>
            <button onClick='updateUser("${user._id}")' class="btn btn-warning">Güncelle</button>
            <button onClick='deleteUser("${user._id}")' class="btn btn-danger">Sil</button>
            </td></tr>
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
        user_name: document.getElementById("user_name").value || "",
        user_description: document.getElementById("user_description").value || "",
        user_type: document.getElementById("user_type").value || ""
    };
    fetch("http://104.248.207.133:5000/api/v1/restaurants/"+getid,{
        method:'POST',
        headers:{
            'Content-Type':'application/json',
        },
        body: JSON.stringify({

               
                
            name : data.user_name,
            description : data.user_description,
            type : data.user_type
            

            

        })
    })
    .then(response=>response.json())
    .then(data=>{
        console.log(data);
        const table=document.getElementById("userTable");
        console.log("Kullanıcı Oluşturuldu:",data);

        table.innerHTML+=`
            

            <tr><td><input type="text" id="user_id_${user._id}" class="form-control" value="${user._id}" /></td>
            <td><input type="text" id="user_name_${user._id}" class="form-control" value="${user.name}" /></td>
            <td><input type="text" id="user_description_${user._id}" class="form-control" value="${user.description}" /></td>
            <td><input type="text" id="user_type_${user._id}" class="form-control" value="${user.type}" /></td>
            <td><button onClick='updateUser("${user._id}")' class="btn btn-warning">Güncelle</button>
            <button onClick='deleteUser("${user._id}")' class="btn btn-danger">Sil</button></td></tr>
            `;
        // getUserList();
        
    })
    .catch((error)=>{
       // console.error("Hata",error);
    });
};


function updateUser(id){
    console.log(id);
    
let data={
        user_id: document.getElementById("user_id_"+id).value || "",
        user_name: document.getElementById("user_name_"+id).value || "",
        
        user_description: document.getElementById("user_description_"+id).value || "",
        user_type: document.getElementById("user_type_"+id).value || ""
    };
    fetch("http://104.248.207.133:5000/api/v1/menus/"+id,{

    method:"PUT",
    headers:{
        'Content-Type' : "application/json",
        'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwOGZkZTZmYTkzZDZhZTQ4YzI3YmRiNCIsImlhdCI6MTYyMDc2OTMwNSwiZXhwIjoxNjIzMzYxMzA1fQ.GG9janFtCzbSFWiGc8CS57dLqibYfZcAFDZzC2-_CRw',
        

    },
   body: JSON.stringify({

               
                
                name : data.user_name,
                description : data.user_description,
                type : data.user_type
                

                

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
    fetch("http://104.248.207.133:5000/api/v1/menus/"+id, {
  'method': 'DELETE',
  'headers': {
    
  }
})
  .then(response => console.log(response))
  .catch(err => console.error(err));
}






















