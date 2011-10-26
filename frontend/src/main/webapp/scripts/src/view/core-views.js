define(["src/util/html-loader",
    "scripts/lib/mustache/mustache.js"], 
    function(Loader) {
      var CategoryClass = Backbone.View.extend({

        render: function() {
          var model = this.model;
          var el = this.el;
          Loader.getTemplate('category', function(templ) { 
            $(el).html(
              Mustache.to_html(templ, model.toJSON()));
          });
          return this;
        }

      });
                    
      return {
        Category: CategoryClass
      }
    }
);
