$(document).ready(function(){

});

function updateUser(userId){
    var data = {
        password: $("#password").val(),
        name: $("#name").val(),
        date_of_birth: $("#dateOfBirth").val(),
        phone_number: $("#PhoneNumber").val()
    };

    $.ajax({
        type: 'PATCH',
        url: '/user/'+userId,
        dataType: 'text',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data)
    }).done(function(){
        alert('successfully updated!');
        window.location.href = '/dashboard';
    }).fail(function(error){
        alert('update failed!');
    });
}