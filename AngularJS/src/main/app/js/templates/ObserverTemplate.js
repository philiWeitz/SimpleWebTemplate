/**
 * 
 */


// creates always a new object 
function ObserverFactory() {
	
	// represents the observer object
	var that = {};
	
	var subjects = [];
	
	that.notifyAll = function() {
		for(var i = 0; i < subjects.length; ++i) {
			if(subjects[i].notify !== undefined) {
				subjects[i].notify();
			}
		}
	};
	
	that.register = function(subject) {
		subjects.push(subject);
	};

	that.unregister = function(subject) {
		var idx = subjects.indexOf(subject); 
		
		if(-1 !== idx) {
			subjects.splice(idx, 1);
		}
	};
	
	return that;
};


function SubjectFactory() {
	
	// represents the subject object
	var that = {};
	
	// the registered observer
	var mObserver = undefined;
	
	// is called when something changed
	that.notify = function() {
		alert("Hello Observer! Something has changed");
	};

	// registers the subject at the observer
	that.register = function(observer) {
		mObserver = observer;
		observer.register(that);
	};

	// unregisters the subject from the observer
	that.unregister = function(observer) {
		mObserver = undefined;
		observer.unregister(that);
	};
	
	// if some data was changed, this function notifies all other subjects 
	that.change = function() {
		if(mObserver !== undefined) {
			mObserver.notifyAll();
		}
	};
	
	return that;
};

var subject = SubjectFactory();
var observer = ObserverFactory();

//subject.change();
//subject.register(observer);
//subject.change();
//subject.unregister(observer);
//subject.change();



