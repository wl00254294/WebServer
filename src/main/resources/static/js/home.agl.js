
var app = angular.module('homeapp', []);

app.controller('MainCtrl', ['$scope', '$window', function($scope, $window) {

  $scope.plates = $window.plates;
  $scope.templates =
	    [{ name: 'dashboard.html', url: 'dashboard'},
	     { name: 'report_week.html', url: 'weekreport'},
	     { name: 'report_month.html', url: 'monthreport'},
	     { name: 'report_cost.html', url: 'costreport'+'?plates='+$scope.plates},
	     { name: 'translate.html', url: 'translate'+'?plates='+$scope.plates},
	     { name: 'test.html', url: 'test'}];
  $scope.template = $scope.templates[0];
  
  $scope.loadpage = function(id) {
   $scope.template = $scope.templates[id];
  };
  
  $scope.directHome = function() {
	  $window.location.href='/websrv/home';
  };
    
}]);


//translate.html
app.controller('TranslateCtrl', ['$scope', '$window', function($scope, $window) {
	$scope.date = new Date();

	    
}]);


//dahboard.html
app.controller('DashboardCtrl', ['$scope', '$window', function($scope, $window) {
	 
	  $scope.orders=$window.orders;
	  $scope.notifys=$window.notifys;
	    
}]);

