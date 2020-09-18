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

        console.log(userId);

        $.ajax({
            type: 'POST',
            url: '/' + userId +'/resume',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: title
        }).done(function(){
            alert('successfully created!');
            window.location.href = '/dashboard';
        });
 
        // .fail(function(error){
        //     alert(JSON.stringify(error));
        // })
    }
};

main.init();