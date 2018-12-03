$(function() {
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        url: '/REM/api/hyperledger/devices',
        success: function(data) {
            var devices = data.values;
            var $deviceSelector = $("#deviceSelector");
            $.each(devices, function(index, device) {
                $deviceSelector.append("<option>" + device.id + "</option>");
            });
        }
    });

    function createUpdatePieChart() {
        var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            title: {
                text: "Desktop Search Engine Market Share - 2016"
            },
            data: [{
                type: "pie",
                startAngle: 240,
                yValueFormatString: "##0.00\"%\"",
                indexLabel: "{label} {y}",
                dataPoints: [{
                        y: 79.45,
                        label: "Google"
                    },
                    {
                        y: 7.31,
                        label: "Bing"
                    },
                    {
                        y: 7.06,
                        label: "Baidu"
                    },
                    {
                        y: 4.91,
                        label: "Yahoo"
                    },
                    {
                        y: 1.26,
                        label: "Others"
                    }
                ]
            }]
        });
        chart.render();
    }


    function updateDeviceInfo() {
        var selectedDevice = $('#deviceSelector').val();
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            url: '/REM/api/hyperledger/device?deviceId=' + selectedDevice,
            success: function(data) {
                $("#eq-detail-deviceID").text(selectedDevice);
                $("#eq-detail-edgeID").text(data.EdgeID);
                $("#eq-detail-timeStamp").text(data.Timestamp);
                $("#eq-detail-status").text(data.DStatus);
                $("#device-details-container").removeClass('hide');
            }
        });

        updateDeviceHistory();
    }

    function updateDeviceHistory() {
        $('#eq-details-table tbody').empty();
        $('#eq-details-table').addClass('hide');
        var selectedDevice = $('#deviceSelector').val();
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            url: '/REM/api/hyperledger/deviceHistory?deviceId=' + selectedDevice,
            success: function(data) {
                var history = data.values;
                var $eqDetailsTable = $("#eq-details-table-data");
                for (i = history.length - 1; i >= 0; i--) {
                    if (history[i].EdgeID !== '' && history[i].DStatus !== '' && history[i].Timestamp !== '') {
                        $eqDetailsTable.append("<tr><td>" + history[i].EdgeID + "</td><td>" + history[i].DStatus + "</td><td>" + history[i].Timestamp + "</td></tr>");
                    }
                }
            }
        });
        $('#eq-details-table').removeClass('hide');
    }

    $("#deviceSelector").on('change', function() {
        updateDeviceInfo();
    });

    $("#sync-eq-details").on('click', function() {
        updateDeviceInfo();
    });

});