//report.html
app.controller('ReportCtrl', ['$scope','$window', function($scope,$window) {
	
	  //$scope.platenames=['Plate1','Plate2','Plate3','Plate4','Plate5','Plate6'];
	  
	
	  $scope.platenames=$window.plates;
	   
	  $scope.costdata=$window.costdata;
	   	
	  $scope.report_cost=function(flag)
	  {
		  	  
		  //$scope.$log.log(name);
		 
		 // var dataset=[{"id":"1","map":"tw","count":530,"badge":73,"name_tw":"\u53f0\u7063","name_cn":"\u53f0\u6e7e\u9ad8\u6821","name_jp":"\u53f0\u6e7e\u9ad8\u6821","name_en":"Taiwan"},{"id":"2","map":"hk","zoom":"11","zoom_m":"11","count":426,"badge":20,"name_tw":"\u9999\u6e2f","name_cn":"\u9999\u6e2f","name_jp":"\u9999\u6e2f","name_en":"Hong Kong","lng":"114.154602","lat":"22.354245"},{"id":"3","map":"jr","count":782,"badge":17,"name_tw":"\u53f0\u7063\u570b\u4e2d","name_cn":"\u53f0\u6e7e\u4e2d\u5b66","name_jp":"\u53f0\u6e7e\u4e2d\u5b66","name_en":"Taiwan Junior High School"},{"id":"4","map":"my","count":203,"badge":41,"name_tw":"\u99ac\u4f86\u897f\u4e9e","name_cn":"\u9a6c\u6765\u897f\u4e9a","name_jp":"\u30de\u30ec\u30fc\u30b7\u30a2","name_en":"Malysia"},{"id":"5","map":"bn","count":18,"badge":50,"name_tw":"\u6c76\u840a","name_cn":"\u6587\u83b1","name_jp":"\u30d6\u30eb\u30cd\u30a4","name_en":"Brunei"},{"id":"6","map":"sg","count":63,"badge":65,"name_tw":"\u65b0\u52a0\u5761","name_cn":"\u65b0\u52a0\u5761","name_jp":"\u30b7\u30f3\u30ac\u30dd\u30fc\u30eb","name_en":"Singapore","lng":"103.806496","lat":"1.349736"},{"id":"7","map":"mo","count":43,"badge":70,"name_tw":"\u6fb3\u9580","name_cn":"\u6fb3\u95e8","name_jp":"\u30de\u30ab\u30aa","name_en":"Macau"},{"id":"8","map":"id","count":21,"badge":71,"name_tw":"\u5370\u5c3c","name_cn":"\u5370\u5c3c","name_jp":"\u30a4\u30f3\u30c9\u30cd\u30b7\u30a2","name_en":"Indonesia"}]   
		  var datall=($scope.costdata)[flag];
		  var dataset=datall.info;
		 
		  // sum
		  var tots = d3.sum(dataset, function(d) { 
	            return d.count; 
	        });
		  
		  //scale size
		  var _g1 = d3.format(".2f");
		  
		  
		//Width and height   
		      var w = 600;   
		      var h = 400;   
		      var r = Math.min(w, h) / 3;   
		      var labelr = r+20;   
		  
		      var outerRadius = h / 3;   
		      var innerRadius = 0;   
		      var arc = d3.svg.arc()   
		              .innerRadius(innerRadius)   
		              .outerRadius(outerRadius);   
		         
		      var pie = d3.layout.pie().value(function(d) { return d.count; }).sort( function(d) { return null; } );;   
		         
		      //Easy colors accessible via a 10-step ordinal scale   
		      var color = d3.scale.category10();   
		  
		      //Create SVG element   
		      var svg = d3.select("#"+datall.platename)   
		            .append("svg")   
		            .attr("width", w)   
		            .attr("height", h);   
		         
		      svg.append("g") 
		  .attr("class", "labels");   
		      //Set up groups   
		      var arcs = svg.selectAll("g.arc")
		              .data(pie(dataset))   
		              .enter()   
		              .append("g")   
		              .attr("class", "arc")   
		              .attr("transform", "translate(" + w/3 + "," + h/2 + ")")
		              ;   
		         
		      //Draw arc paths   
		      arcs.append("path")
		      	.transition().delay(function(d,i) {
		    	  return i * 100; }).duration(100)  
		          .attr("fill", function(d, i) {   
		            return color(i);   
		          })   
		          .attr("d", arc)
		          ;   
		  
		          arcs.append("text")   
		          .attr("x", 240)   
		          .attr("y", function(d, i){   
		            return i*24-100;   
		          })   
		          .attr("fill", function(d, i) {   
		            return color(i);   
		          })   
		          .text(function(d, i) {   
		            return d.data.name_tw;   
		          })
		          .style('font-size','large');   
		      //Labels   
		      arcs.append("text")   
		          .attr("transform", function(d) {   
		          var c = arc.centroid(d),   
		              x = c[0],   
		              y = c[1],   
		              // pythagorean theorem for hypotenuse   
		              h = Math.sqrt(x*x + y*y);   
		          return "translate(" + (x/h * labelr) +  ',' +   
		             (y/h * labelr) +  ")";    
		          })   
		          .attr("class", "pie_text")
		           .attr("fill", function(d, i) {   
		            return color(i);   
		          }) 
		          .attr("text-anchor", "middle")   
		          .text(function(d, i) {   
		            return _g1((d.data.count/tots)*100) + '%';   
		          });  
	  };
	
	  $scope.report_month=function(){
			var data=$window.monthdata;
			
			var margin = {top: 20, right: 20, bottom: 30, left: 40},
		    width = 960 - margin.left - margin.right,
		    height = 500 - margin.top - margin.bottom;

			var x0 = d3.scale.ordinal()
		    	.rangeRoundBands([0, width], .5);

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
	    	.rangeRoundBands([0, width], .5);

		var x1 = d3.scale.ordinal();

		var y = d3.scale.linear()
	    	.range([height, 0]);

		var xAxis = d3.svg.axis()
			.scale(x0)
			.tickSize(0)
			.orient("bottom");

		var yAxis = d3.svg.axis()
	    	.scale(y)
	    	.ticks(10)
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
	      //.attr("width", 30)
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
	      .attr("x",width - 24)
	      .attr("y", 9)
	      .attr("dy", ".35em")
	      .style("text-anchor", "end")
	      .style('font-size','x-large')
	      .text(function(d) {return d; });

		legend.transition().duration(500).delay(function(d,i){ return 1300 + 100 * i; }).style("opacity","1");
		
	  };
	  
	

	    
}]);
