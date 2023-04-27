function fn() {
	var env = karate.env; // get java system property 'karate.env'
	karate.log('karate.env system property was:', env);
	karate.configure("ssl", true)

	if (!env) {
		env = 'dev'; // a custom 'intelligent' default
	}

    var config = {
        env: env,
        baseUrl: 'http://dummy.restapiexample.com/api/v1',
        appPath: '',
        appProxy: {},
        appHeaders: {},
    };

	    config.prepareUrl = function(baseUrl, params) {
            var query = [];
            for(p in params) {
                query.push(p + '=' + params[p]);
            }
            return baseUrl + '?' + query.join('&');
        }

        config.prepareQueryParameter = function(params) {
            var query = [];
            for(p in params) {
                query.push(p + '=' + params[p]);
            }
            return query.join('&');
        }

    /* Set end points and proxy */
    if (env == 'stg') {
            config.baseUrl = 'http://stg.dummy.restapiexample.com';
            //karate.configure('proxy', 'stg.dummy.restapiexample.com:8000');
            //config.appProxy = 'http://stg.dummy.restapiexample.com:8000';
        } else if (env == 'e2e') {
            config.baseUrl = 'http://e2e.dummy.restapiexample.com';
        } else if (env == 'dev') {
             config.baseUrl = 'http://dummy.restapiexample.com/api/v1';
        } else if (env == 'pre-prod') {
		  	config.baseUrl = 'http://pre-prod.dummy.restapiexample.com';
	 	}

	// don't waste time waiting for a connection or if servers don't respond
	// within 5 seconds
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 5000);
	return config;
}