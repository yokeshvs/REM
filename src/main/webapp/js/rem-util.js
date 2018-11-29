$(function() {
    $("#deviceSelector").change(function() {
        var selectedDevice = $(this).val();
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            url: 'http://localhost:8080/REM/api/hyperledger/device?deviceId=' + selectedDevice,
            success: function(data) {
                console.log('success: ' + data);
                $("#eq-detail-deviceID").text(selectedDevice);
                $("#eq-detail-edgeID").text(data.EdgeID);
                $("#eq-detail-timeStamp").text(data.Timestamp);
                $("#eq-detail-status").text(data.DStatus);
                $("#eq-detail-container").removeClass('hide');
            }
        });

    });

    $("#ledgerSubmitBtn").click(function() {
        var patientName = $("#name").val();
        var patientId = $("#patientId").val();
        var initialStage = $("#initialStage").val();
        var finalStage = $("#finalStage").val();
        var mrnNo = $("#mrnNo").val();
        var admitDate = $("#admitDate").val();
        var dischargeDate = $("#dischargeDate").val();
        var deviceUsed = $('input[name=deviceUsed]:checked').val();
        alert(deviceUsed);
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                patientName: patientName,
                patientId: patientId,
                initialUlcerStage: initialStage,
                finalUlcerStage: finalStage,
                mrnNo: mrnNo,
                admitDate: admitDate,
                dischargeDate: dischargeDate,
                deviceUsed: deviceUsed
            }),
            url: 'http://localhost:8080/REM/api/hyperledger/patientLedger',
            success: function(data) {
                console.log('success: ' + data);
            }
        });

    });
});