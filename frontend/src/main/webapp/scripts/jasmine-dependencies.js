
require(["scripts/lib/underscore/underscore-min.js",
    "scripts/lib/jquery/jquery-min.js",
    "scripts/test/lib/jasmine/jasmine.js",
    //include files with tests here
    "scripts/test/test-core-jasmine.js"
    ], function() {
  require(["scripts/test/lib/jasmine/jasmine-html.js"], function() {
    jasmine.getEnv().addReporter(new jasmine.TrivialReporter());
    jasmine.getEnv().execute();
  });
});
