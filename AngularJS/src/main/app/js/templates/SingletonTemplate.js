/**
 * 
 */

var MySingleton = (function() {

	// instance of the Singleton
	var instance = undefined;

	function init() {

		// private variables + functions
		var timestamp = Date.now();

		return {

			// Private methods and variables
			publicVar : "public var",
			
			helloSingleton : function() {
				alert("Hello Singleton " + timestamp);
			}
		};

	};

	return {

		// Get the Singleton instance
		getInstance : function() {
			if (!instance) {
				instance = init();
			}
			return instance;
		}
	};
})();


//access:

//MySingleton.getInstance().helloSingleton();
//MySingleton.getInstance().helloSingleton();
//alert(MySingleton.getInstance().publicVar);
