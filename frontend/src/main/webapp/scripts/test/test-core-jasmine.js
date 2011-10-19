define(["src/view/core-views",
    "src/model/core-models",
    "src/util/html-loader"], function(Views, Models, Loader) {

  return describe("Category view", function() {
    var view = new Views.Category();

    it("has render method", function() {
      expect(view.render).toBeDefined();
    });

    it("renders model from template", function() {
      spyOn(Loader, 'getTemplate');
      
      cat = new Models.Category();
      cat.set({title: 'catName'});
      view = new Views.Category({model: cat});
      view.render();
      // suppose we've found template {{title}}
      Loader.getTemplate.mostRecentCall.args[1]('{{title}}');
      expect(view.el.innerHTML).toContain('catName');
    });

  });

});

