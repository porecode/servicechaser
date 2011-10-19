define(["src/view/core-views",
    "src/model/core-models"], function(Views, Models) {

  return describe("Category view", function() {
    var view = new Views.Category();

    it("has render method", function() {
      expect(view.render).toBeDefined();
    });

    it("renders model's title", function() {
      cat = new Models.Category();
      cat.set({title: 'catName'});
      view = new Views.Category({model: cat});
      expect(view.render().el.innerHTML).toContain('catName');
    });

  });

});

