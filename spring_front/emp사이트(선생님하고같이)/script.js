//사원 통계 정보 불러오기
getStatistics();//함수 호출
getEmp();//함수 호출

function getStatistics(){
    $.ajax({
        url: "http://localhost:8080/api/v1/emp/statistics",
        type:"GET",
        dataType:"json",
        
        success:function(response){
            console.log(response);
            $("#studentsCnt").append(response.empCount);
            $("#boardCnt").append(response.empSalAvg);
            $("#writerCnt").append(response.deptCount);
            $("#viewsCnt").append(response.empCommSum);
        }
    });
}
//사원 추가
function setEmp(){
    var empno = $("#i_empno").val();
    var ename = $("#i_ename").val();
    var job = $("#i_job").val();
    var sal = $("#i_sal").val();
    var comm = $("#i_comm").val();
    
    console.log("입력한 사원번호는 =>"+empno);
    console.log("입력한 사원이름은 =>"+ename);
    console.log("입력한 직책은 =>"+job);
    console.log("입력한 급여는 =>"+sal);
    console.log("입력한 보너스는 =>"+comm);
   //빈값 체크 로직 구현
    if(empno == ""){
        alert("사원번호를 입력하세요");
        $("#i_empno").focus();
        return false;
    }
    if(ename == ""){
        alert("사원이름을 입력하세요");
        $("#i_ename").focus();
        return false;
    }
    if(job == ""){
        alert("직책을 입력하세요");
        $("#i_job").focus();
        return false;
    }
    if(sal == ""){
        alert("급여를 입력하세요");
        $("#i_sal").focus();
        return false;
    }
    if(comm == ""){
        alert("보너스를 입력하세요");
        $("#i_comm").focus();
        return false;
    }

    var jsonData = {
        "empno":empno,
        "ename":ename,
        "job":job,
        "sal":sal,
        "comm":comm
        
    };
    //@CrossOrigin이 있어야 함
    //contentType: 서버에 보낼 데이터 타입
    //dataType: 서버에 응답 결과 데이터 타입
    //data:서버에 보낼 데이터
    //success: 요청 성공
    $.ajax({
        url: "http://localhost:8080/api/v1/emp",
        type:"POST",
        contentType:"application/json",
        data: JSON.stringify(jsonData),
        dataType:"json",
        
        success:function(response){
            if(response>0){
                alert("사원이 등록되었습니다.");
                location.reload();//자바스크립트에서 제공해주는 새로고침
            }
            
        }
    });
}
//전체 사원 조회하는 함수
function getEmp(){
    $.ajax({
        url: "http://localhost:8080/api/v1/emp",
        type:"GET",
        dataType:"json",
        success:function(response){
            var html = "";
            //for문을 이용해서 배열 출력하기
            for(var i=0; i<response.length; i++)
            {
                //부서이름이 SALES인 사원만 출력하기
                // if(response[i].dname =='SALES')
                // {
                //     console.log(response[i]);
                // }
                //사원목록에 사원 데이터 바인딩(==사원목록 HTML에 데이터 보여주기)
                //tbody태그 id: empData에 바인딩 하기!
                html += '<tr onclick="getEmpByEmpno('+response[i].empno+')"><td>'+response[i].empno+'</td><td>'+response[i].ename+'</td><td>'+response[i].job+'</td><td>'+response[i].sal+'</td><td>'+response[i].hiredate+'</td><td>'+response[i].dname+'</td></tr>';

            }
            $("#empData").append(html);//바인딩 작업
        }
    });
}

//tr태그[table] 클릭 이벤트
//클릭했을 때 사원번호 띄우기
//특정 사원 조회 함수
function getEmpByEmpno(empno){
    
    //alert("hello world!");
    console.log("클릭한 사원번호는?: "+ empno);
    $.ajax({
        url: "http://localhost:8080/api/v1/emp/empno/"+empno,
        type:"GET",
        dataType:"json",
        
        success:function(response){
            console.log(response);
            $('.update-popup').css('display','block');
            $('#u_empno').val(response.empno)
            $('#u_ename').val(response.ename)
            $('#u_job').val(response.job)
            $('#u_sal').val(response.sal)
            $('#u_comm').val(response.comm)   

        }
    });
}