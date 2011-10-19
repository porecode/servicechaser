// tests for application models
require(["scripts/src/view/core-views.js",
   "scripts/src/model/core-models.js"], function() {

  module("Core entities");

  test("category entity inits with cid", function() {
    var entity = new Models.Category();
    ok("id defined", entity.cid);
    console.log(entity.cid);
  });


  module("Core views");
  
  test("category view exists", function() {
    ok("Category view is undefined", Views.Category);
  });

  test("category view has render method", function() {
    var view = new Views.Category();
    ok("no render method", view.render);
  });

  test("category view renders correctly", function() {
  });

});
