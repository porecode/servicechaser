define(function() {

  // Category
  
  var Cat = Backbone.Model.extend({
    
    parse: function(json) {
      //this.set('id', json.id);
      this.set(json);
      return this;
    }
             
  });


  CatCol = Backbone.Collection.extend({

    model: Cat,
    url: "scripts/test/data/categories.js",

    parse: function(json) {
      console.log(json);
      console.log('from collection');
      for (modelJs in json) {
         var cat = new Cat();
         this.add(cat.parse(modelJs));
      }
    }

  });

  return {
    Category: Cat, 
    CategoryCollection: CatCol
  }
});
