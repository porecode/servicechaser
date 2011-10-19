require(["scripts/src/view/core-views.js"], function() {

  return describe("Category view", function() {
    console.log('here');  
    var view = new Views.Category();

    it("has render method", function() {
      expect(view.render).isDefined();
      console.log('hey');
    });

  });

});

