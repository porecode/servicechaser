
require(["scripts/lib/underscore/underscore-min.js",
    "scripts/lib/jquery/jquery-min.js",
    "scripts/test/lib/jasmine/jasmine.js",
    "scripts/test/lib/jasmine/jasmine-html.js",
    //include files with tests here
    "scripts/test/test-core-jasmine.js",
    "scripts/src/view/core-views.js"
    ], function() {
  console.log("test js loaded");
  $(function() {
    jasmine.getEnv().addReporter(new jasmine.TrivialReporter());
    jasmine.getEnv().execute();
  });
});
