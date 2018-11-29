$(function() {
   $("#deviceSelector").change(function(){
        var selectedDevice = $(this).val();
        $.ajax({    
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            url: 'http://localhost:8080/REM/api/hyperledger/device?deviceId=' + selectedDevice,
            success: function(data){
                console.log('success: ' + data);
                $("#eq-detail-deviceID").text(selectedDevice);
                $("#eq-detail-edgeID").text(data.EdgeID);
                $("#eq-detail-timeStamp").text(data.Timestamp);
                $("#eq-detail-status").text(data.DStatus);
                $("#eq-detail-container").removeClass('hide');
            }
        });

   });
});