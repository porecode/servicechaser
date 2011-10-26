
require(["order!scripts/lib/jquery/jquery.js",
    "order!scripts/lib/underscore/underscore.js",
    "order!scripts/lib/backbone/backbone.js",
    "order!scripts/test/lib/jasmine/jasmine.js",
    "order!scripts/test/lib/jasmine/jasmine-html.js"

    //include files with tests here
    //"scripts/test/test-core-jasmine.js"
    ], function() {
  require([
    "test/test-core-jasmine"], function() {
    jasmine.getEnv().addReporter(new jasmine.TrivialReporter());
    jasmine.getEnv().execute();
  });
});
