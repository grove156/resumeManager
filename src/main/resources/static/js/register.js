var main = {
        init : function (){
            var _this = this;

            $('#submit-button').on('click', function(){
                _this.save();
            });
        },

        save : function(){
            var data = {
                email: $("#email").val(),
                password: $("#password").val(),
                name: $('#name').val(),
                date_of_birth: $('#datepicker').val(),
                phone_number: $("#phone_number").val()
            };

            console.log(data);

            $.ajax({
                type: 'POST',
                url: '/register',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data)
            }).done(function(){
                alert('successfully registered!');
                window.location.href = '/login';
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
     
        }
};

main.init();