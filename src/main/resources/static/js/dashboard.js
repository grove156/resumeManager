$(document).ready(function() { 

    function deleteResume(resumeId, userId){
        $.ajax({
            type: 'DLETE',
            url: '/'+ userId + '/resume/' + resumeId,
            dataType: 'json',
            contentType: "application/json; charset=utf-8"
        }).done(function(){
            alert('delete sucessfully!');
            window.location.href = '/dashboard';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    };

});