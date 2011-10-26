define(["src/model/core-models",
        "src/view/core-views"], function(Models, Views) {
    var router = Backbone.Router.extend({
      
      routes: {
        "/category/:parent_id": "list_categories",
        "*args": "defaultRoute"
      },

      list_categories: function(parent_id) {
        console.log("list_cat route, id " + parent_id);
        var categories = new Models.CategoryCollection();
        var container = $('div#categories');
        categories.fetch({
          success: function() {
            container.empty();
            categories.each(function(cat) {
              var v = new Views.Category({
                model: cat,
              }).render();
              container.append(v.el);
            });
          }
        });
      },

      defaultRoute: function(args) {
        console.log("default route " + args);
      }

    });
    
    return {
      MainRouter: router
    }
});
