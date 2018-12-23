$(function() {
	var devicesConfig;
//	addAjaxMask();
	$.ajax({
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        url: '/REM/api/firebase/devicesConfig',
        success: function(data) {
            devicesConfig = data;
        }
    });
	
	$.ajax({
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        url: '/REM/api/hyperledger/devices',
        success: function(data) {
            var devices = data.values;
            var $deviceSelector = $("#deviceUsed");
            $.each(devices, function(index, device) {
                $deviceSelector.append("<option value='" + device.id + "'>" + devicesConfig[device.id] + "</option>");
            });
//            removeAjaxMask();
        }
    });
    $("#ledgerSubmitBtn").on('click', function() {
        var patientName = $("#name").val();
        var patientId = $("#patientId").val();
        var initialStage = $("#initialStage").val();
        var finalStage = $("#finalStage").val();
        var mrnNo = $("#mrnNo").val();
        var admitDate = $("#admitDate").val();
        var dischargeDate = $("#dischargeDate").val();
        var deviceUsed = $("#deviceUsed").val();
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