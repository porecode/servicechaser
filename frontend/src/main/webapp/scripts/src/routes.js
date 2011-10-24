define(["src/model/core-models",
  "scripts/lib/jquery/jquery.js",
  "scripts/lib/backbone/backbone.js"], function(Models) {
    var router = Backbone.Router.extend({
      
      routes: {
        "/category/:parent_id": "list_categories",
        "*args": "defaultRoute"
      },

      list_categories: function(parent_id) {
        $('div.container').empty();
        var categories = new Models.CategoryCollection();
        categories.fetch({
          error: function(e, e1) {console.dir(e1);},
          complete: function(a, b) {console.dir(a); console.dir(b);},
          success: function(a,b) {console.dir(a); console.dir(b);}
        });
        categories.forEach(function(cat) {
          console.dir(cat);
          console.log(cat.get('title'));
          console.dir(cat.toJSON());
          $('div.container').append(cat.render().el);
        });
      },

      defaultRoute: function(args) {
        alert(args);
      }

    });
    
    return {
      MainRouter: router
    }
});
