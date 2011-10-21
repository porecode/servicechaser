define(["src/model/core-models",
  "scripts/lib/jquery/jquery.js",
  "scripts/lib/backbone/backbone-min.js"], function(Models) {
    var router = Backbone.Router.extend({
      
      routes: {
        "category/:parent_id": "list_categories"
      },

      list_categories: function(parent_id) {
        console.dir('here!');
        $('div.container').empty();
        var categories = new Models.CategoryCollection({"parent_id": 0});
        categories.fetch();
        categories.forEach(categories, function(cat) {
          $('div.container').append(cat.render().el);
        });
      }
    });
    
    return {
      MainRouter: router
    }
});
