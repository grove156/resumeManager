$(document).ready(function() {

    $('.modal--container').hide();
    $('.modal1').hide();

    $('#cancel').click(function(){
        $('.modal--container').hide();
        $('.modal1').hide();
    });

    $('.bxslider').bxSlider({
        auto: false,
        speed: 500,
        pause: 5000,
        mode: 'horizontal',
        autoControls: false,
        pager: false,
        caption: false,
        touchEnabled:false
    });
});

function getTemplate(resumeId){
    $('#templateResumeId').val(resumeId);
    $('.modal--container').show();
    $('.modal1').show();
}

function getPdfTemplate(){
    var templateId = $('input[name="template"]:checked').val();
    var resumeId = $('#templateResumeId').val();

    $.ajax({
        type: 'GET',
        url: '/resume/'+resumeId+'/'+templateId,
        dataType: 'html',
    }).done(function(html){
        // var wnd = window.open( '', 'resume', 'width=826, height=1169, menubar=yes, status=yes, toolbar=no');
        // wnd.document.write(html);
        window.location.href ='/resume/'+resumeId+'/'+templateId;
    }).fail(function(error){
        alert("getting pdf file failed!");
    });
}

function deleteResume(resumeId, userId){
    $.ajax({
        type: 'DELETE',
        url: '/'+ userId + '/resume/' + resumeId,
        dataType: 'text',
        contentType: "application/json; charset=utf-8"
    }).done(function(){
        alert('delete sucessfully!');
        window.location.href = '/dashboard';
    }).fail(function(error){
        alert(JSON.stringify(error));
    });
};

