define(["src/view/core-views"], function(Views) {

  return describe("Category view", function() {
    var view = new Views.Category();

    it("has render method", function() {
      expect(view.render).toBeDefined();
    });

  });

});

