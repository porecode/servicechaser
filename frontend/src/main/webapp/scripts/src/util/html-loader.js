
define(['scripts/lib/jquery/jquery-min.js'],
    function() {
      function get(name) {
        tmlId = 'tml-'+ name;
        if ($(tmlId).length > 0) {
          return $('#' + tmlId).html();
        } else {
          $('<script/>')
            .attr('id', tmlId)
            .attr('type', 'text/x-mustache')
            .load('scripts/src/template/' + name + '.html')
            .appendTo('body');
        }
        return $('#' + tmlId).html();
      }

      return {
        getTemplate: get
      }
  });

