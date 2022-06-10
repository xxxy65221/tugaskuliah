<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
</head>
<body>

    <?php 
        $host = "localhost";
        $port = "3306";
        $database = "test";
        $user = "nursyah";
        $password = "nursyah";

        $conn = new mysqli($host, $user, $password, $database, $port);
        
        if ($conn->connect_error){
            die("Connection failed: ". $conn->connect_error);
        }
        
    ?>

    <?php 
        $sql = "SELECT NAME, PASSWORD FROM USER_ACCOUNT";
        $result = $conn->query($sql);
        $login = "login failed";
        while($row = $result->fetch_assoc()){
            if ($row["NAME"] == $_POST["username"] && $row["PASSWORD"] == $_POST["password"]){
                $login = "login success";
                break;
            }
        }
        
        echo $login;
    ?>

</body>
</html>