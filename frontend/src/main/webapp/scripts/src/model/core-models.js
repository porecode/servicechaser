define(function() {

  // Category
  
  var Cat = Backbone.Model.extend({
    
//    parse: function(json) {
//      return json;
//    }
             
  });


  CatCol = Backbone.Collection.extend({

    model: Cat,
    url: "scripts/test/data/categories.json",

//    parse: function(json) {
//      console.log('parsing col');
//      for (i in json) {
//         var cat = new Cat();
//         this.add(json[i]);
//      }
//      return this;
//    }

  });

  return {
    Category: Cat, 
    CategoryCollection: CatCol
  }
});
