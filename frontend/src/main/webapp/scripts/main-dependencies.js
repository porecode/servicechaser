
require(["scripts/lib/less/less-min.js",
    "order!scripts/lib/jquery/jquery-min.js",
    "order!scripts/lib/underscore/underscore.js",
    "order!scripts/lib/backbone/backbone.js",

    "order!src/routes",
    "src/util/html-loader"], function() {
    var Routes = require("src/routes");
    var mainRouter = new Routes.MainRouter;
    Backbone.history.start();
});

