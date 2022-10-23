function updateLog(){
    $('.update-popup').css('display', 'block');
}
getLog(1);//로그 조회 함수 호출
{/* <td>1</td>
<td>192.158.0.252</td>
<td>/board</td>
<td>GET</td>
<td>2022-05-19 13:33:02</td> */}
function getLog(pageNum){
    $.ajax({
        url: 'http://192.168.0.74:8080/api/v1/logs?page='+pageNum,
        type : 'GET',
        dataType : 'json',
        success : function(response){
            $('#boardData').empty();
            $('.pagination').empty();
            console.log(response);//개발자도구로 확인
            var html ='';
            //html += '<tr onclick="getEmpByEmpno('+response.list[i].empno+')"><td>'+response.list[i].empno+'</td><td>'+response.list[i].ename+'</td><td>'+response.list[i].job+'</td><td>'+response.list[i].sal+'</td><td>'+response.list[i].hiredate+'</td><td>'+response.list[i].dname+'</td></tr>';
            for(var i=0; i<response.list.length; i++){
                html += '<tr onclick=getPopup('+response.list[i].logId+')><td>'+response.list[i].logId+'</td><td>'+response.list[i].ip+'</td><td>'+response.list[i].url+'</td><td>'+response.list[i].httpMethod+'</td><td>'+response.list[i].createAt+'</td></tr>';
                // html += '<tr onclick="getdeptByDeptno('+response[i].logId+')"><td>'+response[i].ip+'</td><td>'+response[i].url+'</td><td>'+response[i].httpMethod
                // +'</td><td>'+response[i].createAt+'</td></tr>'
            }
            $("#boardData").append(html);//table바인딩 작업
            var paginationHtml = '';
            if(response.hasPreviousPage){//이전버튼 여부 확인
                paginationHtml += '<a onclick="getLog('+(pageNum-1)+')">Previous</a>';
            }
            for(var i=0; i<response.navigatepageNums.length; i++){//총 보여줄 페이지
                var page = response.navigatepageNums[i];
                paginationHtml += '<a id="pageNum'+page+'"onclick="getLog('+page+')">'+page+'</a>'
            }
            if(response.hasNextPage){//다음버튼 여부 확인
                paginationHtml += '<a onclick="getLog('+(pageNum+1)+')">Next</a>';
            }
            $('.pagination').append(paginationHtml)//페이지 바인딩 작업
            //현재 페이지번호 css적용
            $('#pageNum'+pageNum).css('backgroundColor','#287bff')
            $('#pageNum'+pageNum).css('color','#fff')
        }
    });
}
//상세정보 조회
function getPopup(logId){
    $('.logs-popup').css('display', 'block');
    $('#map').empty();

    $.ajax({
        url: 'http://192.168.0.74:8080/api/v1/logs/'+ logId,
        type : 'GET',
        dataType : 'json',
        success : function(response){
           console.log(response);
           //id가 ip, createAt인 input태그에 값을 set!
           $('#ip').val(response.ip);
           $('#createAt').val(response.createAt);
            
        }
    });


    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(36.3286904, 127.4229992), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커가 표시될 위치입니다 
    var markerPosition  = new kakao.maps.LatLng(36.3286904, 127.4229992); 

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });

// 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
}
function getdeptByDeptno(deptno){
    
    //alert("hello world!");
    console.log("클릭한 사원번호는?: "+ deptno);
    $.ajax({
        url: "http://localhost:8080/api/v1/dept/deptno/"+deptno,
        type:"GET",
        dataType:"json",
        
        success:function(response){
            console.log(response);
            
            $('.update-popup').css('display','block');
            $('#u_deptno').val(response.deptno)
            $('#u_dname').val(response.dname)
            $('#u_loc').val(response.loc)
            

        }
    });
}
function updateDept(){
    var deptno = $('#u_deptno').val();
    var dname = $('#u_dname').val();
    var loc = $('#u_loc').val();
   
    var jsonData = {
        "deptno":deptno,
        "dname":dname,
        "loc":loc
        
        
    };
    
    $.ajax({
        url: "http://localhost:8080/api/v1/dept",
        type:"PATCH",
        contentType:"application/json",
        data: JSON.stringify(jsonData),
        dataType:"json",
        
        success:function(response){
            if(response>0){
                alert("수정완료!");
                location.reload();
            }
            
        }
    });
}



function setdept(){
    var deptno = $("#i_deptno").val();
    var dname = $("#i_dname").val();
    var loc = $("#i_loc").val();
    
    if(deptno == ""){
        alert("부서번호를 입력하세요");
        $("#i_deptno").focus();
        return false;
    }

    var jsonData = {
        "deptno":deptno,
        "dname":dname,
        "loc":loc,
    };
    //@CrossOrigin이 있어야 함
    //contentType: 서버에 보낼 데이터 타입
    //dataType: 서버에 응답 결과 데이터 타입
    //data:서버에 보낼 데이터
    //success: 요청 성공
    $.ajax({
        url: "http://localhost:8080/api/v1/dept",
        type:"POST",
        contentType:"application/json",
        data: JSON.stringify(jsonData),
        dataType:"json",
        
        success:function(response){
            if(response>0){
                alert("부서가 등록되었습니다.");
                location.reload();//자바스크립트에서 제공해주는 새로고침

            }
            else{
                alert("이미 가입된 부서번호입니다. 🤷‍♂️")
            }
            
        }
    });
}




function getDeptByDeptno(){
    var deptno = $("#u_deptno").val();
    console.log("클릭한 부서번호는?: "+ deptno);
    $.ajax({
        url: "http://localhost:8080/api/v1/dept/"+deptno,
        type:"DELETE",
        dataType:"json",
        
        success:function(response){
            alert(deptno+" 부서가 삭제됨");
            location.reload();//자바스크립트에서 제공해주는 새로고침 
        }
    });
}