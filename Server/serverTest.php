<?php 
require 'JSON.php'

function cardDataReceive(){
	$jsonCardData = $_POST[];

	$cardData = array();

}


//php데이터를 json형식으로 전환해주는 php의 내장함수
json_encode(value);

header('Content-Type: text/html; charset=EUC-KR');

// Get Post Data
$data = urldecode($_POST['data']);
	
$jsonData      = array();
$jsonTempData  = array();
	 
for($i=1;$i<4; $i++)
{
	$jsonTempData = array();
	$jsonTempData['name'] 		= $data.$i;
	$jsonTempData['number'] 		= $data.$i;
	$jsonTempData['date_added'] 	= $data.$i;
	  
	$jsonData[] = $jsonTempData;
}

$outputArr = array();
$outputArr['Android'] = $jsonData;
 
// Encode Array To JSON Data
print_r(json_encode($outputArr));
//출력결과 예 {"Android":[{"name":"1","number":"1","date_added":"1"},{"name":"2","number":"2","date_added":"2"},{"name":"3","number":"3","date_added":"3"}]} 


/*
$conn = mysqli_connect('localhost', '4Dpay', 'project4d', '4dpaydb');

if(mysqli_connect_errno($conn)){
	echo "데이터베이스 연결 실패: ". mysqli_connect_errno();
}  else {
	echo "데이터베이스 연결 성공";
}

$SelectQuery = "select * from user where user_ID = '$ID' && user_password = '$Password' ";
$result = mysqli_query($conn, $SelectQuery);

if ($result != null){
	$row = mysqli_fetch_array($result);
	echo $row['user_ID'];
} else {
	echo "회원이 아닙니다.";
}
mysqli_close($conn);
*/
?>