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
      var model = new Models.Category(message);
      expect(model.get('id')).toBe(message.id);
      expect(model.get('title')).toBe(message.title);
      expect(model.get('children')).toBe(message.children);
    });

    it("can be fetched from test resource", function() {
      var list = new Models.CategoryCollection();
      list.url = 'scripts/test/data/categories.json';

      expect(list.length).toBe(0);

      list.fetch({
        success: function(col) {
          expect(list.length).toBeGreaterThan(1);
          list.each(function(model) {
            expect(model.get('id')).toBeDefined();
            expect(model.get('title')).toBeDefined();
          });
        }
      });
    });

  });


});

