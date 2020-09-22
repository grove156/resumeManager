
    $('.modal-container').hide();
    $('.modal1').hide();

$(document).ready(function() { 

    
    $('.modal-container').hide();
    $('.modal1').hide();

    $('.cancel').click(function(){
        $('.modal-container').hide();
        $('.modal1').hide();
    });

});

// Update Ajax
function updateEducationAjax(resumeId){
    var educationId = $('#hiddenEducationId').val();

    var data = {
        study_at: $('#studyAt').val(),
        major: $('#major').val(),
        start_date: $('#schoolStart').val(),
        end_date: $('#schoolEnd').val()
    };

    $.ajax({
        type: 'PATCH',
        url: '/resume/'+ resumeId + '/education/' + educationId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data)
    }).done(function(){
        alert('successfully updated!');
        $('.modal-container').hide();
        $('.modal1').hide();
        window.location.reload();
    }).fail(function(error){
        alert('update failed!');
    });
}

function updateExperienceAjax(resumeId){
    var experienceId = $('#hiddenExperienceId').val();

    var data = {
        work_at: $('#workAt').val(),
        position: $('#position').val(),
        role: $('#role').val(),
        start_date: $('#companyStart').val(),
        end_date: $('#companyEnd').val()
    };

    $.ajax({
        type: 'PATCH',
        url: '/resume/'+ resumeId + '/experience/' + experienceId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data)
    }).done(function(){
        alert('successfully updated!');
        $('.modal-container').hide();
        $('.modal1').hide();
        window.location.reload();
    }).fail(function(error){
        alert('update failed!');
    });
}

function updateCertificateAjax(resumeId){
    var certificateId = $('#hiddenCertificateId').val();

    var data = {
        certificate_title: $('#certificateTitle').val(),
        score: $('#score').val(),
        valid_duration: $('#validDuration').val(),
        issued_date: $('#issueDate').val(),
    };

    $.ajax({
        type: 'PATCH',
        url: '/resume/'+ resumeId + '/certificate/' + certificateId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data)
    }).done(function(){
        alert('successfully updated!');
        $('.modal-container').hide();
        $('.modal1').hide();
        window.location.reload();
    }).fail(function(error){
        alert('update failed!');
    });
}

function updateCoverletterAjax(resumeId){
    var coverletterId = $('#hiddenCoverletterId').val();

    var data = {
        title: $('#coverletterTitle').val(),
        content: $('.note-editable').text()
    };

    $.ajax({
        type: 'PATCH',
        url: '/resume/'+ resumeId + '/coverletter/' + coverletterId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data)
    }).done(function(){
        alert('successfully updated!');
        $('.modal-container').hide();
        $('.modal1').hide();
        window.location.reload();
    }).fail(function(error){
        alert('update failed!');
    });
}

// Update functions
function updateEducation(educationId, resumeId){
    $.ajax({
        type: 'GET',
        url: '/resume/'+ resumeId + '/education/' + educationId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8"
    }).done(function(eudcationData){
        $('#studyAt').val(eudcationData.study_at);
        $('#major').val(eudcationData.major);
        $('#schoolStart').val(eudcationData.start_date);
        $('#schoolEnd').val(eudcationData.end_date);

        $('#hiddenEducationId').val(educationId);

        $('.modal-container').show();
        $('.education--modal').show();

    }).fail(function(error){
        alert("update fail!");
    });
}

function updateExperience(experienceId, resumeId){
    $.ajax({
        type: 'GET',
        url: '/resume/'+ resumeId + '/experience/' + experienceId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8"
    }).done(function(experienceData){
        $('#workAt').val(experienceData.work_at);
        $('#position').val(experienceData.position);
        $('#role').val(experienceData.role);
        $('#companyStart').val(experienceData.start_date);
        $('#campanyEnd').val(experienceData.end_date);

        $('#hiddenExperienceId').val(experienceId);

        $('.modal-container').show();
        $('.experience--modal').show();

    }).fail(function(error){
        alert("update fail!");
    });
}

function updateCertificate(certificateId, resumeId){
    $.ajax({
        type: 'GET',
        url: '/resume/'+ resumeId + '/certificate/' + certificateId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8"
    }).done(function(certificateData){
        $('#certificateTitle').val(certificateData.certificate_title);
        $('#score').val(certificateData.score);
        $('#validDuration').val(certificateData.valid_duration);
        $('#issueDate').val(certificateData.issued_date);

        $('#hiddenCertificateId').val(certificateId);

        $('.modal-container').show();
        $('.certificate--modal').show();

    }).fail(function(error){
        alert("update fail!");
    });
}

function updateCoverletter(coverletterId, resumeId){
    $.ajax({
        type: 'GET',
        url: '/resume/'+ resumeId + '/coverletter/' + coverletterId,
        dataType: 'json',
        contentType: "application/json; charset=utf-8"
    }).done(function(CVData){
        $('#coverletterTitle').val(CVData.title);
        $('.note-editable').text(CVData.content);

        $('#hiddenCoverletterId').val(coverletterId);

        $('.modal-container').show();
        $('.coverletter--modal').show();

    }).fail(function(error){
        alert("update fail!");
    });
}

// Delete functions 
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
        alert(error);
        alert("delete fail!");
    });
};

function deleteExperience(experienceId, resumeId){

    $.ajax({
        type: 'DELETE',
        url: '/resume/'+ resumeId + '/experience/' + experienceId,
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
        url: '/resume/'+ resumeId + '/certificate/' + certificateId,
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
        url: '/resume/'+ resumeId + '/coverletter/' + coverletterId,
        dataType: 'text',
        contentType: "application/json; charset=utf-8"
    }).done(function(){
        alert('delete sucessfully!');
        window.location.reload();
    }).fail(function(error){
        alert("delete fail!");
    });
};