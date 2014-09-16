/**
 * 
 */

// creates always a new object 
function ObjectFactory(x) {
	
	// represents the new object
	var that = {};
	
	// creates a private variable for the object
	var privateVar = x;
	
	// creates a public var
	that.publicVar = "This is public";
	
	that.helloWorld = function() {
		alert("Hello World " + privateVar);
	};
	
	// returns the new created object
	return that;
};


//creates always a new object 
function InheritObjectFactory(x,y) {
	
	// represents the new object
	var that = ObjectFactory(x);

	that.publicVarInherit = y;
	
	// returns the new created object
	return that;
};


// each object is of the same type but hello world will be different
var obj1 = ObjectFactory("1");
var obj2 = ObjectFactory("2");

var inheritObj = InheritObjectFactory("3", "1");

