$(function() {
    $("#ledgerSubmitBtn").on('click', function() {
        var patientName = $("#name").val();
        var patientId = $("#patientId").val();
        var initialStage = $("#initialStage").val();
        var finalStage = $("#finalStage").val();
        var mrnNo = $("#mrnNo").val();
        var admitDate = $("#admitDate").val();
        var dischargeDate = $("#dischargeDate").val();
        var deviceUsed = $('input[name=deviceUsed]:checked').val();
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                patientId: patientId,
                patientRecord: {
                	patientName: patientName,
                    initialUlcerStage: initialStage,
                    finalUlcerStage: finalStage,
                    mrnNo: mrnNo,
                    admitDate: admitDate,
                    dischargeDate: dischargeDate,
                    deviceUsed: deviceUsed
                }
            }),
            url: '/REM/api/hyperledger/patientLedger',
            success: function(data) {
                console.log('success: ' + data);
            }
        });

    });
});