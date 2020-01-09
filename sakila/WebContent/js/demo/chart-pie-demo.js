// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
let label = new Array;
let datas = new Array;
$.ajax({
	url : "/sakila/IndexPieChartController",
	method : "POST",
	success : function(json){
		console.log(json);
		$.each(json, function(key, item){
			console.log(key);
			label.push(key);
			console.log(item);
			datas.push(item);
		});

		console.log(label);
		console.log(datas);

		
	}
});
var ctx = document.getElementById("myPieChart").getContext('2d');
var myPieChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: label,
    datasets: [{
      data: datas,
      backgroundColor: '#36b9cc',
      hoverBackgroundColor: '#2c9faf',
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
    
  },
});
