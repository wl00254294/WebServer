
var app = angular.module('homeapp', []);

app.controller('MainCtrl', ['$scope', '$window', function($scope, $window) {
  $scope.memId = $window.memId;
  $scope.templates =
	    [{ name: 'dashboard.html', url: 'dashboard'},
	     { name: 'report_week.html', url: 'weekreport'},
	     { name: 'report_month.html', url: 'monthreport'},
	     { name: 'report_cost.html', url: 'costreport'},
	     { name: 'test.html', url: 'test'}];
  $scope.template = $scope.templates[0];
  
  $scope.loadpage = function(id) {
   $scope.template = $scope.templates[id];
  };
    
}]);


//dahboard.html
app.controller('DashboardCtrl', ['$scope', '$window', function($scope, $window) {
	 
	  $scope.orders=$window.orders;
	  $scope.notifys=$window.notifys;
	    
}]);

//report.html
app.controller('ReportCtrl', ['$scope', '$window', function($scope, $window) {
	   	
	  $scope.report_cost=function()
	  {
		  $window.alert("ssss");
	  }
	
	  $scope.report_month=function(){
			var data=[
			          {
			              "categorie": "平台1", 
			              "values": [
			                  {
			                      "value": 1, 
			                      "rate": "本周"
			                  }, 
			                  {
			                      "value": 4, 
			                      "rate": "上周"
			                  }
			              ]
			          }, 
			          {
			              "categorie": "平台2", 
			              "values": [
			                  {
			                      "value": 11, 
			                      "rate": "本周"
			                  }, 
			                  {
			                      "value": 21, 
			                      "rate": "上周"
			                  }
			              ]
			          }, 
			          {
			              "categorie": "平台3", 
			              "values": [
			                  {
			                      "value": 3, 
			                      "rate": "本周"
			                  }, 
			                  {
			                      "value": 22, 
			                      "rate": "上周"
			                  }
			              ]
			          }, 
			          {
			              "categorie": "平台4", 
			              "values": [
			                  {
			                      "value": 12, 
			                      "rate": "本周"
			                  }, 
			                  {
			                      "value": 7, 
			                      "rate": "上周"
			                  }
			              ]
			          }, 
			          {
			              "categorie": "平台5", 
			              "values": [
			                  {
			                      "value": 6, 
			                      "rate": "本周"
			                  }, 
			                  {
			                      "value": 15, 
			                      "rate": "上周"
			                  }
			              ]
			          }, 
			          {
			              "categorie": "平台6", 
			              "values": [
			                  {
			                      "value": 6, 
			                      "rate": "本周"
			                  }, 
			                  {
			                      "value": 6, 
			                      "rate": "上周"
			                  }
			              ]
			          }
			      ];
			
			var margin = {top: 20, right: 20, bottom: 30, left: 40},
		    width = 960 - margin.left - margin.right,
		    height = 500 - margin.top - margin.bottom;

			var x0 = d3.scale.ordinal()
		    	.rangeRoundBands([0, width], .1);

			var x1 = d3.scale.ordinal();

			var y = d3.scale.linear()
		    	.range([height, 0]);

			var xAxis = d3.svg.axis()
				.scale(x0)
				.tickSize(0)
				.orient("bottom");

			var yAxis = d3.svg.axis()
		    	.scale(y)
		    	.orient("left");

			var color = d3.scale.ordinal()
		    	.range(["#92c5de","#f4a582","#d5d5d5","#ca0020","#0571b0"]);

			var svg = d3.select('#report').append("svg")
		    	.attr("width", width + margin.left + margin.right)
		    	.attr("height", height + margin.top + margin.bottom)
		    	.append("g")
		    	.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
		
			var categoriesNames = data.map(function(d) { return d.categorie; });
			var rateNames = data[0].values.map(function(d) { return d.rate; });

			x0.domain(categoriesNames);
			x1.domain(rateNames).rangeRoundBands([0, x0.rangeBand()]);
			y.domain([0, d3.max(data, function(categorie) { return d3.max(categorie.values, function(d) { return d.value; }); })]);

			svg.append("g")
			  .attr("class", "x axis")
		      .attr("transform", "translate(0," + height + ")")
		      .call(xAxis)
		      .style('font-size','x-large');

			svg.append("g")
		      .attr("class", "y axis")
		      .style('opacity','0')
		      .call(yAxis)
		      .append("text")
		      .attr("transform", "rotate(-90)")
		      .attr("y", 6)
		      .attr("dy", ".71em")
		      .style("text-anchor", "end")
		      .style('font-weight','bold')
		      .style('font-size','x-large')
		      .text("Value");

			svg.select('.y').transition().duration(500).delay(1300).style('opacity','1')
			;

			var slice = svg.selectAll(".slice")
		      .data(data)
		      .enter().append("g")
		      .attr("class", "g")
		      .attr("transform",function(d) { return "translate(" + x0(d.categorie) + ",0)"; });

			slice.selectAll("rect")
		      .data(function(d) { return d.values; })
		      .enter().append("rect")
		      .attr("width", x1.rangeBand())
		      .attr("x", function(d) { return x1(d.rate); })
		      .style("fill", function(d) { return color(d.rate) })
		      .attr("y", function(d) { return y(0); })
		      .attr("height", function(d) { return height - y(0); })
		      .on("mouseover", function(d) {
		          d3.select(this).style("fill", d3.rgb(color(d.rate)).darker(2));
		      })
		      .on("mouseout", function(d) {
		          d3.select(this).style("fill", color(d.rate));
		      });

			slice.selectAll("rect")
		      .transition()
		      .delay(function (d) {return Math.random()*1000;})
		      .duration(1000)
		      .attr("y", function(d) { return y(d.value); })
		      .attr("height", function(d) { return height - y(d.value); });

		  //Legend
			var legend = svg.selectAll(".legend")
		      .data(data[0].values.map(function(d) { return d.rate; }).reverse())
		      .enter().append("g")
		      .attr("class", "legend")
		      .attr("transform", function(d,i) { return "translate(0," + i * 20 + ")"; })
		      .style("opacity","0");

			legend.append("rect")
		      .attr("x", width - 18)
		      .attr("width", 18)
		      .attr("height", 18)
		      .style("fill", function(d) { return color(d); });

			legend.append("text")
		      .attr("x", width - 24)
		      .attr("y", 9)
		      .attr("dy", ".35em")
		      .style("text-anchor", "end")
		      .style('font-size','x-large')
		      .text(function(d) {return d; });

			legend.transition().duration(500).delay(function(d,i){ return 1300 + 100 * i; }).style("opacity","1");
			
		  };
	
	
	  $scope.report_week=function(){
		var data=$window.weekdata;
		
		var margin = {top: 20, right: 20, bottom: 30, left: 40},
	    width = 960 - margin.left - margin.right,
	    height = 500 - margin.top - margin.bottom;

		var x0 = d3.scale.ordinal()
	    	.rangeRoundBands([0, width], .1);

		var x1 = d3.scale.ordinal();

		var y = d3.scale.linear()
	    	.range([height, 0]);

		var xAxis = d3.svg.axis()
			.scale(x0)
			.tickSize(0)
			.orient("bottom");

		var yAxis = d3.svg.axis()
	    	.scale(y)
	    	.orient("left");

		var color = d3.scale.ordinal()
	    	.range(["#92c5de","#f4a582","#d5d5d5","#ca0020","#0571b0"]);

		var svg = d3.select('#report').append("svg")
	    	.attr("width", width + margin.left + margin.right)
	    	.attr("height", height + margin.top + margin.bottom)
	    	.append("g")
	    	.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
		var categoriesNames = data.map(function(d) { return d.categorie; });
		var rateNames = data[0].values.map(function(d) { return d.rate; });

		x0.domain(categoriesNames);
		x1.domain(rateNames).rangeRoundBands([0, x0.rangeBand()]);
		y.domain([0, d3.max(data, function(categorie) { return d3.max(categorie.values, function(d) { return d.value; }); })]);

		svg.append("g")
		  .attr("class", "x axis")
	      .attr("transform", "translate(0," + height + ")")
	      .call(xAxis)
	      .style('font-size','x-large');

		svg.append("g")
	      .attr("class", "y axis")
	      .style('opacity','0')
	      .call(yAxis)
	      .append("text")
	      .attr("transform", "rotate(-90)")
	      .attr("y", 6)
	      .attr("dy", ".71em")
	      .style("text-anchor", "end")
	      .style('font-weight','bold')
	      .style('font-size','x-large')
	      .text("Value");

		svg.select('.y').transition().duration(500).delay(1300).style('opacity','1')
		;

		var slice = svg.selectAll(".slice")
	      .data(data)
	      .enter().append("g")
	      .attr("class", "g")
	      .attr("transform",function(d) { return "translate(" + x0(d.categorie) + ",0)"; });

		slice.selectAll("rect")
	      .data(function(d) { return d.values; })
	      .enter().append("rect")
	      .attr("width", x1.rangeBand())
	      .attr("x", function(d) { return x1(d.rate); })
	      .style("fill", function(d) { return color(d.rate) })
	      .attr("y", function(d) { return y(0); })
	      .attr("height", function(d) { return height - y(0); })
	      .on("mouseover", function(d) {
	          d3.select(this).style("fill", d3.rgb(color(d.rate)).darker(2));
	      })
	      .on("mouseout", function(d) {
	          d3.select(this).style("fill", color(d.rate));
	      });

		slice.selectAll("rect")
	      .transition()
	      .delay(function (d) {return Math.random()*1000;})
	      .duration(1000)
	      .attr("y", function(d) { return y(d.value); })
	      .attr("height", function(d) { return height - y(d.value); });

	  //Legend
		var legend = svg.selectAll(".legend")
	      .data(data[0].values.map(function(d) { return d.rate; }).reverse())
	      .enter().append("g")
	      .attr("class", "legend")
	      .attr("transform", function(d,i) { return "translate(0," + i * 20 + ")"; })
	      .style("opacity","0");

		legend.append("rect")
	      .attr("x", width - 18)
	      .attr("width", 18)
	      .attr("height", 18)
	      .style("fill", function(d) { return color(d); });

		legend.append("text")
	      .attr("x", width - 24)
	      .attr("y", 9)
	      .attr("dy", ".35em")
	      .style("text-anchor", "end")
	      .style('font-size','x-large')
	      .text(function(d) {return d; });

		legend.transition().duration(500).delay(function(d,i){ return 1300 + 100 * i; }).style("opacity","1");
		
	  };
	  
	

	    
}]);
