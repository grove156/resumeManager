var main = {
    init : function (){
        var _this = this;


        $('#submit-button').on('click', function(){
            _this.save();
        });
    },

    save : function(){
        
        var title = $("#resume-title").val();
        

        var userId = $("#user-id").val();

        $.ajax({
            type: 'POST',
            url: '/' + userId +'/resume',
            dataType: 'text',
            contentType: "application/json; charset=utf-8",
            data: title
        }).done(function(){
            alert('your resume has been successfully created!');
            window.location.href = '/dashboard';
        }).fail(function(e){
            alert("pleas try again, your resume not created for some reason");
        });
    }
};

main.init();