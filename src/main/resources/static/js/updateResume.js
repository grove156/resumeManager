function deleteEducation(educationId, resumeId){

    $.ajax({
        type: 'DELETE',
        url: '/resume/'+ resumeId + '/education/' + educationId,
        dataType: 'text',
        contentType: "application/json; charset=utf-8"
    }).done(function(){
        alert('delete sucessfully!');
        window.location.reload();
    }).fail(function(error){
        alert("delete fail!");
    });
};

function deleteExperience(experienceId, resumeId){

    $.ajax({
        type: 'DELETE',
        url: '/resume/'+ resumeId + '/education/' + experienceId,
        dataType: 'text',
        contentType: "application/json; charset=utf-8"
    }).done(function(){
        alert('delete sucessfully!');
        window.location.reload();
    }).fail(function(error){
        alert("delete fail!");
    });
};

function deleteCertificate(certificateId, resumeId){

    $.ajax({
        type: 'DELETE',
        url: '/resume/'+ resumeId + '/education/' + certificateId,
        dataType: 'text',
        contentType: "application/json; charset=utf-8"
    }).done(function(){
        alert('delete sucessfully!');
        window.location.reload();
    }).fail(function(error){
        alert("delete fail!");
    });
};

function deleteCoverletter(coverletterId, resumeId){

    $.ajax({
        type: 'DELETE',
        url: '/resume/'+ resumeId + '/education/' + coverletterId,
        dataType: 'text',
        contentType: "application/json; charset=utf-8"
    }).done(function(){
        alert('delete sucessfully!');
        window.location.reload();
    }).fail(function(error){
        alert("delete fail!");
    });
};