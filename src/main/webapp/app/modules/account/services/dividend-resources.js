define(['./module'], 
function (module) {
	
	module.factory('DividendResource', function($resource) {
	    return $resource(
	        'api/account/STK/:accountId/dividend/:id',
	        {id: '@id', accountId: '@accountId'},
	        {

	        }
	    );
	});
});