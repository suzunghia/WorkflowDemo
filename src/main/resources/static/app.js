(function () {
    var workFlowDemo = angular.module('WorkflowDemo', ['ngRoute', 'angularUtils.directives.dirPagination']);

    workFlowDemo.directive('active', function ($location) {
        return {
            link: function (scope, element) {
                function makeActiveIfMatchesCurrentPath() {
                    if ($location.path().indexOf(element.find('a').attr('href').substr(1)) > -1) {
                        element.addClass('active');
                    } else {
                        element.removeClass('active');
                    }
                }

                scope.$on('$routeChangeSuccess', function () {
                    makeActiveIfMatchesCurrentPath();
                });
            }
        };
    });
    
    workFlowDemo.directive('fileModel', [ '$parse', function($parse) {
    	return {
    		restrict : 'A',
    		link : function(scope, element, attrs) {
    			var model = $parse(attrs.fileModel);
    			var modelSetter = model.assign;

    			element.bind('change', function() {
    				scope.$apply(function() {
    					modelSetter(scope, element[0].files[0]);
    				});
    			});
    		}
    	};
    } ]);
    
    workFlowDemo.controller('CreateCustomerCtrl', function ($scope, $location, $http) {
        var self = this;
        
        self.add = function () {            
        	var customerModel = self.model;        	
        	var savedCustomer;
        	
        	var formData = new FormData();
        	formData.append('firstName', customerModel.firstName);
        	formData.append('lastName', customerModel.lastName);
        	formData.append('dateOfBirth', customerModel.dateOfBirth.getFullYear()  + '-' +  ('0' + (customerModel.dateOfBirth.getMonth() + 1)).slice(-2)  + '-' + ('0' + (customerModel.dateOfBirth.getDate())).slice(-2));
        		
        	$scope.saving=true;
        	$http.post('/customers', formData, {	
        	    transformRequest : angular.identity,
    			headers : {
    				'Content-Type' : undefined
    			}
    		}).success(function(savedCustomer) {
    			$scope.saving=false;
    			$location.path("/view-customer/" + savedCustomer.id);    			
    		}).error(function(data) {
    			$scope.saving=false; 
    		});
        };
    });
    
    workFlowDemo.controller('ViewCustomerCtrl', function ($scope, $http, $routeParams) {
        
    	var customerId = $routeParams.customerId;    	        
    	$scope.currentPage = 1;
    	$scope.pageSize = 10;
    	
    	$scope.dataLoading = true;
        $http.get('/customers/' + customerId).then(function onSuccess(response) {
        	$scope.customer = response.data;
        	$scope.dataLoading = false;
        }, function onError(response) {
        	$scope.customer = response.statusText;
        	$scope.dataLoading = false;
        });
    });
    
    workFlowDemo.controller('ViewAllCustomersCtrl', function ($scope, $http) {
    	
    	var self = this;
    	$scope.customers = []; 
    	$scope.searchText;
        
        $scope.dataLoading = true;
        $http.get('/customers').then(function mySucces(response) {
        	$scope.customers = response.data;
        	$scope.dataLoading = false;
        }, function myError(response) {
        	$scope.customer = response.statusText;
        	$scope.dataLoading = false;
        });        
        
        self.delete = function (customerId) {
        	$scope.selectedCustomer = customerId;
        	$scope.customerDelete = true;
        	$http.delete('/customers/' + customerId).then(function onSucces(response) {
            	$scope.customers = _.without($scope.customers, _.findWhere($scope.customers, {id: customerId}));
            	$scope.customerDelete = false;
            }, function onError(){
            	
            });
        },
        
        $scope.searchFilter = function (obj) {
            var re = new RegExp($scope.searchText, 'i');
            return !$scope.searchText || re.test(obj.firstName) || re.test(obj.lastName.toString());
        };
    });
    
    workFlowDemo.filter('formatDate', function() {
    	return function(input) {
    		return moment(input).format("DD-MM-YYYY");
    	};
    });
    
    workFlowDemo.config(function ($routeProvider) {
        $routeProvider.when('/home', {templateUrl: 'pages/home.tpl.html'});
        $routeProvider.when('/create-customer', {templateUrl: 'pages/createCustomer.tpl.html'});
        $routeProvider.when('/view-customer/:customerId', {templateUrl: 'pages/viewCustomer.tpl.html'});
        $routeProvider.when('/view-all-customers', {templateUrl: 'pages/viewAllCustomers.tpl.html'});
        $routeProvider.otherwise({redirectTo: '/home'});
    });
    
}());