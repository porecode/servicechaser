
require(["scripts/lib/less/less-min.js",
    "scripts/lib/underscore/underscore.js",
    "scripts/lib/backbone/backbone.js",

    "src/routes",
    "src/util/html-loader"], function() {
    var Routes = require("src/routes");
    new Routes.MainRouter();
    Backbone.history.start({pushState: true});
});

