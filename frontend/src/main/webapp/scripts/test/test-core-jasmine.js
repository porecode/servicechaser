define(["src/view/core-views",
    "src/model/core-models",
    "src/util/html-loader"], function(Views, Models, Loader) {

  describe("Category view", function() {
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
  
  describe("Category model", function() {

    it("can be parsed from protobuf message", function() {
      var message = {
        id: 1,
        title: 'someTitle',
        children: []
      }
      var model = new Models.Category();
      model.parse(message);
      expect(model.get('id')).toBe(message.id);
      expect(model.get('title')).toBe(message.title);
      expect(model.get('children')).toBe(message.children);
    });

  });
});

