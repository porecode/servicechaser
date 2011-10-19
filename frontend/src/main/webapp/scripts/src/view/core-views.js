define(["scripts/lib/backbone/backbone-min.js"], 
    function() {
      var Category = Backbone.View.extend({
        
        render: function() {
          $(this.el).html(this.model.get('title'));
          return this;
        }

      });
                    
      return {
        Category: Category
      }}
);
