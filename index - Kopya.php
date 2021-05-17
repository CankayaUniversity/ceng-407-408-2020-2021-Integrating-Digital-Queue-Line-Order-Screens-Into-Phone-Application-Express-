
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Sing in & Sign Up Form </title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  

</head>

<body>
<?
    echo $_GET['Username'].'--'.$_GET['Password'];
    ?>

    <section>
    <div class="container">
        <div class="user signinBx">
            <div class="imgBx"><img src="logo.png" height="15%" width="15%"></div>
            <div class=formBx>
                <form action="./index.php" method="GET">
                    <h2>Sign In</h2>
                    <input id="name" type="text" name="username" placeholder="Username">
                    <input id="Password" type="password" name="pasword" placeholder="Password">
                    <input type="submit" value="Login">
                    <p class="signup">Dont have an account?<a href="#" onclick="toggleForm1();">Sign Up</a></p>
                </form>
            </div>
        </div>

        <div class="user signupBx">
            <div class="imgBx"><img src="logo.png" height="15%" width="15%"></div>
            <div class=formBx>
                <form >
                    <h2>Create an account</h2>
                    <input id="username" type="text" placeholder="Username">
                    <input id="email" type="text" placeholder="Email Address">
                    <input id="password" type="password" placeholder="Create Password">
                    <input type="password" placeholder="Confirm Password">
                    <button id="btnSignup" onclick="toggleForm()" value="Sign Up"> </button>
                     <p class="signup">Already have an account?<a href="#" onclick="toggleForm();">Sign in</a></p>*
                </form>
            </div>
        </div>

    </div>

   
</section>
<script>
       function toggleForm(){
           section =document.querySelector('section');
           container =document.querySelector('.container');
           container.classList.toggle('active');
           section.classList.toggle('active');

       }
</script> 
<script>
    function toggleForm1(){
        section =document.querySelector('section');
        container =document.querySelector('.container');
        container.classList.toggle('active');
        section.classList.toggle('active');

    }
</script> 

    
<script>
   console.log(<?php echo $_GET['Username']  ?>  );
    console.log(window.location.search);
    console.log($.get("index.php"));
    console.log(document.getElementById("Username").value);
    console.log(document.getElementById("Password").value);
    fetch('http://104.248.207.133:5000/api/v1/project-info', {
            method: 'GET',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify({
                
               
                console.log(<?php=$_GET['Username']  ?>  )
            })
        })
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(error => console.log("ERROR"))
</script>
<script>
    console.log($('.formBx'));
</script>
</body>
</html>