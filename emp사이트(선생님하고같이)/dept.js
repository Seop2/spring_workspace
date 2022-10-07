function updateDept(){
    $('.update-popup').css('display', 'block');
}
getDept();//함수 호출

function getDept(){
    $.ajax({
        url: 'http://localhost:8080/api/v1/dept',
        type : 'GET',
        dataType : 'json',
        success : function(response){
        
            var html ='';
            console.log(response);
            for(var i=0; i<response.length; i++){
                html += '<tr onclick="getdeptByDeptno('+response[i].DEPTNO+')"><td>'+response[i].DEPTNO+'</td><td>'+response[i].DNAME+'</td><td>'+response[i].LOC
                +'</td><td>'+response[i].empno+'</td></tr>'
            }
            $("#deptData").append(html);//table바인딩 작업
            // var html = '';
            // for(var i=0; i<response.list.length; i++){
            //     html += '<tr onclick="getEmpByEmpno('+response.list[i].DEPTNO+')"><td>'+response.list[i].DEPTNO+'</td><td>'+response.list[i].DNAME+'</td><td>'+response.list[i].LOC
            //     +'</td><td>'+response.list[i].empno+'</td></tr>'
            // }
            // $('#deptData').append(html);
        }
    });
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
    // console.log("입력한 부서이름은 =>"+dname);
    // console.log("입력한 부서번호는 =>"+deptno);
    // console.log("입력한 부서근무지 =>"+loc);
    
   //빈값 체크 로직 구현
    // if(empno == ""){
    //     alert("사원번호를 입력하세요");
    //     $("#i_empno").focus();
    //     return false;
    // }
    // if(ename == ""){
    //     alert("사원이름을 입력하세요");
    //     $("#i_ename").focus();
    //     return false;
    // }
    // if(job == ""){
    //     alert("직책을 입력하세요");
    //     $("#i_job").focus();
    //     return false;
    // }
    // if(sal == ""){
    //     alert("급여를 입력하세요");
    //     $("#i_sal").focus();
    //     return false;
    // }
    // if(comm == ""){
    //     alert("보너스를 입력하세요");
    //     $("#i_comm").focus();
    //     return false;
    // }
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
//부서 삭제
//부서목록 삭제: 구글링을 해서 EMP테이블 ON DELETE 설정을 추가하는 법 검색해서 적용하기



function deptDel(){
    var deptno = $('#u_deptno').val();
    var ename = $('#u_ename').val();
    
    console.log("클릭한 부서번호는?: "+ deptno);
    $.ajax({
        url: "http://localhost:8080/api/v1/dept/deptno/"+deptno,
        type:"PATCH",
        dataType:"json",
        
        success:function(response){
            alert(ename+" 님이 회원탈퇴하셨습니다");
            //DB에서   
        }
    });
}