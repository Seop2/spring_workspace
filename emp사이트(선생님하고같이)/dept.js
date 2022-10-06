function updateDept(){
    $('.update-popup').css('display', 'block');
}
getDept();//함수 호출
//사원 추가
// function setDept(){
//     var deptno = $("#i_deptno").val();
//     var dname = $("#i_dname").val();
//     var dloc = $("#i_dloc").val();
//     console.log("입력한 부서번호는 =>"+deptno);
//     console.log("입력한 부서이름은 =>"+dname);
//     console.log("입력한 부서근무지 =>"+dloc);
//    //빈값 체크 로직 구현
//     if(deptno == ""){
//         alert("부서번호를 입력하세요");
//         $("#i_deptno").focus();
//         return false;
//     }
//     if(dname == ""){
//         alert("부서이름을 입력하세요");
//         $("#i_dname").focus();
//         return false;
//     }
//     if(dloc == ""){
//         alert("부서근무지 입력하세요");
//         $("#i_dloc").focus();
//         return false;
//     }
    

//     var jsonData = {
//         "deptno":deptno,
//         "dname":dname,
//         "loc":dloc,
//     };
//     //@CrossOrigin이 있어야 함
//     //contentType: 서버에 보낼 데이터 타입
//     //dataType: 서버에 응답 결과 데이터 타입
//     //data:서버에 보낼 데이터
//     //success: 요청 성공
//     $.ajax({
//         url: "http://localhost:8080/api/v1/dept",
//         type:"POST",
//         contentType:"application/json",
//         data: JSON.stringify(jsonData),
//         dataType:"json",
        
//         success:function(response){
//             if(response>0){
//                 alert("사원이 등록되었습니다.");
//                 location.reload();//자바스크립트에서 제공해주는 새로고침

//             }
//             else{
//                 alert("이미 가입된 사원번호입니다. 🤷‍♂️")
//             }
            
//         }
//     });
// }
//전체 부서 조회하는 함수
// function getDept(pageNum){
//     $.ajax({
//         url: "http://localhost:8080/api/v1/dept?page="+pageNum,
//         type:"GET",
//         dataType:"json",
//         success:function(response){
//             $('#deptData').empty();
//             $('.pagination').empty();
//             var html = "";
//             console.log(response)
//             //for문을 이용해서 배열 출력하기
//             for(var i=0; i<response.list.length; i++)
//             {
//                 html += '<tr onclick="getDeptno('+response.list[i].deptno+')"><td>'+response.list[i].dname+'</td><td>'+response.list[i].dloc+'</td><td>'+response.list[i].job+'</td><td>'+response.list[i].sal+'</td><td>'+response.list[i].hiredate+'</td><td>'+response.list[i].dname+'</td></tr>';
//                 //사원목록에 사원 데이터 바인딩(==사원목록 HTML에 데이터 보여주기)
//                 //tbody태그 id: empData에 바인딩 하기!

//             }
//             $("#empData").append(html);//table바인딩 작업

//             var paginationHtml = '';
//             if(response.hasPreviousPage){//이전버튼 여부 확인
//                 paginationHtml += '<a onclick="getDept('+(pageNum-1)+')">Previous</a>';
//             }
//             for(var i=0; i<response.navigatepageNums.length; i++){//총 보여줄 페이지
//                 var page = response.navigatepageNums[i];
//                 paginationHtml += '<a onclick="getDept('+page+')">'+page+'</a>'
//             }
//             if(response.hasNextPage){
//                 paginationHtml += '<a onclick="getDept('+(pageNum+1)+')">Next</a>';
//             }
//             $('.pagination').append(paginationHtml)//페이지 바인딩 작업
//         }
//     });
// }
function getDept(){
    $.ajax({
        url: 'http://localhost:8080/api/v1/dept',
        type : 'GET',
        dataType : 'json',
        success : function(response){
            console.log(response);
            var html = '';
            for(var i=0; i<response.length; i++){
                html += '<tr onclick="getEmpByEmpno('+response[i].DEPTNO+')"><td>'+response[i].DNAME+'</td><td>'+response[i].LOC+'</td><td>'+response[i].LOC
                +'</td><td>'+response[i].empno+'</td></tr>'
            }
            $('#deptData').append(html);
        }
    });
}
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
//부서 삭제
// function deleteDept(){
//     var deptno = $('#d_deptno').val();
//     var dname = $('#d_dname').val();
    
//     console.log("클릭한 부서번호는?: "+ deptno);
//     $.ajax({
//         url: "http://localhost:8080/api/v1/dept/deptno/"+deptno,
//         type:"DELETE",
//         dataType:"json",
        
//         success:function(response){
//             alert(dname+" 부서가 삭제됨");
//             //DB에서   
//         }
//     });
// }