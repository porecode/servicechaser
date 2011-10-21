define(["scripts/lib/backbone/backbone-min.js"], function() {

  // Category
  
  var Cat = Backbone.Model.extend({
    
    parse: function(json) {
      this.set(json);
    }
             
  });


  CatCol = Backbone.Collection.extend({

    model: Cat,

    parse: function(json) {
      for (modelJs in json) {
         var cat = new Category();
         this.add(cat.parse(modelJs));
      }
    }

  });

  return {
    Category: Cat, 
    CategoryCollection: CatCol
  }
});
