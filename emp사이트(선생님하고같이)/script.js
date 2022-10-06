//사원 통계 정보 불러오기
getStatistics();//함수 호출
getEmp(1);//함수 호출

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
    var deptno = $("#i_deptno").val();
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
    if(deptno == ""){
        alert("부서번호를 입력하세요");
        $("#i_deptno").focus();
        return false;
    }

    var jsonData = {
        "empno":empno,
        "ename":ename,
        "job":job,
        "sal":sal,
        "comm":comm,
        "deptno":deptno,
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
            else{
                alert("이미 가입된 사원번호입니다. 🤷‍♂️")
            }
            
        }
    });
}
//전체 사원 조회하는 함수
function getEmp(pageNum){
    $.ajax({
        url: "http://localhost:8080/api/v1/emp?page="+pageNum,
        type:"GET",
        dataType:"json",
        success:function(response){
            $('#empData').empty();
            $('.pagination').empty();
            var html = "";
            console.log(response)
            //for문을 이용해서 배열 출력하기
            for(var i=0; i<response.list.length; i++)
            {
                html += '<tr onclick="getEmpByEmpno('+response.list[i].empno+')"><td>'+response.list[i].empno+'</td><td>'+response.list[i].ename+'</td><td>'+response.list[i].job+'</td><td>'+response.list[i].sal+'</td><td>'+response.list[i].hiredate+'</td><td>'+response.list[i].dname+'</td></tr>';
                //사원목록에 사원 데이터 바인딩(==사원목록 HTML에 데이터 보여주기)
                //tbody태그 id: empData에 바인딩 하기!

            }
            $("#empData").append(html);//table바인딩 작업

            var paginationHtml = '';
            if(response.hasPreviousPage){//이전버튼 여부 확인
                paginationHtml += '<a onclick="getEmp('+(pageNum-1)+')">Previous</a>';
            }
            for(var i=0; i<response.navigatepageNums.length; i++){//총 보여줄 페이지
                var page = response.navigatepageNums[i];
                paginationHtml += '<a onclick="getEmp('+page+')">'+page+'</a>'
            }
            if(response.hasNextPage){
                paginationHtml += '<a onclick="getEmp('+(pageNum+1)+')">Next</a>';
            }
            $('.pagination').append(paginationHtml)//페이지 바인딩 작업
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
//사원 정보 수정(insert랑 같다)
function updateEmp(){
    var empno = $('#u_empno').val();
    var ename = $('#u_ename').val();
    var job = $('#u_job').val();
    var sal = $('#u_sal').val();
    var comm = $('#u_comm').val();
    var jsonData = {
        "empno":empno,
        "ename":ename,
        "job":job,
        "sal":sal,
        "comm":comm
        
    };
    
    $.ajax({
        url: "http://localhost:8080/api/v1/emp",
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
//사원 삭제
//데이터는 곧 삭제
//실제로 기업에서는 데이터를 삭제하지 않고, 삭제 여부 컬럼을 추가해서
//탈퇴한 회원은 y 현재 회원은 n으로 관리한다
function fireEmp(){
    var empno = $('#u_empno').val();
    var ename = $('#u_ename').val();
    
    console.log("클릭한 사원번호는?: "+ empno);
    $.ajax({
        url: "http://localhost:8080/api/v1/emp/empno/"+empno,
        type:"PATCH",
        dataType:"json",
        
        success:function(response){
            alert(ename+" 님이 회원탈퇴하셨습니다");
            //DB에서   
        }
    });
}
//엑셀 다운로드
function downloadExcelFile(){
    console.log("EXCEL 다운");
    location.href = "http://localhost:8080/excel/download";
}